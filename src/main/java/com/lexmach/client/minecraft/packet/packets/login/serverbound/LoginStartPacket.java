package com.lexmach.client.minecraft.packet.packets.login.serverbound;

import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.packets.login.LoginStatePacket;

public class LoginStartPacket extends LoginStatePacket {
    public VarString name;

    public LoginStartPacket() {}

    public LoginStartPacket(VarString varString) {
        name = varString;
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
