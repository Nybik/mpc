package com.lexmach.client.minecraft.data.datatype.block;

import com.lexmach.client.minecraft.data.datatype.block.palette.GlobalPalette;

import java.util.Map;

public class BlockState {
    private final String name;
    private final int id;
    private final Map<String, String> properties;

    public BlockState(String name, int id, Map<String, String> properties) {
        this.name = name;
        this.id = id;
        this.properties = properties;

        GlobalPalette.registerBlock(this);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
