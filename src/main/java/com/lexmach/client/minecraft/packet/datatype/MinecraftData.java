package com.lexmach.client.minecraft.packet.datatype;

import java.io.IOException;
import java.io.InputStream;

public interface MinecraftData {

    public byte[] toBytes();

    public void fromStream(InputStream in) throws IOException;
}
