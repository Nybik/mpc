package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;

public class HeldItemChangeEvent extends Packet {

    public byte slot;

    @Override
    public int getId() {
        return 0x3F;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
