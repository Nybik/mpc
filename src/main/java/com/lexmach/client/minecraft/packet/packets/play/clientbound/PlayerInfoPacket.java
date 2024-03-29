package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;

public class PlayerInfoPacket extends PlayStatePacket {

    public byte[] data;

    public void specialRead(InputStream in, VarInt packageSize) throws Exception {
        int dataSize = packageSize.num - new VarInt(getId()).toBytes().length;
        data = new byte[dataSize];
        PacketUtil.readFully(in, data);
    }

    @Override
    public int getId() {
        return 0x32;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
