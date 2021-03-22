package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class ClientChatMessagePacket extends Packet {
    public VarString chat;

    public ClientChatMessagePacket() {}

    public ClientChatMessagePacket(String msg) {
        chat = new VarString(msg);
    }

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x03;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
