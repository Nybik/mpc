package com.lexmach.client.minecraft.packet.packets.status.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.packets.status.StatusStatePacket;

public class ResponsePacket extends StatusStatePacket {

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
