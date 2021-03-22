package com.lexmach.server.basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class BasicServerClientReader extends Thread {
    private Socket socket;

    BasicServerClientReader(Socket socket) {
        this.socket = socket;
        setDaemon(true);
        setName("ClientReader");
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int size = inputStream.available();
                if (size == 0) {
                    Thread.yield();
                    continue;
                }
                byte[] byteMessage = new byte[size];
                inputStream.read(byteMessage);
                System.out.print(new String(byteMessage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
