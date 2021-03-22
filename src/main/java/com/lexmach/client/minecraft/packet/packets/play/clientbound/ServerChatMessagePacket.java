package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.datatype.VarUUID;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class ServerChatMessagePacket extends PlayStatePacket {
    public VarString chat;
    public Byte position;
    public VarUUID uuid;

    @Override
    public int getId() {
        return 0x0E;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
