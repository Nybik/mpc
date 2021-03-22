package com.lexmach.client.minecraft.packet.packets.login.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.login.LoginStatePacket;

public class SetCompressionPacket extends LoginStatePacket {

    public VarInt threshold;

    @Override
    public int getId() {
        return 0x03;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
