package com.lexmach.client.minecraft.packet.packets.play.serverbound;

import com.lexmach.client.minecraft.datatype.Location;
import com.lexmach.client.minecraft.datatype.Look;
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

    public ClientPlayerPositionAndLookPacket(Location location, float yaw, float pitch, boolean onGround) {
        this(location.getX(), location.getY(), location.getZ(), yaw, pitch, onGround);
    }

    public ClientPlayerPositionAndLookPacket(Location location, Look look, boolean onGround) {
        this(location, look.getYaw(), look.getPitch(), onGround);
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
