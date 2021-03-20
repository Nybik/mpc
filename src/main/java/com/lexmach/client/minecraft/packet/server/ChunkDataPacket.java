package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarTag;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;

public class ChunkDataPacket extends Packet {
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
//        System.out.println("packageSize = " + packageSize);
        if (fullChunk) {
            biomes = PacketUtil.getObjectFromStream(VarInt[].class, in);
        }
        data = PacketUtil.getObjectFromStream(byte[].class, in);
        blockEntities = PacketUtil.getObjectFromStream(VarTag[].class, in);
//        System.out.println(PacketUtil.getBytesFromObject(chunkX).length);
//        System.out.println(PacketUtil.getBytesFromObject(chunkZ).length);
//        System.out.println(PacketUtil.getBytesFromObject(fullChunk).length);
//        System.out.println(PacketUtil.getBytesFromObject(primaryBit).length);
//        System.out.println(PacketUtil.getBytesFromObject(heightMaps).length);
//        System.out.println(PacketUtil.getBytesFromObject(data).length);
//        System.out.println(PacketUtil.getBytesFromObject(biomes).length);
//        System.out.println(PacketUtil.getBytesFromObject(blockEntities).length);

//        VarInt length = new VarInt();
//        length.fromStream(in);
//        blockEntities = new VarTag[length.num];
//        System.out.println("length.num = " + length.num);
//        for (int i = 0; i < length.num; ++i) {
//            blockEntities[i] = new VarTag();
//            blockEntities[i].fromStream(in);
//        }
    }

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
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
