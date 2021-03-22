package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class PlayerPositionAndLookPacket extends PlayStatePacket {

    public Double X;
    public Double Y;
    public Double Z;
    public Float yaw;
    public Float pitch;
    public Byte flags;
    public VarInt teleportId;

    @Override
    public int getId() {
        return 0x34;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
