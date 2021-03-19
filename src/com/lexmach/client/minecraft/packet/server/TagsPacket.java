package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.*;

public class TagsPacket extends Packet {

    public Tag[] blockTags;
    public Tag[] itemTags;
    public Tag[] fluidTags;
    public Tag[] entityTags;

    @Override
    public int getId() {
        return 0x5B;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }

    public static class Tag implements MinecraftCustom {
        VarIdentifier tagName;
        VarInt[] entries;
    }
}

