package servidor.sala;

import servidor.protocolos.RequisicaoSala;

public class FFA extends Sala{ // Free For All

    public FFA(int maxJogadores) {
        super(maxJogadores);
    }
    
    @Override
    public boolean servico(RequisicaoSala rs) {
        return true;
    }
    
}
