package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TrataCliente extends Thread {

    public static final int SERVICO_OLA = 1;
    public static final int SERVICO_MUDAR_APELIDO = 2;
    public static final int SERVICO_SOLICITAR_IDS = 3;
    public static final int SERVICO_SOLICITAR_APELIDO = 4;
    public static final int SERVICO_MENSAGEM = 5;
    public static final int SERVICO_NEGADO = 5000;

    private final Cliente cliente;

    public TrataCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {

        try {
            Scanner s = new Scanner(this.cliente.getSocket().getInputStream());
            while (s.hasNextLine()) {
                try {
                    Requisicao requisicao = new Requisicao(s.nextLine());

                    System.out.print("Cliente " + this.cliente.getId() + ": Nova requisicao ");

                    switch (requisicao.getServico()) {
                        case SERVICO_OLA: // Olá
                            System.out.println("(SERVICO_OLA)");
                            servico1(requisicao.getCorpo());
                            break;
                        default:
                            if (!verificaOla()) {
                                System.out.println("negada. Ainda não deu olá.");
                                enviar(new Resposta(SERVICO_NEGADO, "" + requisicao.getServico()));
                                break;
                            }
                            switch (requisicao.getServico()) {
                                case SERVICO_MUDAR_APELIDO: // Mudar apelido
                                    System.out.println("(SERVICO_MUDAR_APELIDO)");
                                    servico2(requisicao.getCorpo());
                                    break;
                                case SERVICO_SOLICITAR_IDS:
                                    System.out.println("(SERVICO_SOLICITAR_IDS)");
                                    servico3();
                                    break;
                                case SERVICO_SOLICITAR_APELIDO:
                                    System.out.println("(SERVICO_SOLICITAR_APELIDO)");
                                    servico4(requisicao.getCorpo());
                                    break;
                                case SERVICO_MENSAGEM:
                                    System.out.println("(SERVICO_MENSAGEM)");
                                    servico5(requisicao.getCorpo());
                                    break;
                            }
                            break;
                    }

                } catch (RequisicaoMalFormadaException ex) {
                    System.out.println("Cliente " + this.cliente.getId() + ": RequisicaoMalFormadaException (" + ex.getMessage() + ").");
                }
            }
        } catch (IOException ex) {
            System.out.println("IOException lançada pelo tratador do cliente " + this.cliente.getId());
        }

        cliente.disconectar();

    }

    private void servico1(String apelido) throws IOException {

        if (this.cliente.getGdc().isApelidoDisponivel(apelido)) {
            this.cliente.setApelido(apelido);
            enviar(new Resposta(SERVICO_OLA, "" + this.cliente.getId()));
            System.out.println("Cliente " + this.cliente.getId() + ": Deu olá com o apelido " + apelido);
        } else {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_OLA));
        }

    }

    private void servico2(String apelido) throws IOException {

        if (this.cliente.getGdc().isApelidoDisponivel(apelido)) {
            this.cliente.setApelido(apelido);
            System.out.println("Cliente " + this.cliente.getId() + ": Mudou o apelido para " + apelido);
            this.cliente.getGdc().mandarParaTodos(new Resposta(SERVICO_MUDAR_APELIDO, this.cliente.getId() + "###" + apelido));
        } else {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_MUDAR_APELIDO));
        }

    }

    private void servico3() throws IOException {

        List<Integer> ids = this.cliente.getGdc().getAllIds();

        String corpo = "";

        for (Integer id : ids) {
            if (!corpo.equals("")) {
                corpo += "###";
            }
            corpo += id;
        }

        enviar(new Resposta(SERVICO_SOLICITAR_IDS, corpo));

    }

    public void servico4(String corpo) throws IOException {

        int id = Integer.parseInt(corpo);

        Cliente c = this.cliente.getGdc().getClienteById(id);

        enviar(new Resposta(SERVICO_SOLICITAR_APELIDO, "" + c.getApelido()));

    }

    public void servico5(String corpo) throws IOException {

        try {

            Mensagem nova = new Mensagem(corpo);

            if (nova.getId2() == 0) {
                // Mensagem global
                Mensagem global = new Mensagem(this.cliente.getId(), 0, nova.getMensagem());
                this.cliente.getGdc().mandarParaTodos(new Resposta(SERVICO_MENSAGEM, global.encode()));
            } else {
                // Mensagem privada
                Cliente c;
                if( (c = this.cliente.getGdc().getClienteById(nova.getId2())) != null){
                    Mensagem privada = new Mensagem(this.cliente.getId(), nova.getId2(), nova.getMensagem());
                    enviar(new Resposta(SERVICO_MENSAGEM, privada.encode()));
                    c.getTratador().enviar(new Resposta(SERVICO_MENSAGEM, privada.encode()));
                } else{
                    enviar(new Resposta(SERVICO_NEGADO, ""+SERVICO_MENSAGEM));
                }
            }

        } catch (MensagemMalFormadaException ex) {
            System.out.println("Cliente " + this.cliente.getId() + ": MensagemMalFormadaException (" + ex.getMessage() + ").");
        }

    }

    public void enviar(Resposta resposta) throws IOException {

        PrintStream ps = new PrintStream(this.cliente.getSocket().getOutputStream());
        ps.println(resposta.encode());

    }

    public boolean verificaOla() {
        return !(this.cliente.getApelido() == null);
    }

    public Cliente getCliente() {
        return cliente;
    }
}
