package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarInt;

public class SetCompressionPacket extends Packet {

    public VarInt threshold;

    @Override
    public PlayerState getState() {
        return PlayerState.LOGIN;
    }

    @Override
    public int getId() {
        return 0x03;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
