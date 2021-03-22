package com.lexmach.client.minecraft.packet.packets.status.serverbound;

import com.lexmach.client.minecraft.packet.packets.status.StatusStatePacket;

public class PingPacket extends StatusStatePacket {
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
