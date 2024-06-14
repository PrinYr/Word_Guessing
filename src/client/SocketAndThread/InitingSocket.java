package client.SocketAndThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class InitingSocket {
    
    private Socket socket;
    private final int PORT = 10000;
    private String HOST = null;

    public InitingSocket() throws IOException {
        System.out.println("Press Enter button to start game.");
        Scanner ready = new Scanner(System.in);
        HOST = ready.nextLine();
        if (HOST == null) {
            HOST = InetAddress.getLocalHost().toString();
        }
        socket = new Socket(HOST, PORT);
    }

    public Socket getSocket() {
        return this.socket;
    }

}



