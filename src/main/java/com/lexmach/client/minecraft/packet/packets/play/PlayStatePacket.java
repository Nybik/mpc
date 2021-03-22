package com.lexmach.client.minecraft.packet.packets.play;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public abstract class PlayStatePacket extends Packet {

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }
}
