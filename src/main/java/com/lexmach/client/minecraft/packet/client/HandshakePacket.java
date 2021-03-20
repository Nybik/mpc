package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.exceptions.WrongStateException;
import com.lexmach.client.minecraft.packet.util.PlayerState;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarString;
import org.apache.commons.lang3.NotImplementedException;

public class HandshakePacket extends Packet {

    public VarInt protocolVersion;
    public VarString serverAddress;
    public short serverPort; // actually short
    public VarInt state; // 1 - status, 2 - login

    public HandshakePacket(VarInt protocolVersion, VarString serverAddress, short serverPort, VarInt state) {
        this.protocolVersion = protocolVersion;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.state = state;
    }

    public HandshakePacket() {

    }

    @Override
    public PlayerState changeState(PlayerState current) {
        if (current != PlayerState.HANDSHAKING) throw new WrongStateException("Wrong state %s when sent HandshakePacket".formatted(current));
        if (state.num == 1) {
            return PlayerState.STATUS;
        }
        if (state.num == 2) {
            return PlayerState.LOGIN;
        }
        throw new NotImplementedException();
    }

    @Override
    public PlayerState getState() {
        return PlayerState.HANDSHAKING;
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
