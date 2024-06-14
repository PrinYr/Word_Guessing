package client.SocketAndThread;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RecivingThread extends Thread {
    private final Scanner input;

    public RecivingThread(Socket socket) throws IOException {
        input = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(input.nextLine());
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Connection Closed.");
                System.exit(0);
            }
        }
    }
}
