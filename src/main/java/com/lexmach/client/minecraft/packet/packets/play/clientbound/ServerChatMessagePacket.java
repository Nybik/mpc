package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.datatype.VarUUID;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class ServerChatMessagePacket extends Packet {
    public VarString chat;
    public Byte position;
    public VarUUID uuid;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x0E;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
