package servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeClientes {

    private final List<Cliente> clientes;
    private final Servidor servidor;

    public GerenciadorDeClientes(Servidor servidor) {
        this.servidor = servidor;
        this.clientes = new ArrayList<>();
    }

    public void adicionaCliente(Socket socket) {
        Cliente novo = new Cliente(socket, this);
        this.clientes.add(novo);
        System.out.println("Nova conexão com o cliente " + novo.getId());
    }

    public boolean removeCliente(int id) {

        for (Cliente c : clientes) {
            if (c.getId() == id) {
                clientes.remove(c);
                return true;
            }
        }

        return false;

    }

    public List<Integer> getAllIds() {

        List<Integer> ids = new ArrayList<>();

        for (Cliente c : clientes) {
            if (c.getTratador().verificaOla()) {
                ids.add(c.getId());
            }
        }

        return ids;

    }
    
    public Cliente getClienteById(int id){
        for(Cliente c : clientes){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public void mandarParaTodos(Resposta r) {
        for (Cliente c : clientes) {
            try {
                if (c.getTratador().verificaOla()) {
                    c.getTratador().enviar(r);
                }
            } catch (IOException ex) {
                System.out.println("IOException lançada pelo tratador do cliente " + c.getId());
                c.disconectar();
            }
        }
    }

    public boolean isApelidoDisponivel(String apelido) {
        for (Cliente cliente : clientes) {
            if (cliente.getApelido() != null && cliente.getApelido().equals(apelido)) {
                return false;
            }
        }
        return true;
    }

}
