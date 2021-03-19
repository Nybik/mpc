package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.IOException;
import java.io.InputStream;

//TODO
public class DeclareRecipesPacket extends Packet {

    public byte[] arr;

    public void specialRead(InputStream in, VarInt packageSize) throws IOException {
        arr = new byte[packageSize.num - new VarInt(getId()).toBytes().length];
        PacketUtil.readFully(in, arr);
    }

    @Override
    public int getId() {
        return 0x5A;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}