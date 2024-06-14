package client.controller;

import java.io.IOException;

public class ClientMain {
    private static Controller controller;
    public static void main(String[] args) throws IOException{
        controller = new Controller();
        controller.StartThread();
    }
}
