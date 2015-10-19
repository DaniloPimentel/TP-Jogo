package servidor.sala;

import java.util.ArrayList;
import java.util.List;
import servidor.jogador.Jogador;
import servidor.protocolos.RequisicaoSala;

abstract public class Sala {

    public static int ID_SALAS = 1;

    private int id;
    private int maxJogadores;
    private List<Jogador> jogadores;

    public Sala(int maxJogadores) {
        id = ID_SALAS++;
        jogadores = new ArrayList<>();
        this.maxJogadores = maxJogadores;
    }

    public boolean adicionar(Jogador novo) {

        if (!hasJogador(novo)) {
            this.jogadores.add(novo);
            return true;
        }

        return false; // Jogador já está na sala

    }

    public boolean remover(Jogador cliente) {

        if (hasJogador(cliente)) {
            this.jogadores.remove(cliente);
            return true;
        }

        return false; // Jogador não está na sala

    }

    public boolean hasJogador(Jogador cliente) {
        for (Jogador jogador : jogadores) {
            if (jogador.getId() == cliente.getId()) {
                return true;
            }
        }
        return false;
    }
    
    abstract public boolean servico(RequisicaoSala rs);

    public int getId() {
        return id;
    }

    public int getMaxJogadores() {
        return maxJogadores;
    }

}
