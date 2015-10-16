package cliente;

import cliente.protocolos.Requisicao;
import form.Janela;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        
        new Cliente();
        
    }
    
    public static final int SERVICO_OLA = 1;
    public static final int SERVICO_MUDAR_APELIDO = 2;
    public static final int SERVICO_SOLICITAR_IDS = 3;
    public static final int SERVICO_SOLICITAR_APELIDO = 4;
    public static final int SERVICO_MENSAGEM = 5;
    
    public static final String ERRO_CONEXAO = "Erro ao se conectar ao servidor";
    public static final String ERRO_SERVICO_OLA = "Erro ao enviar o apelido ao servidor. Tente novamente.";
    
    private Socket socket;
    private Janela janela;
    private Recebedor recebedor;
    
    private int id;
    private String apelido;
    private int avatar_camisa;
    private int avatar_calca;
    
    Cliente(){
        janela = new Janela(this);
        recebedor = new Recebedor(this);
        recebedor.start();
    }
    
    public void enviar(Requisicao requisicao) throws IOException {

        PrintStream ps = new PrintStream(this.socket.getOutputStream());
        ps.println(requisicao.encode());

    }

    public Socket getSocket() {
        return socket;
    }

}
