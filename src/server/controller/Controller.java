package server.controller;

import java.io.IOException;

import server.Reader.fileReader;

public class Controller {

    private final fileReader read;

    public Controller() {
        this.read = new fileReader();
    }

    public String getWord() {
        try {
            return read.readFile();
        } catch (IOException e) {
           return e.getMessage();
        }
    }
}
