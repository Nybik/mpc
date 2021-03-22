package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class TagsPacket extends PlayStatePacket {

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

