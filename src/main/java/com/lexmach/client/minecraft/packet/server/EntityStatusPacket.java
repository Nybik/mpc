package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class EntityStatusPacket extends Packet {

    public Integer entityId;
    public Byte entityStatus;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x1A;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
