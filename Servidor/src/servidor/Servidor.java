package servidor;

import servidor.cliente.GerenciadorDeClientes;
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
    private final GerenciadorDeClientes gdc;
    private final GerenciadorDeSalas gds;

    public Servidor(int porta) throws IOException {
        this.porta = porta;
        this.servidor = new ServerSocket(this.porta);
        this.gdc = new GerenciadorDeClientes(this);
        this.gds = new GerenciadorDeSalas(this);
    }

    public void executa() throws IOException {

        while (true) {

            Socket cliente = servidor.accept();
            
            gdc.adicionaCliente(cliente);

        }

    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public GerenciadorDeClientes getGdc() {
        return gdc;
    }

    public GerenciadorDeSalas getGds() {
        return gds;
    }

}
