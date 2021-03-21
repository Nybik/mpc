package com.lexmach.client.minecraft;

import com.lexmach.client.basic.BasicClientMain;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.client.HandshakePacket;
import com.lexmach.client.minecraft.packet.client.LoginStartPacket;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.handler.events.EventRegisterThread;
import com.lexmach.client.minecraft.packet.handler.events.PacketEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class FakePlayer {
    private static Logger log = Logger.getLogger(BasicClientMain.class.getName());

    private Socket connection;
    private PlayerState state;

    private String name;

    private String ip;
    private int port;
    private EventRegisterThread eventThread = new EventRegisterThread();

    public void sendPacket(Packet packet) throws Exception {
        if (!packet.isServerBound()) throw new RuntimeException("Sent packet is not server bound");
        OutputStream out = connection.getOutputStream();

        VarInt id = new VarInt(packet.getId());
        byte[] data = packet.getData();
        VarInt length = new VarInt(id.toBytes().length + data.length);

        out.write(length.toBytes());
        out.write(id.toBytes());
        out.write(data);

        out.flush();
        eventThread.invokeSentPacketEvent(packet);
        state = packet.changeState(state);
    }

    public FakePlayer(String name, String ip, int port) throws Exception {
        this.setName(name);
        this.setIp(ip);
        this.setPort(port);
    }

    public void connect() throws Exception {
        if (connection != null) throw new IllegalStateException("Player is already connected");

        log.info("Fake Player \"%s\" is joining server %s:%d".formatted(name, ip, port));

        connection = new Socket(ip, port);
        connection.setTcpNoDelay(true);
        state = PlayerState.HANDSHAKING;
        eventThread.setPlayer(this);
        eventThread.setInputStream(new BufferedInputStream(connection.getInputStream()));
        eventThread.setName("EventRegisterThread of %s".formatted(this.name));
        eventThread.start();
        this.sendPacket(new HandshakePacket(new VarInt(754), new VarString(ip), (short) port, new VarInt(2)));
        this.sendPacket(new LoginStartPacket(new VarString(this.getName())));

    }

    public void addListener(PacketEventListener listener) {
        eventThread.addListener(listener);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void kill() throws IOException {
        eventThread.interrupt();
        connection.close();
    }

    public boolean isAlive() {
        return !connection.isClosed();
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
