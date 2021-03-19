package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarString;

public class EncryptionRequestPacket extends Packet {

    public VarString serverId;
    public byte[] publicKey;
    public byte[] verifyToken;

    @Override
    public int getId() {
        return 0x01;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}