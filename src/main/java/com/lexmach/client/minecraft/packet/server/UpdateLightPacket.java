package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

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
//    public byte[] arr;

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
//        System.out.println("packageSize = " + packageSize);
//        System.out.println("emptyBlockLightMask = " + emptyBlockLightMask);
//        int read = 0;
        for (int i = 0; i < SIZE1; ++i) {
//            int bit1 = ((emptySkyLightMask.num >> i) & 1);
            int bit1 = 1;
            int bit2 = ((skyLightMask.num >> i) & 1);
            if (bit2 == 1) { // has the i'th least significant bit
                VarInt length = new VarInt();
                length.fromStream(in);
                skyLightArray[i] = new byte[SIZE2];
//                read += SIZE2;
                PacketUtil.readFully(in, skyLightArray[i]);
            }
        }
        for (int i = 0; i < SIZE1; ++i) {
//            int bit1 = ((emptyBlockLightMask.num >> i) & 1);
            int bit1 = 1;

            int bit2 = ((blockLightMask.num >> i) & 1);
            if (bit2 == 1) { // has the i'th least significant bit
                VarInt length = new VarInt();
                length.fromStream(in);
                blockLightArray[i] = new byte[SIZE2];
//                read += SIZE2;

                PacketUtil.readFully(in, blockLightArray[i]);
//                System.out.println(i);
            }
        }
//        System.out.println("read = " + read);
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
