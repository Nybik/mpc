package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class ServerKeepAlivePacket extends PlayStatePacket {
    public Long keepAliveId;

    @Override
    public int getId() {
        return 0x1F;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
