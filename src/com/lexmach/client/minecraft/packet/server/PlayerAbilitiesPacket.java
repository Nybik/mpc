package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;

public class PlayerAbilitiesPacket extends Packet {

    public byte flags;
    public float flyingSpeed;
    public float FOVmodifier;

    @Override
    public int getId() {
        return 0x30;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
