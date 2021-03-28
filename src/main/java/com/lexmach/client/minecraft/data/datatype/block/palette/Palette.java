package com.lexmach.client.minecraft.data.datatype.block.palette;

import com.lexmach.client.minecraft.data.datatype.block.BlockState;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Palette {

    public abstract int getId(BlockState blockState);

    public abstract BlockState getBlockState(int id);

    public abstract byte getBytePerBlock();

    public abstract void read(InputStream in);

    public abstract void write(OutputStream out);

    public static Palette choose(byte bitsPerBlock) {
        if (bitsPerBlock <= 4) {
            return new IndirectPalette((byte) 4);
        } else if (bitsPerBlock <= 8) {
            return new IndirectPalette((byte) bitsPerBlock);
        } else {
            return new DirectPalette();
        }
    }
}
