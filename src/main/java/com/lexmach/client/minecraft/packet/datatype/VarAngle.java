package com.lexmach.client.minecraft.packet.datatype;

import java.io.IOException;
import java.io.InputStream;

public class VarAngle implements MinecraftData {
    int angle;

    @Override
    public byte[] toBytes() {
        return new byte[]{(byte)angle};
    }

    @Override
    public void fromStream(InputStream in) throws IOException {
        angle = in.read();
    }
}
