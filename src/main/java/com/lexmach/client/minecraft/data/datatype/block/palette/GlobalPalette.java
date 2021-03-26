package com.lexmach.client.minecraft.data.datatype.block.palette;

import com.lexmach.client.minecraft.data.datatype.block.BlockState;

import java.util.HashMap;
import java.util.Map;

public class GlobalPalette {
    private static final Map<Integer, BlockState> blockStateId = new HashMap<>();
    private static final Map<String, BlockState> blockStateName = new HashMap<>();

    public static void registerBlock(final BlockState blockState) {
        //TODO check if already existed
        blockStateId.put(blockState.getId(), blockState);
        blockStateName.put(blockState.getName(), blockState);
    }

    public static BlockState getBlockState(final int id) {
        return blockStateId.get(id);
    }

    public static BlockState getBlockState(final String name) {
        return blockStateName.get(name);
    }
}
