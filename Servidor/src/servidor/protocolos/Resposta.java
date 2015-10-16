package servidor.protocolos;

public class Resposta {

    private final int servico;
    private final String corpo;

    public Resposta(int servico, String corpo) {
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
