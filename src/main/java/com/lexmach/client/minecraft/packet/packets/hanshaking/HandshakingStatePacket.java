package com.lexmach.client.minecraft.packet.packets.hanshaking;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public abstract class HandshakingStatePacket extends Packet {

    @Override
    public PlayerState getState() {
        return PlayerState.HANDSHAKING;
    }
}
