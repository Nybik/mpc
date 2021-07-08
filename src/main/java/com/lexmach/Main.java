package com.lexmach;

import com.lexmach.client.minecraft.data.datatype.Location;
import com.lexmach.client.minecraft.data.datatype.Look;
import com.lexmach.client.minecraft.data.datatype.Material;
import com.lexmach.client.minecraft.data.datatype.Registry;
import com.lexmach.client.minecraft.data.datatype.block.palette.GlobalPalette;
import com.lexmach.client.minecraft.data.datatype.chunk.Chunk;
import com.lexmach.client.minecraft.data.datatype.chunk.ChunkPosition;
import com.lexmach.client.minecraft.data.datatype.chunk.Chunks;
import com.lexmach.client.minecraft.data.reader.reports.blocks.BlocksReader;
import com.lexmach.client.minecraft.data.reader.reports.registries.RegistriesReader;
import com.lexmach.client.minecraft.fakeplayer.FakePlayer;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.handler.events.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;
import com.lexmach.client.minecraft.packet.packets.play.clientbound.JoinGamePacket;
import com.lexmach.client.minecraft.packet.packets.play.clientbound.PlayerPositionAndLookPacket;
import com.lexmach.client.minecraft.packet.packets.play.clientbound.ServerChatMessagePacket;
import com.lexmach.client.minecraft.packet.packets.play.serverbound.ClientPlayerPositionAndLookPacket;
import com.lexmach.client.minecraft.packet.packets.play.serverbound.TeleportConfirmPacket;
import com.lexmach.client.util.RepeatableTickThread;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Logger;

public class Main extends PacketEventListener {

    private static Logger log = Logger.getLogger(Main.class.getName());

    private static VarInt protocolVersion = new VarInt(754);

    public static Random rnd = new Random();

    public static String randString() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < 5; ++i) {
            answer.append((char) (rnd.nextInt(26) + 'a'));
        }
        return answer.toString();
    }

    public static void main(String[] args) throws Exception {
        BlocksReader.registerBlockStates(Paths.get("src/main/resources/1.16.5/reports/blocks.json").toFile());
        RegistriesReader.registerRegistries(Paths.get("src/main/resources/1.16.5/reports/registries.json").toFile());
//        System.out.println(GlobalPalette.getBlockState(5));
        Location location = null;
        for (int i = 0; i < 1; ++i) {
            FakePlayer player = new FakePlayer("check1234", "localhost", 25565);
            player.addListener(new Main());
            player.connect();
            location = player.getPositionHandler().getLocation();
        }
        Thread.sleep(2000);
        Chunks.get(location).getBlocks().forEach((x, y) -> {
            if (y.getMaterial() == Material.UNKNOWN)
                System.out.println(x + ":" + y);
        });
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
        if (event.getReceived() instanceof JoinGamePacket) {
            try {
//                new RepeatableTickThread(() ->{
//                    Location loc = player.getPositionHandler().getLocation();
//                    if (loc == null) return;
//                    loc = loc.add(new Location(0.2185, 0, 0));
//                    player.getPositionHandler().setLook(new Look(player.getPositionHandler().getLocation(), loc));
//                    player.getPositionHandler().setLocation(loc);
//                    try {
//                        player.sendPacket(new ClientPlayerPositionAndLookPacket(loc, player.getPositionHandler().getLook(), true));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }, 1, 200);
//                new RepeatableTickThread(() ->{
//                    Location loc = player.getPositionHandler().getLocation();
//                    if (loc == null) return;
//                    Look look = player.getPositionHandler().getLook();
//                    System.out.println("KEK " + loc + " " + look);
//                    look.setPitch(0);
//                    look.setYaw(look.getYaw() + 2);
//                    player.getPositionHandler().setLocation(loc);
//                    try {
//                        player.sendPacket(new ClientPlayerPositionAndLookPacket(loc, player.getPositionHandler().getLook(), true));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getReceived() instanceof PlayerPositionAndLookPacket) {
            PlayerPositionAndLookPacket p = (PlayerPositionAndLookPacket) event.getReceived();
            try {
                player.sendPacket(new TeleportConfirmPacket(p.teleportId));
                player.sendPacket(new ClientPlayerPositionAndLookPacket(p.X, p.Y, p.Z, p.yaw, p.pitch, false));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getReceived() instanceof ServerChatMessagePacket) {
            ServerChatMessagePacket chat = (ServerChatMessagePacket) event.getReceived();
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

