package com.lexmach.client.minecraft.packet.packets.play.serverbound;

import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class TeleportConfirmPacket extends PlayStatePacket {
    public VarInt id;

    public TeleportConfirmPacket(VarInt id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
