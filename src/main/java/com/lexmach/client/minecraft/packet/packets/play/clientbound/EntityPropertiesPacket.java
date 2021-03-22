package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;
import com.lexmach.client.minecraft.packet.util.PlayerState;

import java.io.IOException;
import java.io.InputStream;

public class EntityPropertiesPacket extends Packet {

    public byte[] arr;

    public void specialRead(InputStream in, VarInt packageSize) throws IOException {
        arr = new byte[packageSize.num - new VarInt(getId()).toBytes().length];
        PacketUtil.readFully(in, arr);
    }

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x58;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
