package servidor.sala;

import servidor.protocolos.RequisicaoSala;

public class TDM extends Sala{ // Team Death Match

    public TDM(int maxJogadores) {
        super(maxJogadores);
    }

    @Override
    public boolean servico(RequisicaoSala rs) {
        return true;
    }
    
}
