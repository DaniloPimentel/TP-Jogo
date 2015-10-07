/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author strudel
 */
public class Recebedor extends Thread {

    Socket socket;

    public Recebedor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Scanner s = new Scanner(socket.getInputStream());
                if (s.hasNextLine()) {
                    System.out.println("-- Recebido: " + s.nextLine() + "--");
                }
            } catch (IOException ex) {
                System.out.println("IOException");
            }
        }
    }
}
