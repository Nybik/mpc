package com.lexmach;

import com.lexmach.client.basic.BasicClientMain;
import com.lexmach.client.minecraft.fakeplayer.FakePlayer;
import com.lexmach.client.minecraft.packet.client.ClientKeepAlivePacket;
import com.lexmach.client.minecraft.packet.client.ClientPlayerPositionAndLookPacket;
import com.lexmach.client.minecraft.packet.client.TeleportConfirmPacket;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.handler.events.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;
import com.lexmach.client.minecraft.packet.server.JoinGamePacket;
import com.lexmach.client.minecraft.packet.server.PlayerPositionAndLookPacket;
import com.lexmach.client.minecraft.packet.server.ServerChatMessagePacket;
import com.lexmach.client.minecraft.packet.server.ServerKeepAlivePacket;

import java.util.Random;
import java.util.logging.Logger;

public class Main extends PacketEventListener {

    private static Logger log = Logger.getLogger(BasicClientMain.class.getName());

    private static VarInt protocolVersion = new VarInt(754);

    public static Random rnd = new Random();

    public static String randString() {
        String answer = "";
        for (int i = 0; i < 5; ++i) {
            answer += (char)(rnd.nextInt(26) + 'a');
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; ++i) {
            FakePlayer player = new FakePlayer(randString(), "localhost", 25565);
            player.addListener(new Main());
            player.connect();
//            Thread.sleep(4000);
        }
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

    public static double x, y, z;
    public static float yaw, pitch;


    @Override
    public void onPacketReceived(PacketReceivedEvent event) {
        FakePlayer player = event.getPlayer();
//        log.info("Packet id %d is received from player \"%s\"\nContent %s".formatted(event.getReceived().getId(), event.getPlayer().getName(), event.getReceived().getClass().getName()));
        if (event.getReceived() instanceof JoinGamePacket) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getReceived() instanceof PlayerPositionAndLookPacket) {
            PlayerPositionAndLookPacket p = (PlayerPositionAndLookPacket) event.getReceived();
            try {
                player.sendPacket(new TeleportConfirmPacket(p.teleportId));
                System.out.println("new ClientPlayerPositionAndLookPacket(p.X, p.Y, p.Z, p.yaw, p.pitch, true).getData().length = " + new ClientPlayerPositionAndLookPacket(p.X, p.Y, p.Z, p.yaw, p.pitch, true).getData().length);
                player.sendPacket(new ClientPlayerPositionAndLookPacket(p.X, p.Y, p.Z, p.yaw, p.pitch, false));
                x = p.X;
                y = p.Y;
                z = p.Z;
                yaw = p.yaw;
                pitch = p.pitch;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getReceived() instanceof ServerKeepAlivePacket) {
            ServerKeepAlivePacket p = (ServerKeepAlivePacket) event.getReceived();
            try {
                player.sendPacket(new ClientKeepAlivePacket(p.keepAliveId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getReceived() instanceof ServerChatMessagePacket) {
            ServerChatMessagePacket chat = (ServerChatMessagePacket) event.getReceived();
//            if (chat.position <= 2) {
//                log.info("Packet id %d is received from player \"%s\"\nContent %s".formatted(event.getReceived().getId(), event.getPlayer().getName(), event.getReceived().toString()));
//            }
        }
//        if (event.getReceived() instanceof LoginSuccessPacket) {
//            LoginSuccessPacket p = (LoginSuccessPacket) event.getReceived();
//            System.out.println("p.username = " + p.username);
//        }
//        if (event.getReceived() instanceof SpawnLivingEntityPacket) {
//            SpawnLivingEntityPacket p = (SpawnLivingEntityPacket) event.getReceived();
//            System.out.printf("Content: %s%n", p.toString());
//        }
    }
}

