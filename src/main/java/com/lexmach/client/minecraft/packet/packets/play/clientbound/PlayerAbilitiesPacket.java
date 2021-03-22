package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class PlayerAbilitiesPacket extends Packet {

    public Byte flags;
    public Float flyingSpeed;
    public Float FOVmodifier;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x30;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
