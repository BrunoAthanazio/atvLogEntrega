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
                """;
    }
}
