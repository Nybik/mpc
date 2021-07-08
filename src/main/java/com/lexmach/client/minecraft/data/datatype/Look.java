package com.lexmach.client.minecraft.data.datatype;

/**
 * Minecraft object, defining point on a sphere, that entity is looking to
 * yaw and pitch are float numbers equal to amount of positive degrees (not !radians!)
 *
 * for more info look - wiki.vg/Protocol#Player_Rotation
 */
public class Look {
    private float yaw;
    private float pitch;

    public Look(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Look(Location from, Location to) {
        Location vec = to.sub(from);
        vec = vec.normalize();
        this.yaw = (float) (-Math.atan2(vec.getX(), vec.getZ()) / Math.PI * 180);
        if (yaw < 0) yaw += 360;
        this.pitch = (float) (-Math.asin(vec.getY()) / Math.PI * 180);
    }

    public Location vecLookingAt() {
        return new Location(-Math.cos(pitch) * Math.sin(yaw), -Math.sin(pitch), Math.cos(pitch) * Math.cos(yaw));
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        if (yaw >= 360) yaw -= 360;
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        if (pitch >= 360) pitch -= 360;
        this.pitch = pitch;
    }

    @Override
    public String toString() {
        return "Look{" +
                "yaw=" + yaw +
                ", pitch=" + pitch +
                '}';
    }
}
