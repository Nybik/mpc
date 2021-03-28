package com.lexmach.client.minecraft.packet.datatype;

import java.io.IOException;
import java.io.InputStream;

public class VarIdentifier implements MinecraftData {
    public VarString identifier;

    @Override
    public byte[] toBytes() {
        return identifier.toBytes();
    }

    @Override
    public void fromStream(InputStream in) throws IOException {
        identifier = new VarString();
        identifier.fromStream(in);
    }
}
