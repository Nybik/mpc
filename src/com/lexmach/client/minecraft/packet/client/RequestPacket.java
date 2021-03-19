package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;

public class RequestPacket extends Packet {
    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
