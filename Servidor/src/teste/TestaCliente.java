package teste;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TestaCliente {

    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("", 12345);
        PrintStream ps = new PrintStream(cliente.getOutputStream());
        Recebedor r = new Recebedor(cliente);
        r.start();
        while(true){
            String msg = new Scanner(System.in).nextLine();
            ps.println(msg);
        }
    }

}
