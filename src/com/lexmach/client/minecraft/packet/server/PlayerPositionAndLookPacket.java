package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.IOException;
import java.io.InputStream;

public class PlayerPositionAndLookPacket extends Packet {

    public double X;
    public double Y;
    public double Z;
    public float yaw;
    public float pitch;
    public byte flags;
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
