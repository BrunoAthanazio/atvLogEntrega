package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public List<Entrega> listar_entregas() throws SQLException{
        List<Entrega> entregas = new ArrayList<>();
        String query = """
                SELECT Entrega.id_entrega, 
                    Cliente.nome, 
                    Motorista.nome, 
                    Entrega.data_saida, 
                    Entrega.data_entrega,
                    Entrega.status
                FROM Entrega
                JOIN Pedido ON Entrega.pedido_id = Pedido.id_pedido
                JOIN Cliente ON Pedido.cliente_id = Cliente.id_cliente
                JOIN Motorista ON Entrega.motorista_id = Motorista.id_motorista;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_entrega = rs.getInt("Entrega.id_entrega");
                String cliente_nome = rs.getString("Cliente.nome");
                String motorista_nome = rs.getString("Motorista.nome");
                String data_saida = rs.getString("Entrega.data_saida");
                String data_entrega = rs.getString("Entrega.data_entrega");
                String status = rs.getString("Entrega.status");

                entregas.add(new Entrega(id_entrega, cliente_nome, motorista_nome, data_saida, data_entrega, status));
            }

        }
        return entregas;
    }

    public List<String> rel_entregas_motorista() throws SQLException{
        List<String> relatorio = new ArrayList<>();
        String linha = null;
        String query = """
                SELECT m.nome, COUNT(e.motorista_id) AS total_entregas
                FROM Entrega e
                RIGHT JOIN Motorista m ON e.motorista_id = m.id_motorista
                GROUP BY m.nome;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String nome = rs.getString("m.nome");
                String qtd_entregas = rs.getString("total_entregas");
                linha = nome + " | " + qtd_entregas;

                relatorio.add(linha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatorio;
    }

    public List<String> rel_cliente_volume() throws SQLException{
        List<String> relatorio = new ArrayList<>();
        String query = """
                SELECT c.nome, SUM(volume_m3) AS total_volume_entregue
                FROM Pedido p
                JOIN Cliente c ON p.cliente_id = c.id_cliente
                WHERE p.status = "ENTREGUE"
                GROUP BY p.cliente_id
                ORDER BY total_volume_entregue DESC;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String nome = rs.getString("c.nome");
                String total_volume = rs.getString("total_volume_entregue");
                String linha = nome + " | " + total_volume;

                relatorio.add(linha);
            }
        }
        return relatorio;
    }

    public List<String> rel_pedidos_pendentes() throws SQLException{
        List<String> relatorio = new ArrayList<>();
        String query = """
                SELECT c.estado, COUNT(p.id_pedido) AS pedidos_pendentes
                FROM Pedido p
                JOIN Cliente c ON p.cliente_id = c.id_cliente
                WHERE p.status = 'PENDENTE'
                GROUP BY c.estado;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String estado = rs.getString("c.estado");
                String numPedidos = rs.getString("pedidos_pendentes");
                String linha = estado + " | " + numPedidos;

                relatorio.add(linha);
            }
        }
        return relatorio;
    }
}
