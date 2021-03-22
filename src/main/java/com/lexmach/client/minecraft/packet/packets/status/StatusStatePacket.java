package com.lexmach.client.minecraft.packet.packets.status;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public abstract class StatusStatePacket extends Packet {

    @Override
    public PlayerState getState() {
        return PlayerState.STATUS;
    }
}
