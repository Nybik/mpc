package com.lexmach.client.minecraft.data.datatype.chunk;

import com.lexmach.client.minecraft.data.datatype.Position;
import com.lexmach.client.minecraft.data.datatype.block.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Chunk {
    private final ChunkPosition chunkPosition;

    private final Map<Position, BlockState> blocks = new HashMap<>();

    Chunk(ChunkPosition pos) {
        chunkPosition = pos;
    }

    Chunk(int x, int z) {
        chunkPosition = new ChunkPosition(x, z);
    }

    public synchronized void setBlock(Position pos, BlockState blockState) {
        blocks.put(pos, blockState);
    }

    public int getX() {
        return chunkPosition.getX();
    }

    public int getZ() {
        return chunkPosition.getZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chunk chunk = (Chunk) o;
        return Objects.equals(chunkPosition, chunk.chunkPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chunkPosition);
    }

    public Map<Position, BlockState> getBlocks() {
        return blocks;
    }

}
