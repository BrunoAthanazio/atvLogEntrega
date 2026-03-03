package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entrega {
    private int id_entrega, pedido_id, motorista_id;
    private LocalDate data_saida, data_entrega;
    private String status, cliente_nome, motorista_nome;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-dd-MM");

    public Entrega(int id_entrega, int pedido_id, int motorista_id, String data_saida, String data_entrega, String status) {
        this.id_entrega = id_entrega;
        this.pedido_id = pedido_id;
        this.motorista_id = motorista_id;
        this.data_saida = LocalDate.parse(data_saida, FMT);
        this.data_entrega = LocalDate.parse(data_entrega, FMT);
        this.status = status;
    }

    public Entrega(int pedido_id, int motorista_id, String data_saida, String data_entrega, String status) {
        this.pedido_id = pedido_id;
        this.motorista_id = motorista_id;
        this.data_saida = LocalDate.parse(data_saida, FMT);
        this.data_entrega = LocalDate.parse(data_entrega, FMT);
        this.status = status;
    }

    public Entrega(int id_entrega, String cliente_nome, String motorista_nome, String data_saida, String data_entrega, String status){
        this.id_entrega = id_entrega;
        this.cliente_nome = cliente_nome;
        this.motorista_nome = motorista_nome;
        this.data_saida = LocalDate.parse(data_saida, FMT);
        this.data_entrega = LocalDate.parse(data_entrega, FMT);
        this.status = status;
    }

    public int getId_entrega() {
        return id_entrega;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public int getMotorista_id() {
        return motorista_id;
    }

    public LocalDate getData_saida() {
        return data_saida;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public void setMotorista_id(int motorista_id) {
        this.motorista_id = motorista_id;
    }

    public void setData_saida(LocalDate data_saida) {
        this.data_saida = data_saida;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente_nome() {
        return cliente_nome;
    }

    public String getMotorista_nome() {
        return motorista_nome;
    }
}
