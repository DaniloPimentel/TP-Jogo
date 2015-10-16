package servidor.sala;

public class TDM extends Sala{ // Team Death Match

    public TDM(int maxJogadores) {
        super(maxJogadores);
    }

    @Override
    public boolean servico(String corpo) {
        return true;
    }
    
}
