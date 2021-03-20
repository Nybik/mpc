package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;

public class UpdateViewPositionPacket extends Packet {

    public VarInt chunkX;
    public VarInt chunkZ;

    @Override
    public int getId() {
        return 0x40;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
