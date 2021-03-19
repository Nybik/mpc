package com.lexmach.client.basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.function.Supplier;

public class BasicClientServerReader extends Thread {

    private final Supplier<Socket> socketGetter;

    public BasicClientServerReader(Supplier<Socket> socket) {
        this.socketGetter = socket;
//        setDaemon(true);
        setName("ServerReader");
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socketGetter.get().getInputStream();
//            while (true) {
//                System.out.println((char) inputStream.read());
//                System.out.println(":)");
//            }
            while (!socketGetter.get().isClosed()) {
                int size = inputStream.available();
                if (size == 0) {
                    Thread.yield();
                    continue;
                }
                System.out.println("MSG");
                byte[] byteMessage = new byte[size];
                inputStream.read(byteMessage);
                System.out.print(new String(byteMessage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
