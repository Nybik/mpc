package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarString;

public class LoginStartPacket extends Packet {
    public VarString name;

    public LoginStartPacket() {}

    public LoginStartPacket(VarString varString) {
        name = varString;
    }

    @Override
    public PlayerState getState() {
        return PlayerState.LOGIN;
    }

    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
