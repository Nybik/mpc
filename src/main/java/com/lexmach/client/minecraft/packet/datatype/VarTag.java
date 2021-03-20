package com.lexmach.client.minecraft.packet.datatype;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.io.InputStream;

public class VarTag implements MinecraftData {
    Tag NBTTag;

    @Override
    public byte[] toBytes() {
        throw new NotImplementedException("");
    }

    @Override
    public void fromStream(InputStream in) throws IOException {
        var NBT = new NBTInputStream(in, false);
        NBTTag = NBT.readTag();
    }
}
