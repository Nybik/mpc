package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.IOException;
import java.io.InputStream;

public class DeclareCommandsPacket extends PlayStatePacket {
    //TODO

    public byte[] arr;

    public void specialRead(InputStream in, VarInt packageSize) throws IOException {
        arr = new byte[packageSize.num];
        PacketUtil.readFully(in, arr);
    }

    @Override
    public int getId() {
        return 0x10;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
