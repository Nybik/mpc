package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class EntityStatusPacket extends PlayStatePacket {

    public Integer entityId;
    public Byte entityStatus;

    @Override
    public int getId() {
        return 0x1A;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
