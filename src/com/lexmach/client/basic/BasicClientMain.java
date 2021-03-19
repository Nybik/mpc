package com.lexmach.client.basic;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicClientMain {

    private static Logger log = Logger.getLogger(BasicClientMain.class.getName());
    private Socket socket;
    private BasicClientServerReader messageReader;

    public BasicClientMain(String host, int port) throws IOException {
        log.info("Basic Client has started");
        log.info("Trying to connect to server");
        socket = new Socket(host, port);
        messageReader = new BasicClientServerReader(() -> socket);
        messageReader.start();
    }

    void sendMessage(String message) throws IOException {
        socket.getOutputStream().write(message.getBytes());
    }


    public static void main(String[] args) throws IOException {
        BasicClientMain client = new BasicClientMain("localhost", 3111);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.next();
            client.sendMessage(message);
        }
    }

}
