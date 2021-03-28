package com.lexmach.client.minecraft.data.datatype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry {

    public static Map<NamespacedID, Registry> registries = new HashMap<>();

    private NamespacedID name = new NamespacedID("", "");
    private List<NamespacedID> entries = new ArrayList<>();

    private Registry() {}

    public Registry(NamespacedID name, List<NamespacedID> entries) {
        this.name = name;
        this.entries = entries;

        registries.put(name, this);
    }

    public Registry(String name, List<NamespacedID> entries) {
        this(new NamespacedID(name), entries);
    }

    public List<NamespacedID> getEntries() {
        return entries;
    }

    public void setEntries(List<NamespacedID> entries) {
        this.entries = entries;
    }

    public NamespacedID getName() {
        return name;
    }

    public void setName(NamespacedID name) {
        this.name = name;
    }

    public static List<NamespacedID> getEntries(NamespacedID name) {
        return registries.getOrDefault(name, new Registry()).getEntries();
    }

    public static List<NamespacedID> getEntries(String name) {
        return getEntries(new NamespacedID(name));
    }
}
