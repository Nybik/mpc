package com.lexmach.client.minecraft.packet.handler;

import com.lexmach.client.minecraft.fakeplayer.FakePlayer;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.exceptions.UnknownPackageException;
import com.lexmach.client.minecraft.packet.handler.events.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class PacketThreadHandler extends Thread {

    private static Logger log = Logger.getLogger(PacketThreadHandler.class.getName());

    private FakePlayer target;
    private InputStream in;
    private OutputStream out;

    private List<PacketEventListener> listeners = new ArrayList<>();

    public PacketThreadHandler() {

    }

    public void addListener(PacketEventListener listener) {
        listeners.add(listener);
    }
    public void removeListener(PacketEventListener listener) {
        listeners.remove(listener);
    }

    public PacketThreadHandler(FakePlayer player, InputStream in, OutputStream out) {
        this.target = player;
        this.in = in;
        this.out = out;
        this.setName("PacketThreadHandler of %s".formatted(player.getName()));
    }

    public void invokeSentPacketEvent(Packet sent) {
        for (PacketEventListener listener : listeners) {
            listener.onPacketSent(new PacketSentEvent(target, sent));
        }
    }
    public void invokeReceivedPacketEvent(Packet sent) {
        for (PacketEventListener listener : listeners) {
            listener.onPacketReceived(new PacketReceivedEvent(target, sent));
        }
    }

    public synchronized void sendPacket(Packet packet) throws Exception {
        byte[] packetPrepared = target.getCompressionHandler().prepare(packet);
        System.out.println("packetPrepared = " + Arrays.toString(packetPrepared));
        System.out.println("packetPrepared = " + Arrays.toString(packet.getData()));
        System.out.println("packetPrepared = " + Arrays.toString(packet.prepare()));

        out.write(new VarInt(packetPrepared.length).toBytes());
        out.write(packetPrepared);

        out.flush();
    }

    @Override
    public void run() {
        while (target.isAlive()) {
            try {
                if (in.available() == 0) {
                    //TODO rework
                    sleep(50);
                    continue;
                }
                System.out.println("DATA");
                Packet received = PacketUtil.readPacket(target.getCompressionHandler().readPacketData(in), target.getState());

                target.setState(received.changeState(target.getState()));

                invokeReceivedPacketEvent(received);
            } catch (Exception ex) {
                if (ex instanceof UnknownPackageException) {
                    System.out.println("ex.getMessage() = " + ex.getMessage());
                    continue;
                }
                ex.printStackTrace();
                this.stop();
            }
        }
    }

    public void setPlayer(FakePlayer player) {
        this.target = player;
    }

    public void setInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    public void setOutputStream(OutputStream out) {
        this.out = out;
    }
}
