package server.runServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import server.controller.Controller;

public class ClientHandler extends Thread {

    private final Socket client;
    private final Scanner input;
    private final PrintWriter output;
    private final Controller control;

    public ClientHandler(Socket socket) throws IOException {
        this.client = socket;
        this.input = new Scanner(client.getInputStream());
        this.output = new PrintWriter(client.getOutputStream(), true);
        this.control = new Controller();
    }

    @Override
    public void run() {
        int score = 0;
        boolean playing = true;
        try {
            while (playing) {
                String word = control.getWord();
                if (word == null) {
                    output.println("Error retrieving the word. Terminating the game.");
                    break;
                }
                word = word.toLowerCase();
                int totalTries = word.length();
                char[] guessWord = word.toCharArray(); 
                char[] playerGuess = new char[totalTries];
    
                for (int i = 0; i < playerGuess.length; i++) {
                    playerGuess[i] = '_';
                }
    
                boolean isWordGuessed = false;
                int tries = 0;
    
                output.println("\nWelcome to guess the word game!\n");
                output.printf("Your total score is: %d\n", score);
                output.printf("The word has %d letters.\n", totalTries);
                output.println("Try to guess!");
    
                while (!isWordGuessed && tries < totalTries && playing) {
                    output.print("\nWord: ");
                    printArray(playerGuess);
                    output.printf("\nYou have %d chances left.\n", totalTries - tries); 
                    output.println("Enter a letter or word! ('-' to quit)");
    
                    String guessedWord = input.nextLine().toLowerCase(); 
                    if (guessedWord.isEmpty()) {
                        output.println("You have to enter something!");
                        continue;
                    }
    
                    char letter = guessedWord.charAt(0); 
    
                    if (letter == '-') {
                        playing = false;
                        break;
                    }
    
                    tries++; 
                    if (guessedWord.length() == 1) { 
                        for (int i = 0; i < guessWord.length; i++) { 
                            if (guessWord[i] == letter) { 
                                playerGuess[i] = letter;
                            }
                        }
                    } else if (word.equals(guessedWord)) {
                        isWordGuessed = true;
                    }
    
                    if (isWordGuessed(playerGuess) || word.equals(guessedWord)) { 
                        isWordGuessed = true;
                        output.println("Hooray Hooray, you won!");
                        score++;
                    }
                }
    
                if (!isWordGuessed) { 
                    output.print("Your final state was: ");
                    printArray(playerGuess);
                    output.println("\nThe word was: " + word);
                }
    
                if(playing){
                    
                    output.println("Do you want to play another game? (yes/no)");
                    String response = input.nextLine().toLowerCase();
                    if (response.equals("no") || response.equals("-")) {
                        playing = false;
                        System.out.println("Client " + client.toString() + " ended the connection.");
                    }
                }
            }
            
        } catch (Exception e) {
            System.err.println("Client disconnected");
        }

        closeClientConnection();
    }

    private void printArray(char[] array) {
        for (char c : array) {
            output.print(c + " ");
        }
        output.println();
    }

    private boolean isWordGuessed(char[] array) {
        for (char c : array) {
            if (c == '_') {     
                return false;
            }
        }
        return true;
    }

    private void closeClientConnection() {
        try {
            client.close();
        } catch (IOException e) {
            System.err.println("Unable to disconnect client: " + e.getMessage());
        }
    }
}
