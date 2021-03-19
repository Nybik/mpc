package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;

public class EntityStatusPacket extends Packet {

    public int entityId;
    public byte entityStatus;

    @Override
    public int getId() {
        return 0x1A;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
