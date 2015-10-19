package main;

import main.protocolos.Requisicao;
import form.ConfigInicial;
import form.JanelaPrincipal;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public static final int SERVICO_TCHAU = 0;
    public static final int SERVICO_OLA = 1;
    public static final int SERVICO_MUDAR_APELIDO = 2;
    public static final int SERVICO_SOLICITAR_CLIENTES = 3;
    public static final int SERVICO_MENSAGEM = 4;
    public static final int SERVICO_AVATAR = 5;
    public static final int SERVICO_ENTRAR_SALA = 6;
    public static final int SERVICO_SAIR_SALA = 7;
    public static final int SERVICO_SALA = 8;
    public static final int SERVICO_NEGADO = 5000;

    public static final String ERRO_CONEXAO = "Erro ao se conectar ao servidor";
    public static final String ERRO_SERVICO_OLA = "Erro ao enviar o apelido ao servidor. Tente novamente.";

    private Socket socket;
    private final ConfigInicial configInicial;
    private JanelaPrincipal janela;
    private final Recebedor recebedor;

    private Integer id;
    private String apelido;
    private int avatar_camisa;
    private int avatar_calca;

    Main() {
        configInicial = new ConfigInicial(this);
        recebedor = new Recebedor(this);
    }

    public void enviar(Requisicao requisicao) throws IOException {

        PrintStream ps = new PrintStream(this.socket.getOutputStream());
        ps.println(requisicao.encode());

    }

    public Socket getSocket() {
        return socket;
    }

    public Recebedor getRecebedor() {
        return recebedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getAvatar_camisa() {
        return avatar_camisa;
    }

    public void setAvatar_camisa(int avatar_camisa) {
        this.avatar_camisa = avatar_camisa;
    }

    public int getAvatar_calca() {
        return avatar_calca;
    }

    public void setAvatar_calca(int avatar_calca) {
        this.avatar_calca = avatar_calca;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public JanelaPrincipal getJanela() {
        return janela;
    }

    public void setJanela(JanelaPrincipal janela) {
        this.janela = janela;
    }

}
