package com.lexmach;

import com.lexmach.client.basic.BasicClientMain;
import com.lexmach.client.minecraft.FakePlayer;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.client.PingPacket;
import com.lexmach.client.minecraft.packet.client.TeleportConfirmPacket;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.handler.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;
import com.lexmach.client.minecraft.packet.server.LoginSuccessPacket;
import com.lexmach.client.minecraft.packet.server.PlayerPositionAndLookPacket;

import java.util.logging.Logger;

public class Main extends PacketEventListener {

    private static Logger log = Logger.getLogger(BasicClientMain.class.getName());

    private static VarInt protocolVersion = new VarInt(754);
    public static FakePlayer player;

    public static void main(String[] args) throws Exception {
        player = new FakePlayer("faker", "localhost", 25565);
        player.addListener(new Main());
        player.connect();
//        byte one = 1;
//        Boolean b = (boolean)one;
//
//        System.out.println();

//        player.sendPacket(new PingPacket());
    }

    @Override
    public void onPacketSent(PacketSentEvent event) {
        log.info("Packet id %d is sent from player \"%s\"\nContent: %s".formatted(event.getSent().getId(), event.getPlayer().getName(), event.getSent()));
    }

    @Override
    public void onPacketReceived(PacketReceivedEvent event) {
        log.info("Packet id %d is received from player \"%s\"\nContent %s".formatted(event.getReceived().getId(), event.getPlayer().getName(), event.getReceived().getClass().getName()));
//        if (event.getReceived() instanceof PlayerPositionAndLookPacket) {
//            PlayerPositionAndLookPacket p = (PlayerPositionAndLookPacket) event.getReceived();
//            try {
//                player.sendPacket(new TeleportConfirmPacket(p.teleportId));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        if (event.getReceived() instanceof LoginSuccessPacket) {
            LoginSuccessPacket p = (LoginSuccessPacket) event.getReceived();
            System.out.println("p.username = " + p.username);
        }
    }
}

