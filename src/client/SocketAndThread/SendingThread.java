package client.SocketAndThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendingThread extends Thread{
    private final PrintWriter output;
    private final Scanner userEntry;

    public SendingThread(Socket socket) throws IOException {
        this.userEntry = new Scanner(System.in);
        output = new PrintWriter(socket.getOutputStream(), true);
    }
    
    @Override
    public void run() {
        String message;

        do {
            message = userEntry.nextLine();
            output.println(message);
        } while (!(message.equals("-") || message.equals("no")));

        System.out.println("\nClosing connection...");
        System.exit(0);
    }
}
