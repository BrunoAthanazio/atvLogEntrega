package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HistoricoEntrega {
    private int id_historicoEntrega, entrega_id;
    private LocalDate data_evento;
    private String descricao;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HistoricoEntrega(int id_historicoEntrega, int entrega_id, String data_evento, String descricao) {
        this.id_historicoEntrega = id_historicoEntrega;
        this.entrega_id = entrega_id;
        this.data_evento = LocalDate.parse(data_evento, FMT);
        this.descricao = descricao;
    }

    public HistoricoEntrega(int entrega_id, String data_evento, String descricao) {
        this.entrega_id = entrega_id;
        this.data_evento = LocalDate.parse(data_evento, FMT);
        this.descricao = descricao;
    }

    public int getId_historicoEntrega() {
        return id_historicoEntrega;
    }

    public int getEntrega_id() {
        return entrega_id;
    }

    public LocalDate getData_evento() {
        return data_evento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setEntrega_id(int entrega_id) {
        this.entrega_id = entrega_id;
    }

    public void setData_evento(LocalDate data_evento) {
        this.data_evento = data_evento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
