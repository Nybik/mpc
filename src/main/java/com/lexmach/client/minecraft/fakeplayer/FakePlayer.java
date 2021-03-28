package com.lexmach.client.minecraft.fakeplayer;

import com.lexmach.client.minecraft.fakeplayer.handler.FakePlayerEventHandler;
import com.lexmach.client.minecraft.fakeplayer.handler.FakePlayerPositionHandler;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.handler.CompressionHandler;
import com.lexmach.client.minecraft.packet.handler.PacketThreadHandler;
import com.lexmach.client.minecraft.packet.handler.events.PacketEventListener;
import com.lexmach.client.minecraft.packet.packets.hanshaking.serverbound.HandshakePacket;
import com.lexmach.client.minecraft.packet.packets.login.serverbound.LoginStartPacket;
import com.lexmach.client.minecraft.packet.packets.play.serverbound.ClientChatMessagePacket;
import com.lexmach.client.minecraft.packet.util.PlayerState;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class FakePlayer {
    private static final Logger log = Logger.getLogger(FakePlayer.class.getName());

    private Socket connection;
    private PlayerState state;

    private String name;

    private String ip;
    private int port;

    private final PacketThreadHandler packetThreadHandler = new PacketThreadHandler();
    private final CompressionHandler compressionHandler = new CompressionHandler();
    private final FakePlayerEventHandler playerEventHandler = new FakePlayerEventHandler();
    private final FakePlayerPositionHandler locationHandler = new FakePlayerPositionHandler();

    public synchronized void sendPacket(Packet packet) throws Exception {
        if (!packet.isServerBound()) throw new RuntimeException("Sent packet is not server bound");
        packetThreadHandler.sendPacket(packet);

        packetThreadHandler.invokeSentPacketEvent(packet);
        state = packet.changeState(state);
    }

    public FakePlayer(String name, String ip, int port) {
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

        playerEventHandler.setPlayer(this);
        addListener(playerEventHandler);

        packetThreadHandler.setPlayer(this);
        packetThreadHandler.setOutputStream(connection.getOutputStream());
        packetThreadHandler.setInputStream(new BufferedInputStream(connection.getInputStream()));
        packetThreadHandler.setName("EventRegisterThread of %s".formatted(this.name));
        packetThreadHandler.start();

        this.sendPacket(new HandshakePacket(new VarInt(754), new VarString(ip), (short) port, new VarInt(2)));
        this.sendPacket(new LoginStartPacket(new VarString(this.getName())));

    }

    public void addListener(PacketEventListener listener) {
        packetThreadHandler.addListener(listener);
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
        packetThreadHandler.interrupt();
        connection.close();
    }

    public synchronized boolean isAlive() {
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

    public CompressionHandler getCompressionHandler() {
        return compressionHandler;
    }

    public void sendMessage(String s) throws Exception {
        this.sendPacket(new ClientChatMessagePacket(s));
    }

    public synchronized FakePlayerPositionHandler getPositionHandler() {
        return locationHandler;
    }
}
