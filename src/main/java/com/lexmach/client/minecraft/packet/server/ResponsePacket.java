package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarString;

public class ResponsePacket extends Packet {

    public VarString jsonResponse;

    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
