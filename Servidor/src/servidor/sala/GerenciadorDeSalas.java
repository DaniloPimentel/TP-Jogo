package servidor.sala;

import servidor.sala.Sala;
import java.util.ArrayList;
import java.util.List;
import servidor.cliente.Cliente;
import servidor.Servidor;
import servidor.protocolos.RequisicaoSala;
import servidor.sala.FFA;
import servidor.sala.TDM;

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

    public boolean adicionarClienteEmSala(int id, Cliente cliente) {

        for (Sala s : salas) {

            if (s.hasCliente(cliente)) {
                return false; // O cliente não pode ficar em mais de uma sala
            }

            if (s.getId() == id) {
                return s.adicionar(cliente);
            }
        }

        return false;
    }

    public boolean removerClienteDaSala(int id, Cliente cliente) {
        
        for (Sala s : salas) {
            if (s.getId() == id) {
                return s.remover(cliente);
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

}
