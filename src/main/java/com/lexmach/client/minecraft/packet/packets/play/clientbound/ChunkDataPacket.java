package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarTag;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;

public class ChunkDataPacket extends PlayStatePacket {
    Integer chunkX = 0;
    Integer chunkZ = 0;
    Boolean fullChunk;
    VarInt primaryBit = new VarInt();
    VarTag heightMaps = new VarTag();
    VarInt[] biomes;
    byte[] data;
    VarTag[] blockEntities;


    public void specialRead(InputStream in, VarInt packageSize) throws Exception {
        chunkX = PacketUtil.getObjectFromStream(Integer.class, in);
        chunkZ = PacketUtil.getObjectFromStream(Integer.class, in);
        fullChunk = PacketUtil.getObjectFromStream(Boolean.class, in);
        primaryBit.fromStream(in);
        heightMaps.fromStream(in);
        if (fullChunk) {
            biomes = PacketUtil.getObjectFromStream(VarInt[].class, in);
        }
        data = PacketUtil.getObjectFromStream(byte[].class, in);
        blockEntities = PacketUtil.getObjectFromStream(VarTag[].class, in);
    }

    @Override
    public int getId() {
        return 0x20;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }

    public static class ChunkSection implements MinecraftCustom {
        public Short blockCount;
        Byte bitsPerBlock;

    }

}
