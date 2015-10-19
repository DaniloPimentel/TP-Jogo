package cliente;

import cliente.protocolos.Resposta;
import cliente.protocolos.RespostaMalFormadaException;
import java.io.IOException;
import java.util.Scanner;

public class Recebedor extends Thread{
    
    private final Cliente cliente;
    
    public Recebedor(Cliente cliente) {
        
        this.cliente = cliente;
        
    }
    
    @Override
    public void run(){
        
        this.cliente.getGdj().start();
        this.cliente.getGds().start();
        
        try {
            
            Scanner s = new Scanner(this.cliente.getSocket().getInputStream());
            
            while(s.hasNextLine()) {
                
                try {
                    
                    Resposta nova = new Resposta(s.nextLine());
                    
                    System.out.print("Recebedor: Nova resposta ");
                    
                    switch(nova.getServico()) {
                        case Cliente.SERVICO_OLA:
                            System.out.println("(SERVICO_OLA)");
                            servicoOLA(nova.getCorpo());
                            break;
                        case Cliente.SERVICO_SOLICITAR_JOGADORES:
                            System.out.println("(SERVICO_SOLICITAR_JOGADORES)");
                            this.cliente.getGdj().atualizarListaJogadores(nova.getCorpo());
                            break;
                        case Cliente.SERVICO_SOLICITAR_SALAS:
                            System.out.println("(SERVICO_SOLICITAR_SALAS)");
                            this.cliente.getGds().atualizarListaSalas(nova.getCorpo());
                        case Cliente.SERVICO_NEGADO:
                            System.out.print("(SERVICO_NEGADO - ");
                            switch(Integer.parseInt(nova.getCorpo())) {
                                case Cliente.SERVICO_OLA:
                                    System.out.println("SERVICO_OLA)");
                                    this.cliente.setId(-1);
                                case Cliente.SERVICO_SOLICITAR_JOGADORES:
                                    System.out.println("SERVICO_SOLICITAR_JOGADORES)");
                                    break;
                                case Cliente.SERVICO_SOLICITAR_SALAS:
                                    System.out.println("SERVICO_SOLICITAR_SALAS)");
                                    break;
                            }
                    }
                    
                    
                } catch(RespostaMalFormadaException ex) {
                    System.out.println("Recebedor: RespostaMalFormadaException (" + ex.getMessage() + ").");
                }
                
            }
            
        } catch (IOException ex) {
            
        }
        
    }
    
    private void servicoOLA(String corpo) {
        
        try {
            
            int id = Integer.parseInt(corpo);
            
            this.cliente.setId(id);
            
        } catch (NumberFormatException ex) {
            
            this.cliente.setId(-1);
            
        }
        
    }
    
}
