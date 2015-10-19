package servidor.jogador;

import servidor.protocolos.Resposta;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import servidor.Servidor;

public class GerenciadorDeJogadores {

    private final List<Jogador> jogadores;
    private final Servidor servidor;

    public GerenciadorDeJogadores(Servidor servidor) {
        this.servidor = servidor;
        this.jogadores = new ArrayList<>();
    }

    public void adicionaJogador(Socket socket) {
        Jogador novo = new Jogador(socket, servidor);
        this.jogadores.add(novo);
        System.out.println("Nova conexão com o jogador " + novo.getId());
    }

    public boolean removeJogador(int id) {

        for (Jogador c : jogadores) {
            if (c.getId() == id) {
                jogadores.remove(c);
                return true;
            }
        }

        return false;

    }

    public List<Jogador> getAll() {

        return jogadores;

    }
    
    public Jogador getJogadorById(int id){
        for(Jogador c : jogadores){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public void enviarParaTodos(Resposta r) {
        for (Jogador c : jogadores) {
            try {
                if (c.getTratador().verificaOla()) {
                    c.getTratador().enviar(r);
                }
            } catch (IOException ex) {
                System.out.println("IOException lançada pelo tratador do jogador " + c.getId());
                c.disconectar();
            }
        }
    }

    public boolean isApelidoDisponivel(String apelido) {
        for (Jogador jogador : jogadores) {
            if (jogador.getApelido() != null && jogador.getApelido().equals(apelido)) {
                return false;
            }
        }
        return true;
    }

}
