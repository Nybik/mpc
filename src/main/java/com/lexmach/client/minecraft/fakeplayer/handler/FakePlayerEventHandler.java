package com.lexmach.client.minecraft.fakeplayer.handler;

import com.lexmach.client.minecraft.datatype.Location;
import com.lexmach.client.minecraft.fakeplayer.FakePlayer;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.handler.events.PacketEventListener;
import com.lexmach.client.minecraft.packet.handler.events.PacketReceivedEvent;
import com.lexmach.client.minecraft.packet.handler.events.PacketSentEvent;
import com.lexmach.client.minecraft.packet.packets.login.clientbound.SetCompressionPacket;
import com.lexmach.client.minecraft.packet.packets.play.clientbound.PlayerPositionAndLookPacket;
import com.lexmach.client.minecraft.packet.packets.play.clientbound.ServerKeepAlivePacket;
import com.lexmach.client.minecraft.packet.packets.play.clientbound.UpdateViewPositionPacket;
import com.lexmach.client.minecraft.packet.packets.play.serverbound.ClientKeepAlivePacket;

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
        if (packet instanceof PlayerPositionAndLookPacket) {
            PlayerPositionAndLookPacket p = (PlayerPositionAndLookPacket)packet;
            player.getLocationHandler().setLocation(new Location(p.X, p.Y, p.Z));
        }
        if (packet instanceof ServerKeepAlivePacket) {
            ServerKeepAlivePacket p = (ServerKeepAlivePacket)packet;
            try {
                player.sendPacket(new ClientKeepAlivePacket(p.keepAliveId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlayer(FakePlayer player) {
        this.player = player;
    }
}
