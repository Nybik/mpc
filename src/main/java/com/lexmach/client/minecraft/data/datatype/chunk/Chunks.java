package com.lexmach.client.minecraft.data.datatype.chunk;

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

    public static synchronized Chunk get(int x, int z) {
        return get(new ChunkPosition(x, z));
    }
}
