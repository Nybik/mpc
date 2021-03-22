package com.lexmach.client.minecraft.packet.packets.play.serverbound;

import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class ClientSettingsPacket extends PlayStatePacket {

    public VarString locale;
    public byte viewDistance;
    public VarInt chatMode;
    public boolean chatColors;
    public byte displayedSkins;
    public VarInt mainHand;

    @Override
    public int getId() {
        return 0x05;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
