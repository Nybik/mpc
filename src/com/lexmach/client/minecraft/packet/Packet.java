package com.lexmach.client.minecraft.packet;

import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.util.PacketUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class used to describe every packet in minecraft
 *
 * Write
 * public void specialRead(InputStream in, VarInt packageSize) throws IOException
 * to override standard read method
 */
public abstract class Packet {

    //TODO make Packet implement MinecraftCustom and check if it works

    private static HashMap<Integer, Packet> serverPackets = new HashMap<>();
    private static HashMap<Integer, Packet> clientPackets = new HashMap<>();

    public Packet() { }

    public abstract int getId();

    public abstract boolean isServerBound();

    public byte[] getData() throws Exception {
        List<Object> fields = new ArrayList<>();
        for (var field : this.getClass().getFields()) {
            fields.add(field.get(this));
        }
        List<byte[]> objByteArray = new ArrayList<>();
        int size = 0;
        for (var obj : fields) {
            byte[] byteObj = PacketUtil.getBytesFromObject(obj);
            objByteArray.add(byteObj);
            size += byteObj.length;
        }
        byte[] data = new byte[size];
        int it = 0;
        for (var bytes : objByteArray) {
            for (var objByte : bytes) {
                data[it++] = objByte;
            }
        }
        return data;
    }

    @Override
    public String toString() {
        String str = "[%s]: {\n%s}";
        String name = this.getClass().getName();
        StringBuilder content = new StringBuilder();
        for (var field : this.getClass().getFields()) {
            try {
                content.append("\t").append(field.get(this).toString()).append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return str.formatted(name, content.toString());
    }
}