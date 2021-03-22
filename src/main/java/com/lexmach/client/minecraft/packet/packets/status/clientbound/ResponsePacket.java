package com.lexmach.client.minecraft.packet.packets.status.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarString;

public class ResponsePacket extends Packet {

    public VarString jsonResponse;

    @Override
    public PlayerState getState() {
        return PlayerState.STATUS;
    }

    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
