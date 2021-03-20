package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class HeldItemChangeEvent extends Packet {

    public Byte slot;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x3F;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
