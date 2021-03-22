package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class ServerDifficultyPacket extends PlayStatePacket {

    public Byte difficulty;
    public Boolean isLocked;

    @Override
    public int getId() {
        return 0x0D;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
