package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarTag;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class JoinGamePacket extends PlayStatePacket {

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
    public int getId() {
        return 0x24;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
