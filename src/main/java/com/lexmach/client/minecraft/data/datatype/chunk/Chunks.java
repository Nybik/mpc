package com.lexmach.client.minecraft.data.datatype.chunk;

import com.lexmach.client.minecraft.data.datatype.Location;

import java.util.HashMap;
import java.util.Map;

public class Chunks {
    public static int CHUNK_HEIGHT = 256;
    public static int SECTION_HEIGHT = 16;
    public static int SECTION_WIDTH = 16;
    public static int SECTION_LENGTH = 16;

    public static int CHUNK_SECTION_COUNT = 16;

    private static final Map<ChunkPosition, Chunk> chunks = new HashMap<>();

    public static synchronized Chunk get(ChunkPosition pos) {
        chunks.putIfAbsent(pos, new Chunk(pos));
        return chunks.get(pos);
    }

    public static synchronized Chunk get(Location location) {
        int chunkX = (int)Math.floor(location.getX() / SECTION_LENGTH);
        int chunkZ = (int)Math.floor(location.getZ() / SECTION_WIDTH);
        return get(new ChunkPosition(chunkX, chunkZ));
    }
}
