package servidor;

import java.net.Socket;

public class Cliente {

    public static int ID_CLIENTES = 1;

    private final int id;
    private String apelido;
    private final Socket socket;

    private final TrataCliente tratador;

    private final GerenciadorDeClientes gdc;

    public Cliente(Socket socket, GerenciadorDeClientes gdc) {
        this.id = ID_CLIENTES++;
        this.socket = socket;
        this.gdc = gdc;
        this.tratador = new TrataCliente(this);
        this.tratador.start();
    }

    public void disconectar() {

        this.gdc.removeCliente(this.id);

        System.out.println("Cliente " + this.id + " disconectado.");

    }

    public Socket getSocket() {
        return socket;
    }

    public int getId() {
        return id;
    }

    public GerenciadorDeClientes getGdc() {
        return gdc;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public TrataCliente getTratador() {
        return tratador;
    }

}
