package cliente.jogador;

import cliente.Cliente;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import cliente.protocolos.Requisicao;

public class GerenciadorDeJogadores extends Thread {

    private final Cliente cliente;
    private final List<Jogador> jogadores;

    public GerenciadorDeJogadores(Cliente cliente) {
        this.cliente = cliente;
        this.jogadores = new ArrayList<>();
    }

    @Override
    public void run() {

        while (true) {

            try {
                requisitarJogadores();
                sleep(1000);
            } catch (IOException ex) {
                System.out.println("GDJ: IOException");
            } catch (InterruptedException ex) {
                System.out.println("GDJ: InterruptedException");
            }

        }

    }

    public void requisitarJogadores() throws IOException {

        this.cliente.enviar(new Requisicao(Cliente.SERVICO_SOLICITAR_JOGADORES, ""));

    }

    public void atualizarListaJogadores(String corpo) {

        List<Jogador> nova = criaLista(corpo);

        System.out.println("GDJ: atualizando Lista de Jogadores");
        System.out.println("Lista antiga: " + this.jogadores);
        System.out.println("Lista recebida: " + nova);
        
        for(int i = jogadores.size()-1; i >= 0; i--) {
            
            Jogador j = jogadores.get(i);
            
            if (contem(nova, j) == null) {
                remover(j);
            }
            
        }

        for (Jogador j : nova) {
            if (!adicionar(j)) {
                alterar(j);
            }
        }

        System.out.println("Nova lista: " + this.jogadores);
        
        this.cliente.getJanela().atualizaTabelaJogadores(jogadores);

    }

    private List<Jogador> criaLista(String corpo) {

        List<Jogador> nova = new ArrayList<>();

        String[] jogs = corpo.split("###");

        for (String jog : jogs) {

            if (jog.contains("@@@")) {

                String[] partes = jog.split("@@@");

                try {

                    int id = Integer.parseInt(partes[0]);
                    String apelido = partes[1];
                    int avatarCamisa = Integer.parseInt(partes[2]);
                    int avatarCalca = Integer.parseInt(partes[3]);

                    Jogador jogador = new Jogador(id, apelido, avatarCamisa, avatarCalca);

                    nova.add(jogador);

                } catch (NumberFormatException ex) {

                }

            }

        }

        return nova;

    }

    public boolean adicionar(Jogador jogador) {

        if (contem(jogadores, jogador) != null) {
            return false;
        }

        this.jogadores.add(jogador);

        return true;

    }

    public boolean remover(Jogador jogador) {

        for (Jogador j : jogadores) {
            if (j.getId() == jogador.getId()) {
                this.jogadores.remove(j);
                return true;
            }
        }

        return false;

    }

    public boolean alterar(Jogador jogador) {

        for (Jogador j : jogadores) {

            if (j.getId() == jogador.getId()) {
                j.setApelido(jogador.getApelido());
                j.setAvatarCalca(jogador.getAvatarCalca());
                j.setAvatarCamisa(jogador.getAvatarCamisa());
                return true;
            }

        }

        return false;
    }

    public Jogador contem(List<Jogador> lista, Jogador jogador) {

        for (Jogador j : lista) {

            if (j.getId() == jogador.getId()) {
                return j;
            }

        }

        return null;

    }

}
