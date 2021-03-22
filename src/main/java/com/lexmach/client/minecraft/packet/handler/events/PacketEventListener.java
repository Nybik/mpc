package com.lexmach.client.minecraft.packet.handler.events;

public abstract class PacketEventListener {

    public abstract void onPacketSent(PacketSentEvent event);

    public abstract void onPacketReceived(PacketReceivedEvent event);

}
