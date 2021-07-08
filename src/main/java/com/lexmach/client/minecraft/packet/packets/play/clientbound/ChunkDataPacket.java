package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.data.datatype.Position;
import com.lexmach.client.minecraft.data.datatype.block.BlockState;
import com.lexmach.client.minecraft.data.datatype.chunk.Chunk;
import com.lexmach.client.minecraft.data.datatype.chunk.ChunkPosition;
import com.lexmach.client.minecraft.data.datatype.chunk.Chunks;
import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.datatype.VarChunkSection;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarTag;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;
import java.util.Map;

public class ChunkDataPacket extends PlayStatePacket {

    Integer chunkX;
    Integer chunkZ;
    Boolean fullChunk;
    VarInt primaryBit = new VarInt();
    VarTag heightMaps = new VarTag();
    VarInt[] biomes;
    VarChunkSection[] sections;
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
        sections = new VarChunkSection[Chunks.CHUNK_SECTION_COUNT];

        VarInt sectionLength = PacketUtil.getObjectFromStream(VarInt.class, in);
        for (int sectionY = 0; sectionY < Chunks.CHUNK_SECTION_COUNT; ++sectionY) {
            if ((primaryBit.num & (1 << sectionY)) == 1) { // if ith bit is present
                sections[sectionY] = new VarChunkSection();
                sections[sectionY].fromStream(in);
            }
        }

        blockEntities = PacketUtil.getObjectFromStream(VarTag[].class, in);

        Chunk chunk = Chunks.get(new ChunkPosition(chunkX, chunkZ));
        for (int sectionY = 0; sectionY < Chunks.CHUNK_SECTION_COUNT; ++sectionY) {
            if (sections[sectionY] != null) {
                int offset = sectionY * Chunks.SECTION_HEIGHT;
                sections[sectionY].blocks.forEach((pos, block) -> {
                    chunk.setBlock(pos.add(new Position(0, offset, 0)), block);
                });
            }
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
