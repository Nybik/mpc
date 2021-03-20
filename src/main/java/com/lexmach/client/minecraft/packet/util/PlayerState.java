package com.lexmach.client.minecraft.packet.util;

public enum PlayerState {
    HANDSHAKING(0), STATUS(1), LOGIN(2), PLAY(3);
    private final int number;

    PlayerState(int num) {
        this.number = num;
    }
}
