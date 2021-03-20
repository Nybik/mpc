package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarString;

public class ClientSettingsPacket extends Packet {

    public VarString locale;
    public byte viewDistance;
    public VarInt chatMode;
    public boolean chatColors;
    public byte displayedSkins;
    public VarInt mainHand;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x05;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
