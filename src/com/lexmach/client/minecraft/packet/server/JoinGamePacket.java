package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarTag;

public class JoinGamePacket extends Packet {

    public int entityID;
    public boolean isHardmode;
    public byte gamemode;
    public byte previousGamemode;
    public VarIdentifier[] worldNames;
    public VarTag dimensionCodec;
    public VarTag dimension;
    public VarIdentifier worldName;
    public long hashseed;
    public VarInt maxPlayers;
    public VarInt viewDistance;
    public boolean reducedDebug;
    public boolean enableRespawnScreen;
    public boolean isDebug;
    public boolean isFlat;

    @Override
    public int getId() {
        return 0x24;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
