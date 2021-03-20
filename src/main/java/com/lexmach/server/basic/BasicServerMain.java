package com.lexmach.server.basic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class BasicServerMain {

    private static final Logger log = Logger.getLogger(BasicServerMain.class.getName());
    private ServerSocket socket;
    private List<Socket> connections = new ArrayList<>();
    private List<BasicServerClientReader> messageReaders = new ArrayList<>();

    public BasicServerMain(int port, int maxConnections) throws IOException {
        log.info("Basic Client has started");
        log.info("Trying to connect to server");
        socket = new ServerSocket(port, maxConnections);
        for (int i = 0; i < maxConnections; ++i) {
            Socket connectionSocket = socket.accept();
            connections.add(connectionSocket);
            messageReaders.add(new BasicServerClientReader(connectionSocket));
            messageReaders.get(i).start();
        }
    }

    void sendMessage(int connection, String message) throws IOException {
        connections.get(connection).getOutputStream().write(message.getBytes());
    }

    public static void main(String[] args) throws IOException {
        BasicServerMain server = new BasicServerMain(3111, 1);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.next();
            server.sendMessage(0, message);
        }
    }
}
