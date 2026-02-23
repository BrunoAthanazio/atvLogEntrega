package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDao {
    public void cadastrar_cliente(Cliente cliente) throws SQLException{
        String command = """
                INSERT INTO Cliente
                (nome, cpf_cnpj, endereco, cidade, estado)
                VALUES
                (?,?,?,?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf_cnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();
        }
    }

    public void cadastrar_motorista(Motorista motorista) throws SQLException {
        String command = """
                INSERT INTO Motorista
                (nome, cnh, veiculo, cidade_base)
                VALUES
                (?,?,?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidade_base());
            stmt.executeUpdate();
        }
    }
}
