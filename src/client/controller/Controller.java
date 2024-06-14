package client.controller;

import java.io.IOException;
import java.net.Socket;

import client.SocketAndThread.InitingSocket;
import client.SocketAndThread.RecivingThread;
import client.SocketAndThread.SendingThread;

public final class Controller {

    private InitingSocket initSocket;
    private RecivingThread inputThread;
    private SendingThread outputThread;
    private final Socket clientSocket;

    public Controller() throws IOException {
        
        initSocket = new InitingSocket();
        clientSocket = initSocket.getSocket();
        outputThread = new SendingThread(clientSocket);
        inputThread = new RecivingThread(clientSocket);
    
    }
    
    public void StartThread() throws IOException {
        outputThread.start();
        inputThread.start();
    }
}