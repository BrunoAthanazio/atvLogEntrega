package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }

    private static void inicio(){
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
        int opcao = SC.nextInt();
        SC.nextLine();

        switch (opcao){
            case 1:{
                cadastrar_cliente();
                break;
            }
            case 2:{
                cadastrar_motorista();
                break;
            }
            case 3: {
                criar_pedido();
                break;
            }
            case 4: {
                gerar_entrega();
                break;
            }
            case 5: {
                gerar_historicoEntrega();
                break;
            }
            case 6: {
                atualizar_statusEntrega();
                break;
            }
            case 7: {
                listar_entregas();
                break;
            }
        }
    }

    public static void cadastrar_cliente() {
        System.out.println("Insira o nome do cliente: ");
        String nome = SC.nextLine();

        System.out.println("Insira cpf/cnpj: ");
        String cpf_cnpj = SC.nextLine();

        System.out.println("Insira o endereço: ");
        String endereco = SC.nextLine();

        System.out.println("Insira a cidade: ");
        String cidade = SC.nextLine();

        System.out.println("Insira o estado: ");
        String estado = SC.nextLine();

        Cliente cliente = new Cliente(nome, cpf_cnpj, endereco, cidade, estado);
        var dao = new SistemaDao();

        try {
            dao.cadastrar_cliente(cliente);
        }catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados!");
            e.printStackTrace();
        }
    }

    public static void cadastrar_motorista() {
        System.out.println("Insira o nome do motorista: ");
        String nome = SC.nextLine();

        System.out.println("Insira a cnh: ");
        String cnh = SC.nextLine();

        System.out.println("Insira o veículo: ");
        String veiculo = SC.nextLine();

        System.out.println("Insira a cidade base do motorista: ");
        String cidade_base = SC.nextLine();

        Motorista motorista = new Motorista(nome, cnh, veiculo, cidade_base);
        var dao = new SistemaDao();

        try{
            dao.cadastrar_motorista(motorista);
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados!");
            e.printStackTrace();
        }
    }

    public static void criar_pedido(){
        var dao = new SistemaDao();

        System.out.println("Insira o id do cliente");
        int cliente_id = SC.nextInt();
        SC.nextLine();

        System.out.println("Insira a data do pedido conforme o formato aaaa-dd-mm");
        String data_pedido = SC.nextLine();

        System.out.println("Insira o volume do pedido");
        String volume_m3 = SC.nextLine();

        System.out.println("Insira o peso do pedido");
        String peso_kg = SC.nextLine();

        System.out.println("""
               Insira o status do pedido:
               1. PENDENTE
               2. ENTREGUE
               3. CANCELADO
               """);
        int opcao = SC.nextInt();
        String status = "PENDENTE";
        switch (opcao) {
            case 1: {
                status = "PENDENTE";
                break;
            }
            case 2:{
                status = "ENTREGUE";
                break;
            }
            case 3:{
                status = "CANCELADO";
                break;
            }
        }
        Pedido pedido = new Pedido(cliente_id, status, volume_m3, peso_kg, data_pedido);
        try{
            dao.criar_pedido(pedido);
        }catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados!");
            e.printStackTrace();
        }
    }

    public static void gerar_entrega(){
        var dao = new SistemaDao();

        System.out.println("Insira o id do pedido:");
        int pedido_id = SC.nextInt();

        System.out.println("Insira o id do motorista:");
        int motorista_id = SC.nextInt();
        SC.nextLine();

        System.out.println("Insira a data de saída da entrega no formato aaaa-dd-mm");
        String data_saida = SC.nextLine();

        System.out.println("Insira a data de entrega da entrega no formato aaaa-dd-mm");
        String data_entrega = SC.nextLine();

        System.out.println("""
               Insira o status da entrega:
               1. EM ROTA
               2. ENTREGUE
               3. ATRASADA
               """);
        int opcao = SC.nextInt();
        String status = "EM_ROTA";
        switch (opcao) {
            case 1: {
                status = "EM_ROTA";
                break;
            }
            case 2: {
                status = "ENTREGUE";
                break;
            }
            case 3: {
                status = "ATRASADA";
                break;
            }
        }
        Entrega entrega = new Entrega(pedido_id, motorista_id, data_saida, data_entrega, status);
        try{
            dao.gerar_entrega(entrega);
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o baco de dados!");
            e.printStackTrace();
        }
    }

    public static void gerar_historicoEntrega(){
        var dao = new SistemaDao();

        System.out.println("Insira o id da entrega:");
        int entrega_id = SC.nextInt();
        SC.nextLine();

        System.out.println("Insira a data do evento no formato aaaa-dd-mm");
        String data_evento = SC.nextLine();

        System.out.println("Faça uma descrição do evento:");
        String descricao = SC.nextLine();

        HistoricoEntrega historicoEntrega = new HistoricoEntrega(entrega_id, data_evento, descricao);
        try {
            dao.gerar_HistoricoEntrega(historicoEntrega);
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados!");
            e.printStackTrace();
        }
    }

    public static void atualizar_statusEntrega() {
        var dao = new SistemaDao();
        System.out.println("Insira o id da entrega que deseja atualizar:");
        int id_entrega = SC.nextInt();
        SC.nextLine();

        System.out.println("""
               Insira o novo status da entrega:
               1. EM ROTA
               2. ENTREGUE
               3. ATRASADA
               """);
        int opcao = SC.nextInt();
        String status = "EM_ROTA";
        switch (opcao) {
            case 1: {
                status = "EM_ROTA";
                break;
            }
            case 2: {
                status = "ENTREGUE";
                break;
            }
            case 3: {
                status = "ATRASADA";
                break;
            }
        }
        try{
            dao.atualizar_statusEntrega(status, id_entrega);
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados!");
            e.printStackTrace();
        }
    }

    public static void listar_entregas() {
        List<Entrega> entregas = new ArrayList<>();
        var dao = new SistemaDao();
        try{
            entregas = dao.listar_entregas();
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados!");
            e.printStackTrace();
        }

        for(Entrega entrega : entregas) {
            System.out.println("ID: " + entrega.getId_entrega());
            System.out.println("Cliente: " + entrega.getCliente_nome());
            System.out.println("Motorista: " + entrega.getMotorista_nome());
            System.out.println("Data de saída: " + entrega.getData_saida());
            System.out.println("Data de entrega: " + entrega.getData_entrega());
            System.out.println("Status da entrega: " + entrega.getStatus());
        }
    }
}