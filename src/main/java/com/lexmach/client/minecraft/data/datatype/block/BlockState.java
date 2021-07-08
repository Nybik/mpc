package com.lexmach.client.minecraft.data.datatype.block;

import com.lexmach.client.minecraft.data.datatype.Material;
import com.lexmach.client.minecraft.data.datatype.NamespacedID;
import com.lexmach.client.minecraft.data.datatype.block.palette.GlobalPalette;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BlockState {
    public static List<BlockState> blockStates = new ArrayList<>();

    private final NamespacedID name;
    private final int id;
    private final Map<String, String> properties;

    public BlockState(String name, int id, Map<String, String> properties) {
        this.name = new NamespacedID(name);
        this.id = id;
        this.properties = properties;

        GlobalPalette.registerBlock(this);
        blockStates.add(this);
    }

    public String getName() {
        return name.toString();
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public NamespacedID getNamespaced() {
        return name;
    }

    @Override
    public String toString() {
        return "BlockState{" +
                "name=" + name +
                ", id=" + id +
                ", properties=" + properties +
                '}';
    }

    public Material getMaterial() {
        return Material.getMaterialByName(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockState that = (BlockState) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, properties);
    }
}
