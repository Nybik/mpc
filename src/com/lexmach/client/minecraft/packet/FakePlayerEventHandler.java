package com.lexmach.client.minecraft.packet;

import com.lexmach.client.minecraft.FakePlayer;
import com.lexmach.client.minecraft.packet.handler.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;

public class FakePlayerEventHandler extends PacketEventListener {
    FakePlayer player;

    @Override
    public void onPacketSent(PacketSentEvent event) {

    }

    @Override
    public void onPacketReceived(PacketReceivedEvent event) {
        Packet packet = event.getReceived();

    }
}
