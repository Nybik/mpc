package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;

public class PlayerAbilitiesPacket extends Packet {

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
