package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                 ____________________________________________________________
                |    1 - Cadastrar Cliente                                   |
                |    2 - Cadastrar Motorista                                 |
                |    3 - Criar Pedido                                        |
                |    4 - Atribuir Pedido a Motorista (Gerar Entrega)         |
                |    5 - Registrar Evento de Entrega (Histórico)             |
                |    6 - Atualizar Status da Entrega                         |
                |    7 - Listar Todas as Entregas com Cliente e Motorista    |
                |    8 - Relatório: Total de Entregas por Motorista          |
                |    9 - Relatório: Clientes com Maior Volume Entregue       |
                |    10 - Relatório: Pedidos Pendentes por Estado            |
                |    11 - Relatório: Entregas Atrasadas por Cidade           |
                |    12 - Buscar Pedido por CPF/CNPJ do Cliente              |
                |____________________________________________________________|
                """);
    }
}