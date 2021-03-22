package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class HeldItemChangeEvent extends PlayStatePacket {

    public Byte slot;

    @Override
    public int getId() {
        return 0x3F;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
