package com.lexmach.client.minecraft.data.datatype.block.palette;

import com.lexmach.client.minecraft.data.datatype.block.BlockState;

import java.io.InputStream;
import java.io.OutputStream;

public class DirectPalette extends Palette {
    @Override
    public int getId(BlockState blockState) {
        return blockState.getId();
    }

    @Override
    public BlockState getBlockState(int id) {
        return GlobalPalette.getBlockState(id);
    }

    @Override
    public byte getBytePerBlock() {
        return (byte) Math.ceil(Math.log(BlockState.blockStates.size()) / Math.log(2));
    }

    @Override
    public void read(InputStream in) { }

    @Override
    public void write(OutputStream out) { }
}
