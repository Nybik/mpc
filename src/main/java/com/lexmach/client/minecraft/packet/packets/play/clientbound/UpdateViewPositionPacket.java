package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class UpdateViewPositionPacket extends PlayStatePacket {

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
