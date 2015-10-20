package cliente.sala;

import cliente.Cliente;
import cliente.protocolos.Requisicao;
import java.util.List;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

public class GerenciadorDeSalas extends Thread {

    private final Cliente cliente;
    private final List<Sala> salas;

    public GerenciadorDeSalas(Cliente cliente) {
        this.cliente = cliente;
        this.salas = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {

            try {
                requisitarSalas();
                sleep(1000);
            } catch (IOException ex) {
                System.out.println("GDS: IOException");
            } catch (InterruptedException ex) {
                System.out.println("GDS: InterruptedException");
            }

        }
    }

    private void requisitarSalas() throws IOException {

        cliente.enviar(new Requisicao(Cliente.SERVICO_SOLICITAR_SALAS, ""));

    }

    public void atualizarListaSalas(String corpo) {

        System.out.println("GDS: atualizando lista de salas");

        List<Sala> nova = criaLista(corpo);

        System.out.println("Lista antiga: " + this.salas);
        System.out.println("Lista recebida: " + nova);

        for (int i = salas.size() - 1; i >= 0; i--) {

            Sala s = salas.get(i);

            if (contem(nova, s) == null) {
                remover(s);
            }

        }

        for (Sala s : nova) {
            if (!adicionar(s)) {
                alterar(s);
            }
        }

        System.out.println("Nova lista: " + this.salas);

        this.cliente.getJanela().atualizaTabelaSalas(salas);

    }

    private List<Sala> criaLista(String corpo) {

        List<Sala> nova = new ArrayList<>();

        String[] salas_corpo = corpo.split("###");

        for (String sala_corpo : salas_corpo) {

            if (sala_corpo.contains("@@@")) {

                String[] partes = sala_corpo.split("@@@");

                try {

                    int id = Integer.parseInt(partes[0]);
                    String mapa = partes[1];
                    String tipo = partes[2];
                    int jogadores = Integer.parseInt(partes[3]);
                    int maxJogadores = Integer.parseInt(partes[4]);

                    Sala sala = new Sala(id, mapa, tipo, jogadores, maxJogadores);

                    nova.add(sala);

                } catch (NumberFormatException ex) {

                }

            }

        }

        return nova;

    }

    public boolean adicionar(Sala sala) {

        if (contem(salas, sala) != null) {
            return false;
        }

        this.salas.add(sala);

        return true;

    }

    public boolean remover(Sala sala) {

        for (Sala j : salas) {
            if (j.getId() == sala.getId()) {
                this.salas.remove(j);
                return true;
            }
        }

        return false;

    }

    public boolean alterar(Sala sala) {

        for (Sala j : salas) {

            if (j.getId() == sala.getId()) {
                j.setJogadores(sala.getJogadores());
                j.setMapa(sala.getMapa());
                j.setMaxJogadores(sala.getMaxJogadores());
                j.setTipo(sala.getTipo());
                return true;
            }

        }

        return false;
    }

    public Sala contem(List<Sala> lista, Sala sala) {

        for (Sala j : lista) {

            if (j.getId() == sala.getId()) {
                return j;
            }

        }

        return null;

    }

}
