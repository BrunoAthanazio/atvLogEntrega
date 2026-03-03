package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private int id_pedido, cliente_id;
    private String status;
    private String volume_m3, peso_kg;
    private LocalDate data_pedido;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Pedido(int id_pedido, int cliente_id, String status, String volume_m3, String peso_kg, String data_pedido) {
        this.id_pedido = id_pedido;
        this.cliente_id = cliente_id;
        this.status = status;
        this.volume_m3 = volume_m3;
        this.peso_kg = peso_kg;
        this.data_pedido = LocalDate.parse(data_pedido, FMT);
    }

    public Pedido(int cliente_id, String status, String volume_m3, String peso_kg, String data_pedido) {
        this.cliente_id = cliente_id;
        this.status = status;
        this.volume_m3 = volume_m3;
        this.peso_kg = peso_kg;
        this.data_pedido = LocalDate.parse(data_pedido, FMT);
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public String getStatus() {
        return status;
    }

    public String getVolume_m3() {
        return volume_m3;
    }

    public String getPeso_kg() {
        return peso_kg;
    }

    public LocalDate getData_pedido() {
        return data_pedido;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVolume_m3(String volume_m3) {
        this.volume_m3 = volume_m3;
    }

    public void setCidade_base(String cidade_base) {
        this.peso_kg = cidade_base;
    }

    public void setData_pedido(LocalDate data_pedido) {
        this.data_pedido = data_pedido;
    }
}