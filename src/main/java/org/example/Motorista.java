package org.example;

public class Motorista {
    private int motorista_id;
    private String nome,cnh, veiculo, cidade_base;

    public Motorista (int motorista_id, String nome, String cnh, String veiculo, String cidade_base) {
        this.motorista_id = motorista_id;
        this.nome = nome;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.cidade_base = cidade_base;
    }

    public int getMotorista_id() {
        return motorista_id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnh() {
        return cnh;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public String getCidade_base() {
        return cidade_base;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public void setCidade_base(String cidade_base) {
        this.cidade_base = cidade_base;
    }
}
