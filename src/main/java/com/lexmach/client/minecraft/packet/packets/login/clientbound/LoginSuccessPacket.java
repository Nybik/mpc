package com.lexmach.client.minecraft.packet.packets.login.clientbound;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.exceptions.WrongStateException;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.datatype.VarUUID;

public class LoginSuccessPacket extends Packet {

    public VarUUID id;
    public VarString username;

    @Override
    public PlayerState changeState(PlayerState current) {
        if (current != PlayerState.LOGIN) {
            throw new WrongStateException("Wrong state %s when received LoginSuccessPacket".formatted(current));
        }
        return PlayerState.PLAY;
    }

    @Override
    public PlayerState getState() {
        return PlayerState.LOGIN;
    }

    @Override
    public int getId() {
        return 0x02;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
