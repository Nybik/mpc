package com.lexmach.client.minecraft.packet.packets.play.serverbound;

import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;

public class ClientPlayerPositionAndLookPacket extends PlayStatePacket {
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
    public int getId() {
        return 0x13;
    }

    @Override
    public boolean isServerBound() {
        return true;
    }
}
