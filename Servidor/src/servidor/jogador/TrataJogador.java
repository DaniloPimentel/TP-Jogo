package servidor.jogador;

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

public class TrataJogador extends Thread {

    public static final int SERVICO_TCHAU = 0;
    public static final int SERVICO_OLA = 1;
    public static final int SERVICO_MUDAR_APELIDO = 2;
    public static final int SERVICO_SOLICITAR_JOGADORES = 3;
    public static final int SERVICO_MENSAGEM = 4;
    public static final int SERVICO_AVATAR = 5;
    public static final int SERVICO_ENTRAR_SALA = 6;
    public static final int SERVICO_SAIR_SALA = 7;
    public static final int SERVICO_SALA = 8;
    public static final int SERVICO_NEGADO = 5000;

    private final Jogador jogador;
    private final Servidor servidor;

    public TrataJogador(Jogador jogador, Servidor servidor) {
        this.jogador = jogador;
        this.servidor = servidor;
    }

    @Override
    public void run() {

        try {
            Scanner s = new Scanner(this.jogador.getSocket().getInputStream());
            while (s.hasNextLine()) {
                try {
                    Requisicao requisicao = new Requisicao(s.nextLine());

                    System.out.print("Jogador " + this.jogador.getId() + ": Nova requisicao ");

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
                                case SERVICO_SOLICITAR_JOGADORES:
                                    System.out.println("(SERVICO_SOLICITAR_JOGADORES)");
                                    servicoSOLICITAR_JOGADORES();
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
                    System.out.println("Jogador " + this.jogador.getId() + ": RequisicaoMalFormadaException (" + ex.getMessage() + ").");
                }
            }
        } catch (IOException ex) {
            System.out.println("IOException lançada pelo tratador do jogador " + this.jogador.getId());
        }

        if(verificaOla()) {
            servicoTCHAU();
        } else {
            jogador.disconectar();
        }

    }
    
    private void servicoTCHAU(){
        
        this.servidor.getGdj().enviarParaTodos(new Resposta(SERVICO_TCHAU, "" + this.jogador.getId()));
        
        jogador.disconectar();
        
    }

    private void servicoOLA(String apelido) throws IOException {

        if (this.servidor.getGdj().isApelidoDisponivel(apelido)) {
            this.jogador.setApelido(apelido);
            this.servidor.getGdj().enviarParaTodos(new Resposta(SERVICO_OLA, "" + this.jogador.getId()));
            System.out.println("Jogador " + this.jogador.getId() + ": Deu olá com o apelido " + apelido);
        } else {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_OLA));
        }

    }

    private void servicoMUDAR_APELIDO(String apelido) throws IOException {

        if (this.servidor.getGdj().isApelidoDisponivel(apelido)) {
            this.jogador.setApelido(apelido);
            System.out.println("Jogador " + this.jogador.getId() + ": Mudou o apelido para " + apelido);
            this.servidor.getGdj().enviarParaTodos(new Resposta(SERVICO_MUDAR_APELIDO, this.jogador.getId() + "###" + apelido));
        } else {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_MUDAR_APELIDO));
        }

    }

    private void servicoSOLICITAR_JOGADORES() throws IOException {

        List<Jogador> jogadores = this.servidor.getGdj().getAll();

        String corpo = "";
        
        for(Jogador c : jogadores){
            
            if (!corpo.equals("")) {
                corpo += "###";
            }
            
            corpo += c.getId() + "@@@" + c.getApelido() + "@@@" + c.getAvatarCamisa() + "@@@" + c.getAvatarCalca();
            
        }

        enviar(new Resposta(SERVICO_SOLICITAR_JOGADORES, corpo));

    }

    public void servicoMENSAGEM(String corpo) throws IOException {

        try {

            Mensagem nova = new Mensagem(corpo);

            if (nova.getId2() == 0) {
                // Mensagem global
                Mensagem global = new Mensagem(this.jogador.getId(), 0, nova.getMensagem());
                this.servidor.getGdj().enviarParaTodos(new Resposta(SERVICO_MENSAGEM, global.encode()));
            } else {
                // Mensagem privada
                Jogador c;
                if ((c = this.servidor.getGdj().getJogadorById(nova.getId2())) != null) {
                    Mensagem privada = new Mensagem(this.jogador.getId(), nova.getId2(), nova.getMensagem());
                    if(nova.getId2() == this.jogador.getId()) {
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
            System.out.println("Jogador " + this.jogador.getId() + ": MensagemMalFormadaException (" + ex.getMessage() + ").");
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_MENSAGEM));
        }

    }

    private void servicoAVATAR(String corpo) throws IOException {

        String[] avatar = corpo.split("###");

        try {

            int camisa = Integer.parseInt(avatar[0]);

            int calca = Integer.parseInt(avatar[1]);

            this.jogador.setAvatarCalca(calca);

            this.jogador.setAvatarCamisa(camisa);
            
            System.out.println("Jogador " + this.jogador.getId() + ": Escolheu o avatar [" + camisa + ", " + calca + "]");

        } catch (NumberFormatException ex) {

            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_AVATAR));

        }

    }

    private void servicoENTRAR_SALA(String corpo) throws IOException {

        try {

            int sala = Integer.parseInt(corpo);

            if (!this.servidor.getGds().adicionarJogadorEmSala(sala, jogador)) {
                enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_ENTRAR_SALA));
            } else{
                System.out.println("Jogador " + this.jogador.getId() + ": Entrou na sala " + sala);
            }

        } catch (NumberFormatException ex) {
            enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_ENTRAR_SALA));
        }

    }
    
    private void servicoSAIR_SALA(String corpo) throws IOException {

        try {

            int sala = Integer.parseInt(corpo);

            if (!this.servidor.getGds().removerJogadorDaSala(sala, jogador)) {
                enviar(new Resposta(SERVICO_NEGADO, "" + SERVICO_SAIR_SALA));
            } else {
                System.out.println("Jogador " + this.jogador.getId() + ": Saiu da sala " + sala);
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

        PrintStream ps = new PrintStream(this.jogador.getSocket().getOutputStream());
        ps.println(resposta.encode());

    }

    public boolean verificaOla() {
        return !(this.jogador.getApelido() == null);
    }

    public Jogador getJogador() {
        return jogador;
    }
}
