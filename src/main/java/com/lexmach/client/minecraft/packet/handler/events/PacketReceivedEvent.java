package com.lexmach.client.minecraft.packet.handler.events;

import com.lexmach.client.minecraft.fakeplayer.FakePlayer;
import com.lexmach.client.minecraft.packet.Packet;

public class PacketReceivedEvent {

    private FakePlayer player;
    private Packet received;

    PacketReceivedEvent() {}

    public PacketReceivedEvent(FakePlayer player, Packet received) {
        this.player = player;
        this.received = received;
    }

    public FakePlayer getPlayer() {
        return player;
    }

    public void setPlayer(FakePlayer player) {
        this.player = player;
    }

    public Packet getReceived() {
        return received;
    }

    public void setReceived(Packet received) {
        this.received = received;
    }
}
