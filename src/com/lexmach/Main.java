package com.lexmach;

import com.lexmach.client.basic.BasicClientMain;
import com.lexmach.client.minecraft.FakePlayer;
import com.lexmach.client.minecraft.packet.client.PingPacket;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.handler.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;

import java.util.logging.Logger;

public class Main extends PacketEventListener {

    private static Logger log = Logger.getLogger(BasicClientMain.class.getName());

    private static VarInt protocolVersion = new VarInt(754);

    public static void main(String[] args) throws Exception {
        FakePlayer player = new FakePlayer("faker", "localhost", 25565);
        player.addListener(new Main());
        player.connect();

//        player.sendPacket(new PingPacket());
    }

    @Override
    public void onPacketSent(PacketSentEvent event) {
        log.info("Packet id %d is sent from player \"%s\"\nContent: %s".formatted(event.getSent().getId(), event.getPlayer().getName(), event.getSent()));
    }

    @Override
    public void onPacketReceived(PacketReceivedEvent event) {
        log.info("Packet id %d is received from player \"%s\"\nContent: %s".formatted(event.getReceived().getId(), event.getPlayer().getName(), event.getReceived()));
    }
}
