package com.lexmach.client.minecraft.packet;

import com.lexmach.client.minecraft.packet.util.PlayerState;

import java.util.Objects;

public class PacketState {

    private final PlayerState state;
    private final int id;
    private final boolean isServerBound;

    public PacketState(Packet packet) {
        this.state = packet.getState();
        this.id = packet.getId();
        this.isServerBound = packet.isServerBound();
    }

    public PacketState(PlayerState state, int id, boolean isServerBound) {
        this.state = state;
        this.id = id;
        this.isServerBound = isServerBound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacketState that = (PacketState) o;
        return id == that.id &&
                isServerBound == that.isServerBound &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, id, isServerBound);
    }

    @Override
    public String toString() {
        return "PacketState{" +
                "state=" + state +
                ", id=" + id +
                ", isServerBound=" + isServerBound +
                '}';
    }
}
