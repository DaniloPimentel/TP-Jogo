package main.protocolos;

public class Requisicao {

    private final int servico;
    private final String corpo;

    public Requisicao(int servico, String corpo) {
        this.servico = servico;
        this.corpo = corpo;
    }

    public String encode() {
        return this.servico + "&&&" + this.corpo;
    }

    public int getServico() {
        return servico;
    }

    public String getCorpo() {
        return corpo;
    }
}
