package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;

public class PingPacket extends Packet {
    public long payload;

    public PingPacket() {
        payload = System.currentTimeMillis();
    }

    @Override
    public int getId() {
        return 0x01;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}