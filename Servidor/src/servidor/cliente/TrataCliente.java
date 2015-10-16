package servidor.cliente;

import servidor.protocolos.Mensagem;
import servidor.protocolos.RequisicaoMalFormadaException;
import servidor.protocolos.Resposta;
import servidor.protocolos.MensagemMalFormadaException;
import servidor.protocolos.Requisicao;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import servidor.Servidor;
import servidor.protocolos.RequisicaoSala;

public class TrataCliente extends Thread {

    public static final int SERVICO_TCHAU = 0;
    public static final int SERVICO_OLA = 1;
    public static final int SERVICO_MUDAR_APELIDO = 2;
    public static final int SERVICO_SOLICITAR_CLIENTES = 3;
    public static final int SERVICO_MENSAGEM = 4;
    public static final int SERVICO_AVATAR = 5;
    public static final int SERVICO_ENTRAR_SALA = 6;
    public static final int SERVICO_SAIR_SALA = 7;
    public static final int SERVICO_SALA = 8;
    public static final int SERVICO_NEGADO = 5000;

    private final Cliente cliente;
    private final Servidor servidor;

    public TrataCliente(Cliente cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
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
                        case SERVICO_OLA:
                            System.out.println("(SERVICO_OLA)");
                            servicoOLA(requisicao.getCorpo());
                            break;
                        default:
                            if (!verificaOla()) {
                                System.out.println("negada. Ainda não deu olá.");
                                enviar(new Resposta(SERVICO_NEGADO, "" + requisicao.getServico()));
                                break;
                            }
                            switch (requisicao.getServico()) {
                                case SERVICO_TCHAU:
                                    System.out.println("(SERVICO_TCHAU)");
                                    servicoTCHAU();
                                    break;
                                case SERVICO_MUDAR_APELIDO:
                                    System.out.println("(SERVICO_MUDAR_APELIDO)");
                                    servicoMUDAR_APELIDO(requisicao.getCorpo());
                                    break;
                                case SERVICO_SOLICITAR_CLIENTES:
                                    System.out.println("(SERVICO_SOLICITAR_CLIENTES)");
                                    servicoSOLICITAR_CLIENTES();
                                    break;
                                case SERVICO_MENSAGEM:
                                    System.out.println("(SERVICO_MENSAGEM)");
                                    servicoMENSAGEM(requisicao.getCorpo());
                                    break;
                                case SERVICO_AVATAR:
                                    System.out.println("(SERVICO_AVATAR)");
                                    servicoAVATAR(requisicao.getCorpo());
                                    break;
                                case SERVICO_ENTRAR_SALA:
                                    System.out.println("(SERVICO_ENTRAR_SALA)");
                                    servicoENTRAR_SALA(requisicao.getCorpo());
                                    break;
                                case SERVICO_SAIR_SALA:
                                    System.out.println("(SERVICO_SAIR_SALA)");
                                    servicoSAIR_SALA(requisicao.getCorpo());
                                    break;
                                case SERVICO_SALA:
                                    System.out.println("(SERVICO_SALA)");
                                    servicoSALA(requisicao.getCorpo());
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

        if(verificaOla()) {
            servicoTCHAU();
        } else {
            cliente.disconectar();
        }

    }
    
    private void servicoTCHAU(){
        
        this.servidor.getGdc().enviarParaTodos(new Resposta(SERVICO_TCHAU, "" + this.cliente.getId()));
        
        cliente.disconectar();
        
    }

    private void servicoOLA(String apelido) throws IOException {

        if (this.servidor.getGdc().isApelidoDisponivel(apelido)) {
            this.cliente.setApelido(apelido);
            this.servidor.getGdc().enviarParaTodos(new Resposta(SERVICO_OLA, "" + this.cliente.getId()));
            System.out.println("Cliente " + this.cliente.getId() + ": Deu olá com o apelido " + apelido);
        } else {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_OLA));
        }

    }

    private void servicoMUDAR_APELIDO(String apelido) throws IOException {

        if (this.servidor.getGdc().isApelidoDisponivel(apelido)) {
            this.cliente.setApelido(apelido);
            System.out.println("Cliente " + this.cliente.getId() + ": Mudou o apelido para " + apelido);
            this.servidor.getGdc().enviarParaTodos(new Resposta(SERVICO_MUDAR_APELIDO, this.cliente.getId() + "###" + apelido));
        } else {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_MUDAR_APELIDO));
        }

    }

    private void servicoSOLICITAR_CLIENTES() throws IOException {

        List<Cliente> clientes = this.servidor.getGdc().getAll();

        String corpo = "";
        
        for(Cliente c : clientes){
            
            if (!corpo.equals("")) {
                corpo += "###";
            }
            
            corpo += c.getId() + "@@@" + c.getApelido() + "@@@" + c.getAvatarCamisa() + "@@@" + c.getAvatarCalca();
            
        }

        enviar(new Resposta(SERVICO_SOLICITAR_CLIENTES, corpo));

    }

    public void servicoMENSAGEM(String corpo) throws IOException {

        try {

            Mensagem nova = new Mensagem(corpo);

            if (nova.getId2() == 0) {
                // Mensagem global
                Mensagem global = new Mensagem(this.cliente.getId(), 0, nova.getMensagem());
                this.servidor.getGdc().enviarParaTodos(new Resposta(SERVICO_MENSAGEM, global.encode()));
            } else {
                // Mensagem privada
                Cliente c;
                if ((c = this.servidor.getGdc().getClienteById(nova.getId2())) != null) {
                    Mensagem privada = new Mensagem(this.cliente.getId(), nova.getId2(), nova.getMensagem());
                    if(nova.getId2() == this.cliente.getId()) {
                        enviar(new Resposta(SERVICO_MENSAGEM, privada.encode()));
                    } else {
                        enviar(new Resposta(SERVICO_MENSAGEM, privada.encode()));
                        c.getTratador().enviar(new Resposta(SERVICO_MENSAGEM, privada.encode()));
                    }
                } else {
                    enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_MENSAGEM));
                }
            }

        } catch (MensagemMalFormadaException ex) {
            System.out.println("Cliente " + this.cliente.getId() + ": MensagemMalFormadaException (" + ex.getMessage() + ").");
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_MENSAGEM));
        }

    }

    private void servicoAVATAR(String corpo) throws IOException {

        String[] avatar = corpo.split("###");

        try {

            int camisa = Integer.parseInt(avatar[0]);

            int calca = Integer.parseInt(avatar[1]);

            this.cliente.setAvatarCalca(calca);

            this.cliente.setAvatarCamisa(camisa);
            
            System.out.println("Cliente " + this.cliente.getId() + ": Escolheu o avatar [" + camisa + ", " + calca + "]");

        } catch (NumberFormatException ex) {

            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_AVATAR));

        }

    }

    private void servicoENTRAR_SALA(String corpo) throws IOException {

        try {

            int sala = Integer.parseInt(corpo);

            if (!this.servidor.getGds().adicionarClienteEmSala(sala, cliente)) {
                enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_ENTRAR_SALA));
            } else{
                System.out.println("Cliente " + this.cliente.getId() + ": Entrou na sala " + sala);
            }

        } catch (NumberFormatException ex) {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_ENTRAR_SALA));
        }

    }
    
    private void servicoSAIR_SALA(String corpo) throws IOException {

        try {

            int sala = Integer.parseInt(corpo);

            if (!this.servidor.getGds().removerClienteDaSala(sala, cliente)) {
                enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_SAIR_SALA));
            } else {
                System.out.println("Cliente " + this.cliente.getId() + ": Saiu da sala " + sala);
            }

        } catch (NumberFormatException ex) {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_SAIR_SALA));
        }

    }
    
    public void servicoSALA(String corpo) throws IOException {
        
        try {
            
            if(!this.servidor.getGds().repassarServicoASala(new RequisicaoSala(corpo))) {
                enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_SALA));
            }
        } catch (RequisicaoMalFormadaException ex) {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_SALA));
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
