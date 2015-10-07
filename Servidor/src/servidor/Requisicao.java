package servidor;

public class Requisicao {

    private final int servico;
    private final String corpo;
    
    public Requisicao(String requisicao) throws RequisicaoMalFormadaException{
        
        String[] partes = requisicao.split("&&&");
        
        if(partes.length == 1 || partes.length == 2){
        
            try {
                this.servico = Integer.parseInt(requisicao.split("&&&")[0]);
            } catch(NumberFormatException ex){
                throw new RequisicaoMalFormadaException("Serviço inválido.");
            }
            
            if(partes.length == 2){
                this.corpo = partes[1];
            } else{
                this.corpo = "";
            }
            
        } else{
            throw new RequisicaoMalFormadaException("Não possui identificador primario.");
        }
    }
    
    public String encode(){
        return this.servico+"&&&"+this.corpo;
    }

    public int getServico() {
        return servico;
    }

    public String getCorpo() {
        return corpo;
    }

}
