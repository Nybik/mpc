package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarString;

public class HandshakePacket extends Packet {

    public VarInt protocolVersion;
    public VarString serverAddress;
    public short serverPort; // actually short
    public VarInt state; // 1 - status, 2 - login

    public HandshakePacket(VarInt protocolVersion, VarString serverAddress, short serverPort, VarInt state) {
        this.protocolVersion = protocolVersion;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.state = state;
    }

    public HandshakePacket() {

    }


    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
