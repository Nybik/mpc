package com.lexmach.client.minecraft.fakeplayer;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.handler.events.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;
import com.lexmach.client.minecraft.packet.packets.login.clientbound.SetCompressionPacket;

public class FakePlayerEventHandler extends PacketEventListener {
    private FakePlayer player;

    @Override
    public void onPacketSent(PacketSentEvent event) {

    }

    @Override
    public void onPacketReceived(PacketReceivedEvent event) {
        Packet packet = event.getReceived();
        if (packet instanceof SetCompressionPacket) {
            SetCompressionPacket p = (SetCompressionPacket)packet;
            player.getCompressionHandler().setThreshold(p.threshold.num);
        }
    }

    public void setPlayer(FakePlayer player) {
        this.player = player;
    }
}
