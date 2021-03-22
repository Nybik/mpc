package com.lexmach.client.minecraft.packet.packets.status.serverbound;

import com.lexmach.client.minecraft.packet.packets.status.StatusStatePacket;

public class RequestPacket extends StatusStatePacket {

    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
