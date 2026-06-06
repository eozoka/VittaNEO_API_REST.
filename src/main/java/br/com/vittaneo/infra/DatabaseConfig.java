package br.com.vittaneo.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "rm567593";
    private static final String PASSWORD = "230505";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initialize() {
        System.out.println("Iniciando criação das tabelas...");
        criarTabelaDestino();
        criarTabelaVoo();
    }

    private static void criarTabelaDestino() {
        var sql = """
                CREATE TABLE TB_DESTINO (
                    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                    nm_destino VARCHAR2(100) NOT NULL,
                    ds_destino VARCHAR2(255)
                )
                """;
        executarTryCatch(sql, "TB_DESTINO");
    }

    private static void criarTabelaVoo() {
        var sql = """
            CREATE TABLE TB_VOO (
                id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                cd_voo VARCHAR2(20) NOT NULL,
                nm_rota VARCHAR2(150) NOT NULL,
                dt_partida VARCHAR2(50),
                nr_duracao NUMBER,
                vl_preco NUMBER(20,2)
            )
            """;
        executarTryCatch(sql, "TB_VOO");
    }

    private static void executarTryCatch(String sql, String tabela) {
        try (var conn = getConnection()) {
            conn.prepareStatement(sql).execute();
            System.out.println("A tabela " + tabela + " foi criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("A tabela " + tabela + " já existe ou aconteceu o erro: " + e.getMessage());
        }
    }
}