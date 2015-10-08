package teste;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Recebedor extends Thread {

    Socket socket;

    public Recebedor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            Scanner s = new Scanner(socket.getInputStream());

            while (s.hasNextLine()) {
                System.out.println("-- Recebido: " + s.nextLine() + " --");
            }

        } catch (IOException ex) {
            System.out.println("IOException");
        }

        System.out.println("Servidor desconectado.");
        
        System.exit(0);

    }
}
