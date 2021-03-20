package com.lexmach.client.minecraft.packet.server;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarTag;

public class JoinGamePacket extends Packet {

    public Integer entityID;
    public Boolean isHardmode;
    public Byte gamemode;
    public Byte previousGamemode;
    public VarIdentifier[] worldNames;
    public VarTag dimensionCodec;
    public VarTag dimension;
    public VarIdentifier worldName;
    public Long hashseed;
    public VarInt maxPlayers;
    public VarInt viewDistance;
    public Boolean reducedDebug;
    public Boolean enableRespawnScreen;
    public Boolean isDebug;
    public Boolean isFlat;

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x24;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
