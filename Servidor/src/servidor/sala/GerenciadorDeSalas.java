package servidor.sala;

import java.util.ArrayList;
import java.util.List;
import servidor.jogador.Jogador;
import servidor.Servidor;
import servidor.protocolos.RequisicaoSala;

public class GerenciadorDeSalas {

    Servidor servidor;
    List<Sala> salas;

    public GerenciadorDeSalas(Servidor servidor) {

        this.servidor = servidor;
        salas = new ArrayList<>();

        salas.add(new FFA(8));
        salas.add(new FFA(8));
        salas.add(new FFA(8));
        salas.add(new FFA(8));
        salas.add(new TDM(8));
        salas.add(new TDM(8));
        salas.add(new TDM(8));
        salas.add(new TDM(8));

    }

    public boolean adicionarJogadorEmSala(int id, Jogador jogador) {

        for (Sala s : salas) {

            if (s.hasJogador(jogador)) {
                return false; // O jogador não pode ficar em mais de uma sala
            }

            if (s.getId() == id) {
                return s.adicionar(jogador);
            }
        }

        return false;
    }

    public boolean removerJogadorDaSala(int id, Jogador jogador) {
        
        for (Sala s : salas) {
            if (s.getId() == id) {
                return s.remover(jogador);
            }
        }

        return false;
        
    }
    
    public boolean repassarServicoASala(RequisicaoSala rs){
        
        for(Sala s: salas) {
            if(s.getId() == rs.getId()) {
                return s.servico(rs);
            }
        }
        
        return false;
        
    }

    public List<Sala> getAll() {
        return salas;
    }

}
