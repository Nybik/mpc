package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.*;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;

public class TagsPacket extends Packet {

    public Tag[] blockTags;
    public Tag[] itemTags;
    public Tag[] fluidTags;
    public Tag[] entityTags;

//    public byte[] data;
//
//    public void specialRead(InputStream in, VarInt packageSize) throws Exception {
//        int dataSize = packageSize.num - new VarInt(getId()).toBytes().length;
//        data = new byte[dataSize];
//        PacketUtil.readFully(in, data);
//    }

    @Override
    public int getId() {
        return 0x5B;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }

    public static class Tag implements MinecraftCustom {
        public VarIdentifier tagName;
        public VarInt[] entries;
    }
}

