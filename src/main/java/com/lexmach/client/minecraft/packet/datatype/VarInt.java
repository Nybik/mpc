package com.lexmach.client.minecraft.packet.datatype;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VarInt implements MinecraftData  {
    public int num;

    public VarInt() {}

    public VarInt(int x) {
        num = x;
    }

    @Override
    public byte[] toBytes() {
        int value = num;
        List<Byte> bytes = new ArrayList<>();
        do {
            byte temp = (byte)(value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            bytes.add(temp);
        } while (value != 0);
        return ArrayUtils.toPrimitive(bytes.toArray(new Byte[0]));
    }

    @Override
    public void fromStream(InputStream in) throws IOException {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = (byte) in.read();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);
        this.num = result;
    }

    @Override
    public String toString() {
        return "VarInt{" +
                "num=" + num +
                '}';
    }
}
