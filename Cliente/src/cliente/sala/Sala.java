package cliente.sala;

public class Sala {

    private int id;
    private String mapa;
    private String tipo;
    private int jogadores;
    private int maxJogadores;

    public Sala(int id, String mapa, String tipo, int jogadores, int maxJogadores) {
        this.id = id;
        this.mapa = mapa;
        this.tipo = tipo;
        this.jogadores = jogadores;
        this.maxJogadores = maxJogadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getJogadores() {
        return jogadores;
    }

    public void setJogadores(int jogadores) {
        this.jogadores = jogadores;
    }

    public int getMaxJogadores() {
        return maxJogadores;
    }

    public void setMaxJogadores(int maxJogadores) {
        this.maxJogadores = maxJogadores;
    }

}
