package br.com.vittaneo.entities;

public class Voo {

    private int id;
    private String codigo;
    private String rota;
    private String partida;
    private int duracao;
    private double preco;

    public int getId() {
        return id;
    }
    public void setId(int id) {

        this.id = id;
    }

    public String getCodigo() {

        return codigo;
    }
    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

    public String getRota() {
        return rota;
    }
    public void setRota(String rota) {
        this.rota = rota;
    }

    public String getPartida() {
        return partida;
    }
    public void setPartida(String partida) {
        this.partida = partida;
    }

    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}