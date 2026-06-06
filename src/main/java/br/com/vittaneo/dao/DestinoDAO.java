package br.com.vittaneo.dao;

import br.com.vittaneo.entities.Destino;
import br.com.vittaneo.infra.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

    public void inserir(Destino destino)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO TB_DESTINO (nm_destino, ds_destino) VALUES (?, ?)")) {
            stmt.setString(1, destino.getNome());
            stmt.setString(2, destino.getDescricao());
            stmt.executeUpdate();
        }
    }

    public List<Destino> listar()
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM TB_DESTINO");
             ResultSet rs = stmt.executeQuery()) {
            List<Destino> lista = new ArrayList<>();
            while (rs.next()) {
                Destino d = new Destino();
                d.setId(rs.getInt("id"));
                d.setNome(rs.getString("nm_destino"));
                d.setDescricao(rs.getString("ds_destino"));
                lista.add(d);
            }
            return lista;
        }
    }

    public Destino buscarPorId(int id)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM TB_DESTINO WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Destino d = new Destino();
                    d.setId(rs.getInt("id"));
                    d.setNome(rs.getString("nm_destino"));
                    d.setDescricao(rs.getString("ds_destino"));
                    return d;
                }
                return null;
            }
        }
    }

    public void atualizar(Destino destino)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "UPDATE TB_DESTINO SET nm_destino = ?, ds_destino = ? WHERE id = ?")) {
            stmt.setString(1, destino.getNome());
            stmt.setString(2, destino.getDescricao());
            stmt.setInt(3, destino.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM TB_DESTINO WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}