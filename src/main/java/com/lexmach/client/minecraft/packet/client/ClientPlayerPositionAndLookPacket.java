package com.lexmach.client.minecraft.packet.client;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.util.PlayerState;

public class ClientPlayerPositionAndLookPacket extends Packet {
    public Double x;
    public Double y;
    public Double z;
    public Float yaw;
    public Float pitch;
    public Boolean onGround;

    public ClientPlayerPositionAndLookPacket(Double x, Double y, Double z, Float yaw, Float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }


    @Override
    public PlayerState getState() {
        return PlayerState.PLAY;
    }

    @Override
    public int getId() {
        return 0x13;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
