package server.runServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private static final int PORT = 10000;

    public static void main(String[] args) {
        System.out.println("\nStarting server...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    System.out.println("Client connected: " + client.getInetAddress());
                    ClientHandler handler = new ClientHandler(client);
                    handler.start();
                } catch (IOException e) {
                    System.err.println("Failed to accept client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server on port " + PORT + ": " + e.getMessage());
        }
    }
}
