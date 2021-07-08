package com.lexmach.client.minecraft.data.datatype;

import java.util.HashMap;
import java.util.Map;

public enum Material {
    AIR("minecraft:air"),
    DIRT("minecraft:dirt"),
    STONE("minecraft:stone"),
    BEDROCK("minecraft:bedrock"),
    GRASS("minecraft:grass_block"),
    COBBLESTONE("minecraft:cobblestone"),
    UNKNOWN("mpc:unknown");

    private static final Map<NamespacedID, Material> idMaterialMap = new HashMap<>();

    public NamespacedID name;

    Material(String name) {
        this.name = new NamespacedID(name);
    }

    public static Material getMaterialByName(NamespacedID name) {
        return Material.getMaterialByName(name.getName());
    }

    public static Material getMaterialByName(String name) {
        return idMaterialMap.getOrDefault(new NamespacedID(name), Material.UNKNOWN);
    }


    static {
        for (Material material: Material.values()) {
            idMaterialMap.put(material.name, material);
        }
    }
}
