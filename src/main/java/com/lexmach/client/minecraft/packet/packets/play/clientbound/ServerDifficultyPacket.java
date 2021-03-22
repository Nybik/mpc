package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class ServerDifficultyPacket extends Packet {

    public Byte difficulty;
    public Boolean isLocked;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x0D;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
