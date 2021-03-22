package com.lexmach.client.minecraft.packet.packets.login.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.packets.login.LoginStatePacket;

public class EncryptionRequestPacket extends LoginStatePacket {

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
