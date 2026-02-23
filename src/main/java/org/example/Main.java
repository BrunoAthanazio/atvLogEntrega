package org.example;

import java.sql.Connection;
import java.sql.SQLException;
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
}