package servidor.jogador;

import java.net.Socket;
import servidor.Servidor;

public class Jogador {

    public static int ID_JOGADORES = 1;

    private final int id;
    private String apelido;
    private final Socket socket;
    private int avatarCamisa;
    private int avatarCalca;

    private final TrataJogador tratador;

    private final Servidor servidor;

    public Jogador(Socket socket, Servidor servidor) {
        this.id = ID_JOGADORES++;
        this.socket = socket;
        this.servidor = servidor;
        this.tratador = new TrataJogador(this, servidor);
        this.tratador.start();
        this.avatarCalca=1;
        this.avatarCamisa=1;
    }

    public void disconectar() {

        this.servidor.getGdj().removeJogador(this.id);

        System.out.println("Jogador " + this.id + " disconectado.");

    }

    public Socket getSocket() {
        return socket;
    }

    public int getId() {
        return id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public TrataJogador getTratador() {
        return tratador;
    }

    public int getAvatarCamisa() {
        return avatarCamisa;
    }

    public void setAvatarCamisa(int avatarCamisa) {
        this.avatarCamisa = avatarCamisa;
    }

    public int getAvatarCalca() {
        return avatarCalca;
    }

    public void setAvatarCalca(int avatarCalca) {
        this.avatarCalca = avatarCalca;
    }

    public Servidor getServidor() {
        return servidor;
    }

}
