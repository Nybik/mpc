package com.lexmach.client.minecraft.fakeplayer.handler;

import com.lexmach.client.minecraft.datatype.Location;

public class LocationHandler {

    private Location location = null;

    public synchronized Location getLocation() {
        return location;
    }

    public synchronized void setLocation(Location location) {
        this.location = location;
    }
}
