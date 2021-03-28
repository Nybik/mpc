package com.lexmach.client.minecraft.packet.datatype;

import com.google.common.primitives.Longs;
import com.lexmach.client.minecraft.data.datatype.Position;
import com.lexmach.client.minecraft.data.datatype.block.BlockState;
import com.lexmach.client.minecraft.data.datatype.block.palette.Palette;
import com.lexmach.client.minecraft.data.datatype.chunk.Chunks;
import com.lexmach.client.minecraft.packet.util.PacketUtil;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class VarChunkSection implements MinecraftData {
    public Short blockCount;
    public Palette palette;
    public Long[] blockData;
    public HashMap<Position, BlockState> blocks;

    @Override
    public byte[] toBytes() {
        throw new NotImplementedException("Not implemented VarChunk serialization");
    }

    @Override
    public void fromStream(InputStream in) throws IOException {
        try {
            blockCount = PacketUtil.getObjectFromStream(Short.class, in);
            byte bitsPerBlock = PacketUtil.getObjectFromStream(Byte.class, in);
            palette = Palette.choose(bitsPerBlock);
            palette.read(in);
            blockData = PacketUtil.getObjectFromStream(Long[].class, in);
            blocks = new HashMap<>();
            //TODO add support for other versions

            int currentLong = 0, currentOffset = 0;
            long individualMask = (1L << palette.getBytePerBlock()) - 1;
            for (int y = 0; y < Chunks.SECTION_HEIGHT; ++y) {
                for (int x = 0; x < Chunks.SECTION_LENGTH; ++x) {
                    for (int z = 0; z < Chunks.SECTION_WIDTH; ++z) {
                        if (currentOffset + palette.getBytePerBlock() > Longs.BYTES * 8) {
                            currentLong++;
                            currentOffset = 0;
                        }
                        long id = (blockData[currentLong] >> currentOffset) & individualMask;

                        blocks.put(new Position(x, y, z), palette.getBlockState((int) id));

                        currentOffset += palette.getBytePerBlock();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
