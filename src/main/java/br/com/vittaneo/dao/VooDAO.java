package br.com.vittaneo.dao;

import br.com.vittaneo.entities.Voo;
import br.com.vittaneo.infra.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VooDAO {

    public void inserir(Voo voo)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO TB_VOO (cd_voo, nm_rota, dt_partida, nr_duracao, vl_preco) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, voo.getCodigo());
            stmt.setString(2, voo.getRota());
            stmt.setString(3, voo.getPartida());
            stmt.setInt(4, voo.getDuracao());
            stmt.setDouble(5, voo.getPreco());
            stmt.executeUpdate();
        }
    }

    public List<Voo> listar()
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM TB_VOO");
             ResultSet rs = stmt.executeQuery()) {
            List<Voo> lista = new ArrayList<>();
            while (rs.next()) {
                Voo v = new Voo();
                v.setId(rs.getInt("id"));
                v.setCodigo(rs.getString("cd_voo"));
                v.setRota(rs.getString("nm_rota"));
                v.setPartida(rs.getString("dt_partida"));
                v.setDuracao(rs.getInt("nr_duracao"));
                v.setPreco(rs.getDouble("vl_preco"));
                lista.add(v);
            }
            return lista;
        }
    }

    public Voo buscarPorId(int id)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM TB_VOO WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Voo v = new Voo();
                    v.setId(rs.getInt("id"));
                    v.setCodigo(rs.getString("cd_voo"));
                    v.setRota(rs.getString("nm_rota"));
                    v.setPartida(rs.getString("dt_partida"));
                    v.setDuracao(rs.getInt("nr_duracao"));
                    v.setPreco(rs.getDouble("vl_preco"));
                    return v;
                }
                return null;
            }
        }
    }

    public void atualizar(Voo voo)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "UPDATE TB_VOO SET cd_voo = ?, nm_rota = ?, dt_partida = ?, nr_duracao = ?, vl_preco = ? WHERE id = ?")) {
            stmt.setString(1, voo.getCodigo());
            stmt.setString(2, voo.getRota());
            stmt.setString(3, voo.getPartida());
            stmt.setInt(4, voo.getDuracao());
            stmt.setDouble(5, voo.getPreco());
            stmt.setInt(6, voo.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id)
            throws Exception {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM TB_VOO WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}