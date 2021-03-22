package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.IOException;
import java.io.InputStream;

public class UpdateLightPacket extends Packet {
    public static final int SIZE1 = 18;
    public static final int SIZE2 = 2048;


    public VarInt chunkX = new VarInt();
    public VarInt chunkZ = new VarInt();
    public Boolean trustEdges = false;
    public VarInt skyLightMask = new VarInt();
    public VarInt blockLightMask = new VarInt();
    public VarInt emptySkyLightMask = new VarInt();
    public VarInt emptyBlockLightMask = new VarInt();
    public byte[][] skyLightArray;
    public byte[][] blockLightArray;

    public void specialRead(InputStream in, VarInt packageSize) throws Exception {
        chunkX.fromStream(in);
        chunkZ.fromStream(in);
        trustEdges = PacketUtil.getObjectFromStream(Boolean.class, in);
        skyLightMask.fromStream(in);
        blockLightMask.fromStream(in);
        emptySkyLightMask.fromStream(in);
        emptyBlockLightMask.fromStream(in);
        skyLightArray = new byte[SIZE1][];
        blockLightArray = new byte[SIZE1][];
        for (int i = 0; i < SIZE1; ++i) {
            int bit1 = ((emptySkyLightMask.num >> i) & 1);
            int bit2 = ((skyLightMask.num >> i) & 1);
            if (bit2 == 1 && bit1 == 1) { // has the i'th least significant bit
                VarInt length = new VarInt();
                length.fromStream(in);
                skyLightArray[i] = new byte[length.num];
                PacketUtil.readFully(in, skyLightArray[i]);
            }
        }
        for (int i = 0; i < SIZE1; ++i) {
            int bit1 = ((emptyBlockLightMask.num >> i) & 1);
            int bit2 = ((blockLightMask.num >> i) & 1);
            if (bit2 == 1 && bit1 == 1) { // has the i'th least significant bit
                VarInt length = new VarInt();
                length.fromStream(in);
                blockLightArray[i] = new byte[length.num];
                PacketUtil.readFully(in, blockLightArray[i]);
            }
        }
    }


    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x23;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
