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
        
        try {
            
            Scanner s = new Scanner(this.cliente.getSocket().getInputStream());
            
            while(s.hasNextLine()) {
                
                try {
                    
                    Resposta nova = new Resposta(s.nextLine());
                    
                    System.out.println("Recebedor: Nova resposta ");
                    
                } catch(RespostaMalFormadaException ex) {
                    System.out.println("Recebedor: RespostaMalFormadaException (" + ex.getMessage() + ").");
                }
                
            }
            
        } catch (IOException ex) {
            
        }
        
    }
    
}
