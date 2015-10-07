package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class TrataCliente extends Thread {

    private final Cliente cliente;

    public TrataCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {

        try {
            Scanner s = new Scanner(this.cliente.getSocket().getInputStream());
            while (s.hasNextLine()) {
                Requisicao requisicao = new Requisicao(s.nextLine());
                System.out.print("Cliente " + this.cliente.getId() + ": Nova requisicao ");
                switch (requisicao.getServico()) {
                    case 1: // Olá
                        System.out.println("(tipo 1)");
                        servico1(requisicao.getCorpo());
                        break;
                    case 2: // Mudar apelido
                        System.out.println("(tipo 2)");
                        servico2(requisicao.getCorpo());
                        break;
                }
            }
        } catch (IOException ex) {
            System.out.println("IOException lançada pelo tratador do cliente " + this.cliente.getId());
        } catch (RequisicaoMalFormadaException ex) {
            
        }

        cliente.disconectar();

    }

    private void servico1(String apelido) throws IOException, RequisicaoMalFormadaException {

        if (this.cliente.getGdc().isApelidoDisponivel(apelido)) {
            this.cliente.setApelido(apelido);
            enviar(new Resposta(1, ""));
            System.out.println("Cliente " + this.cliente.getId() + ": Deu olá com o apelido " + apelido);
        } else {
            enviar(new Resposta(5001, ""));
        }

    }
    
    private void servico2(String apelido) throws IOException, RequisicaoMalFormadaException {

        if (this.cliente.getGdc().isApelidoDisponivel(apelido) && verificaOla()) {
            this.cliente.setApelido(apelido);
            System.out.println("Cliente " + this.cliente.getId() + ": Mudou o apelido para " + apelido);
            this.cliente.getGdc().mandarParaTodos(new Resposta(2, this.cliente.getId() + "###" + apelido));
        } else {
            enviar(new Resposta(5002, ""));
        }

    }

    public void enviar(Resposta resposta) throws IOException {

        PrintStream ps = new PrintStream(this.cliente.getSocket().getOutputStream());
        ps.println(resposta.encode());

    }
    
    public boolean verificaOla(){
        return !(this.cliente.getApelido() == null);
    }

    public Cliente getCliente() {
        return cliente;
    }
}
