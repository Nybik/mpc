package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.IOException;
import java.io.InputStream;

public class PlayerPositionAndLookPacket extends Packet {

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
