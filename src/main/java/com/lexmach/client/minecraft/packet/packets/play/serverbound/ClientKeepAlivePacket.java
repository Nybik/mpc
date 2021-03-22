package com.lexmach.client.minecraft.packet.packets.play.serverbound;

import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class ClientKeepAlivePacket extends PlayStatePacket {
    public Long keepAliveId;

    public ClientKeepAlivePacket(Long keepAlive) {
        this.keepAliveId = keepAlive;
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
