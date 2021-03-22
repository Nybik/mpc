package com.lexmach.client.minecraft.packet.packets.login.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarString;
import com.lexmach.client.minecraft.packet.datatype.VarUUID;
import com.lexmach.client.minecraft.packet.exceptions.WrongStateException;
import com.lexmach.client.minecraft.packet.packets.login.LoginStatePacket;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class LoginSuccessPacket extends LoginStatePacket {

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
    public int getId() {
        return 0x02;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
