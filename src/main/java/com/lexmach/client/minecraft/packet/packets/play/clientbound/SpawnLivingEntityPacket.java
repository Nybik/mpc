package com.lexmach.client.minecraft.packet.packets.play.clientbound;

import com.lexmach.client.minecraft.packet.datatype.VarAngle;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.datatype.VarUUID;
import com.lexmach.client.minecraft.packet.packets.play.PlayStatePacket;


public class SpawnLivingEntityPacket extends PlayStatePacket {
    public VarInt entityId;
    public VarUUID entityUUID;
    public VarInt type;
    public Double x;
    public Double y;
    public Double z;
    public VarAngle yaw;
    public VarAngle pitch;
    public VarAngle headPitch;
    public Short velocityX;
    public Short velocityY;
    public Short velocityZ;

    @Override
    public int getId() {
        return 0x02;
    }

    @Override
    public boolean isServerBound() {
        return false;
    }
}
