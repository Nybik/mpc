package com.lexmach.client.minecraft.packet.handler.events;

import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;

public abstract class PacketEventListener {

    public abstract void onPacketSent(PacketSentEvent event);

    public abstract void onPacketReceived(PacketReceivedEvent event);

}
