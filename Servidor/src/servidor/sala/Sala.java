package servidor.sala;

import java.util.ArrayList;
import java.util.List;
import servidor.cliente.Cliente;
import servidor.protocolos.RequisicaoSala;

abstract public class Sala {

    public static int ID_SALAS = 1;

    private int id;
    private int maxJogadores;
    private List<Cliente> jogadores;

    public Sala(int maxJogadores) {
        id = ID_SALAS++;
        jogadores = new ArrayList<>();
        this.maxJogadores = maxJogadores;
    }

    public boolean adicionar(Cliente novo) {

        if (!hasCliente(novo)) {
            this.jogadores.add(novo);
            return true;
        }

        return false; // Jogador já está na sala

    }

    public boolean remover(Cliente cliente) {

        if (hasCliente(cliente)) {
            this.jogadores.remove(cliente);
            return true;
        }

        return false; // Jogador não está na sala

    }

    public boolean hasCliente(Cliente cliente) {
        for (Cliente jogador : jogadores) {
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
