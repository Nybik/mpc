package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;

public class TeleportConfirmPacket extends Packet {
    public VarInt id;

    public TeleportConfirmPacket(VarInt id) {
        this.id = id;
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
