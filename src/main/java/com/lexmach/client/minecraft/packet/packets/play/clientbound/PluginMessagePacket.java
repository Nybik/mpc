package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;

public class PluginMessagePacket extends PlayStatePacket {

    public VarIdentifier channel;

    //TODO
    public byte[] data;

    public void specialRead(InputStream in, VarInt packageSize) throws Exception {
        channel = new VarIdentifier();
        channel.fromStream(in);
        int dataSize = packageSize.num - channel.toBytes().length;
        data = new byte[dataSize];
        PacketUtil.readFully(in, data);
    }

    @Override
    public int getId() {
        return 0x17;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
