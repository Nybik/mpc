package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class ClientKeepAlivePacket extends Packet {
    public Long keepAliveId;

    public ClientKeepAlivePacket(Long keepAlive) {
        this.keepAliveId = keepAlive;
    }

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x10;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
