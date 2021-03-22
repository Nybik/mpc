package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarIdentifier;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.IOException;
import java.io.InputStream;

public class UnlockRecipesPacket extends Packet {
    VarInt action;
    Boolean craftingRecipeBookOpen;
    Boolean craftingRecipeBookFilter;
    Boolean smeltingRecipeBookFilterActive;
    Boolean blastFurnaceBookOpen;
    Boolean blastFurnaceBookFilterActive;
    Boolean smokerBookOpen;
    Boolean smokerBookFilterActive;
    VarIdentifier[] recipeIds;
    VarIdentifier[] recipeId2s;
    //TODO
    public byte[] arr;

    public void specialRead(InputStream in, VarInt packageSize) throws IOException {
        arr = new byte[packageSize.num];
        PacketUtil.readFully(in, arr);
    }

    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }


    @Override
    public int getId() {
        return 0x35;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
