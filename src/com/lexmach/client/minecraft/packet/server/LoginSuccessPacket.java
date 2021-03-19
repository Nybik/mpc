package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;

import java.util.UUID;

public class LoginSuccessPacket extends Packet {

    public UUID id;
    public String username;

    @Override
    public int getId() {
        return 0x02;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
