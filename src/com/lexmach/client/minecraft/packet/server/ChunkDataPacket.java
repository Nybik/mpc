package com.lexmach.client.minecraft.packet.server;

import com.flowpowered.nbt.Tag;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarTag;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;

public class ChunkDataPacket extends Packet {
    Integer chunkX = 0;
    Integer chunkZ = 0;
    Boolean fullChunk = false;
    VarInt primaryBit = new VarInt();
    VarTag heightMaps = new VarTag();
    VarInt[] biomes = new VarInt[0];
    byte[] data = new byte[0];
    VarTag[] blockEntities = new VarTag[0];


    public void specialRead(InputStream in, VarInt packageSize) throws Exception {
        PacketUtil.setObjectFromStream(chunkX, in);
        PacketUtil.setObjectFromStream(chunkZ, in);
        PacketUtil.setObjectFromStream(fullChunk, in);
        PacketUtil.setObjectFromStream(primaryBit, in);
        PacketUtil.setObjectFromStream(heightMaps, in);
        System.out.println("heightMaps = " + heightMaps);
        if (fullChunk) {
            PacketUtil.setObjectFromStream(biomes, in);
        }
        VarInt kek = new VarInt();
        kek.fromStream(in);
        data = new byte[kek.num];
        System.out.println(kek.num);
        PacketUtil.readFully(in, data);
        System.out.println("data = " + data.length);
        VarInt length = new VarInt();
        length.fromStream(in);
        blockEntities = new VarTag[length.num];
        for (int i = 0; i < length.num; ++i) {
            blockEntities[i] = new VarTag();
            System.out.println(blockEntities[i]);
            PacketUtil.setObjectFromStream(blockEntities[i], in);
            System.exit(0);
        }
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
