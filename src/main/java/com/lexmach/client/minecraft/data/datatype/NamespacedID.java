package com.lexmach.client.minecraft.data.datatype;

import java.util.Objects;

public class NamespacedID {
    private static final String DEFAULT_NAMESPACE = "minecraft";

    private String namespace;
    private String name;

    public NamespacedID(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;
    }

    public NamespacedID(String namespaced) {
        String[] split = namespaced.split(":");

        if (split.length == 2) {
            this.namespace = split[0];
            this.name = split[1];
        } else {
            this.namespace = DEFAULT_NAMESPACE;
            this.name = split[0];
        }
    }


    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return namespace + ":" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamespacedID that = (NamespacedID) o;
        return Objects.equals(namespace, that.namespace) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, name);
    }
}
