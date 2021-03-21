package com.lexmach.client.minecraft.packet.handler.events;

import com.lexmach.client.minecraft.fakeplayer.FakePlayer;
import com.lexmach.client.minecraft.packet.Packet;

public class PacketSentEvent {

    private FakePlayer player;
    private Packet sent;

    PacketSentEvent() {}

    public PacketSentEvent(FakePlayer player, Packet sent) {
        this.player = player;
        this.sent = sent;
    }

    public FakePlayer getPlayer() {
        return player;
    }

    public void setPlayer(FakePlayer player) {
        this.player = player;
    }

    public Packet getSent() {
        return sent;
    }

    public void setSent(Packet sent) {
        this.sent = sent;
    }
}
