package com.lexmach.client.minecraft.packet.handler;

import com.lexmach.client.basic.BasicClientMain;
import com.lexmach.client.minecraft.FakePlayer;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.exceptions.UnknownPackageException;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;
import com.lexmach.client.minecraft.packet.server.ResponsePacket;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EventRegisterThread extends Thread {

    private static Logger log = Logger.getLogger(EventRegisterThread.class.getName());

    private FakePlayer target;
    private InputStream in;

    private List<PacketEventListener> listeners = new ArrayList<>();

    public EventRegisterThread() {

    }

    public void addListener(PacketEventListener listener) {
        listeners.add(listener);
    }
    public void removeListener(PacketEventListener listener) {
        listeners.remove(listener);
    }

    public EventRegisterThread(FakePlayer player, InputStream in) {
        this.target = player;
        this.in = in;
        this.setName("EventRegisterThread of %s".formatted(player.getName()));
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

    @Override
    public void run() {
        while (target.isAlive()) {
            try {
                if (in.available() == 0) {
                    Thread.yield();
                    continue;
                };
                Packet received = PacketUtil.readPacket(in, target.getState());
                target.setState(received.changeState(target.getState()));
                invokeReceivedPacketEvent(received);
            } catch (Exception ex) {
                if (ex instanceof UnknownPackageException) {
//                    System.out.println("ex.getMessage() = " + ex.getMessage());
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
}
