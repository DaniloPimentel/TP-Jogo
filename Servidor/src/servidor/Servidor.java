package servidor;

import servidor.jogador.GerenciadorDeJogadores;
import servidor.sala.GerenciadorDeSalas;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {

        new Servidor(12345).executa();

    }

    private final int porta;
    private final ServerSocket servidor;
    private final GerenciadorDeJogadores gdj;
    private final GerenciadorDeSalas gds;

    public Servidor(int porta) throws IOException {
        this.porta = porta;
        this.servidor = new ServerSocket(this.porta);
        this.gdj = new GerenciadorDeJogadores(this);
        this.gds = new GerenciadorDeSalas(this);
    }

    public void executa() throws IOException {

        while (true) {

            Socket jogador = servidor.accept();

            gdj.adicionaJogador(jogador);

        }

    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public GerenciadorDeJogadores getGdj() {
        return gdj;
    }

    public GerenciadorDeSalas getGds() {
        return gds;
    }

}
