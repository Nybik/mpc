package com.lexmach.client.minecraft.data.datatype.block.palette;

import com.lexmach.client.minecraft.data.datatype.block.BlockState;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class IndirectPalette extends Palette {
    private Map<Integer, BlockState> idToState = new HashMap<>();
    private Map<BlockState, Integer> stateToId = new HashMap<>();

    private byte blockByte;

    public IndirectPalette(byte blockByte) {
        this.blockByte = blockByte;
    }

    @Override
    public int getId(BlockState blockState) {
        return blockState.getId();
    }

    @Override
    public BlockState getBlockState(int id) {
        return idToState.get(id);
    }

    @Override
    public byte getBytePerBlock() {
        return blockByte;
    }

    @Override
    public void read(InputStream in) {
        try {
            VarInt[] arr = PacketUtil.getObjectFromStream(VarInt[].class, in);
            for (int i = 0; i < arr.length; ++i) {
                BlockState blockState = GlobalPalette.getBlockState(arr[i].num);
                idToState.put(i, blockState);
                stateToId.put(blockState, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(OutputStream out) {

    }
}
