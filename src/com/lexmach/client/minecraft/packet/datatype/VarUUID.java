package com.lexmach.client.minecraft.packet.datatype;

import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class VarUUID implements MinecraftData {
    final static int SIZE = 16;

    byte[] num = new byte[SIZE];

    @Override
    public byte[] toBytes() {
        return num;
    }

    @Override
    public void fromStream(InputStream in) throws IOException {
        PacketUtil.readFully(in, num);
    }

    public BigInteger toBigInteger() {
        return new BigInteger(num);
    }

    @Override
    public String toString() {
        return "VarUUID{" +
                "num=" + toBigInteger() +
                '}';
    }
}
