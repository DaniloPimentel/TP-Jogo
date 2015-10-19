package main;

import main.protocolos.Resposta;
import main.protocolos.RespostaMalFormadaException;
import java.io.IOException;
import java.util.Scanner;

public class Recebedor extends Thread{
    
    private final Main cliente;
    
    public Recebedor(Main cliente) {
        
        this.cliente = cliente;
        
    }
    
    @Override
    public void run(){
        
        try {
            
            Scanner s = new Scanner(this.cliente.getSocket().getInputStream());
            
            while(s.hasNextLine()) {
                
                try {
                    
                    Resposta nova = new Resposta(s.nextLine());
                    
                    System.out.print("Recebedor: Nova resposta ");
                    
                    switch(nova.getServico()) {
                        case Main.SERVICO_OLA:
                            System.out.println("(SERVICO_OLA)");
                            servicoOLA(nova.getCorpo());
                            break;
                        case Main.SERVICO_NEGADO:
                            System.out.print("(SERVICO_NEGADO - ");
                            switch(Integer.parseInt(nova.getCorpo())) {
                                case Main.SERVICO_OLA:
                                    System.out.println("SERVICO_OLA)");
                                    this.cliente.setId(-1);
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
