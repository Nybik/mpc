package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class ServerKeepAlivePacket extends Packet {
    public Long keepAliveId;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x1F;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
