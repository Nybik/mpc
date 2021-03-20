package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.datatype.VarUUID;

public class LoginSuccessPacket extends Packet {

    public VarUUID id;
    public VarString username;

    @Override
    public int getId() {
        return 0x02;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
