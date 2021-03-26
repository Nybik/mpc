package com.lexmach.client.minecraft.fakeplayer.handler;

import com.lexmach.client.minecraft.data.datatype.Location;
import com.lexmach.client.minecraft.data.datatype.Look;

public class FakePlayerPositionHandler {

    private Location location = null;
    private Look look = null;

    public synchronized Look getLook() {
        return look;
    }

    public synchronized Location getLocation() {
        return location;
    }

    public synchronized void setLocation(Location location) {
        this.location = location;
    }

    public synchronized void setLook(Look look) {
        this.look = look;
    }
}
