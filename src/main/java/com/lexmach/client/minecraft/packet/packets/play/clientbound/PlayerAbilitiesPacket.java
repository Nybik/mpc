package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class PlayerAbilitiesPacket extends PlayStatePacket {

    public Byte flags;
    public Float flyingSpeed;
    public Float FOVmodifier;

    @Override
    public int getId() {
        return 0x30;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
