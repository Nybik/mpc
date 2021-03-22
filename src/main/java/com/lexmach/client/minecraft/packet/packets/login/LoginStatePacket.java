package com.lexmach.client.minecraft.packet.packets.login;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public abstract class LoginStatePacket extends Packet {

    @Override
    public PlayerState getState() {
        return PlayerState.LOGIN;
    }
}
