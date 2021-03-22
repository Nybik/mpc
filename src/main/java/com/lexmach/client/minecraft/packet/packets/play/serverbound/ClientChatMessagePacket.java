package com.lexmach.client.minecraft.packet.packets.play.serverbound;

import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class ClientChatMessagePacket extends PlayStatePacket {
    public VarString chat;

    public ClientChatMessagePacket() {}

    public ClientChatMessagePacket(String msg) {
        chat = new VarString(msg);
    }


    @Override
    public int getId() {
        return 0x03;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
