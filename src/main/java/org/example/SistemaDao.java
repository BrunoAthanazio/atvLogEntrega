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

    public void criar_pedido(Pedido pedido) throws SQLException{
        String command = """
                INSERT INTO Pedido
                (cliente_id, data_pedido, volume_m3, peso_kg, status)
                VALUES
                (?,?,?,?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, pedido.getCliente_id());
            stmt.setObject(2, pedido.getData_pedido());
            stmt.setString(3, pedido.getVolume_m3());
            stmt.setString(4, pedido.getPeso_kg());
            stmt.setString(5, pedido.getStatus());
            stmt.executeUpdate();
        }
    }

    public void gerar_entrega(Entrega entrega) throws SQLException{
        String command = """
                INSERT INTO Entrega
                (pedido_id, motorista_id, data_saida, data_entrega, status)
                VALUES
                (?,?,?,?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, entrega.getPedido_id());
            stmt.setInt(2, entrega.getMotorista_id());
            stmt.setObject(3, entrega.getData_saida());
            stmt.setObject(4, entrega.getData_entrega());
            stmt.setString(5, entrega.getStatus());
            stmt.executeUpdate();
        }
    }

    public void gerar_HistoricoEntrega (HistoricoEntrega historicoEntrega) throws SQLException{
        String command = """
                INSERT INTO HistoricoEntrega
                (entrega_id, data_evento, descricao)
                VALUES
                (?,?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, historicoEntrega.getEntrega_id());
            stmt.setObject(2, historicoEntrega.getData_evento());
            stmt.setString(3, historicoEntrega.getDescricao());
            stmt.executeUpdate();
        }
    }

    public void atualizar_statusEntrega (String status, int id_entrega) throws SQLException{
        String command = """
                UPDATE Entrega
                SET status = ?
                WHERE id_entrega = ? 
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, status);
            stmt.setInt(2, id_entrega);
            stmt.executeUpdate();
        }
    }
}
