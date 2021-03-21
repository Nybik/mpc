package com.lexmach.client.minecraft.packet.handler;

import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.util.PacketUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

public class CompressionHandler {
    private int threshold = -1;

    public CompressionHandler() { }

    public byte[] readPacketData(InputStream in) throws Exception {
        if (threshold > 0) {
            VarInt packetLength = PacketUtil.getObjectFromStream(VarInt.class, in);
            VarInt dataLength = PacketUtil.getObjectFromStream(VarInt.class, in);

            byte[] compressed = new byte[packetLength.num - dataLength.toBytes().length];
            PacketUtil.readFully(in, compressed);

            if (dataLength.num == 0) {
                return compressed;
            }
            else {
                byte[] data = new byte[dataLength.num];

                Inflater inflater = new Inflater();
                inflater.setInput(compressed);
                int uncompressedLength = inflater.inflate(data);

                if (uncompressedLength != dataLength.num) throw new RuntimeException("Wrong packet decompression");

                return data;
            }
        }
        else {
            VarInt packetSize = PacketUtil.getObjectFromStream(VarInt.class, in);
            byte[] data = new byte[packetSize.num];
            PacketUtil.readFully(in, data);
            return data;
        }
    }

    public byte[] prepare(Packet packet) throws Exception {
        byte[] uncompressedData = packet.prepare();
        if (uncompressedData.length >= threshold && threshold > 0) {
            byte[] compressedData = compress(uncompressedData);
            return ArrayUtils.addAll(new VarInt(uncompressedData.length).toBytes(), compressedData);
        }
        else if (threshold > 0) {
            System.out.println(uncompressedData.length );
            System.out.println("what");
            return ArrayUtils.addAll(new VarInt(0).toBytes(), uncompressedData);
        }
        else {
            System.out.println("???");
            return uncompressedData;
        }
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public static byte[] compress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream defl = new DeflaterOutputStream(out);
            defl.write(in);
            defl.flush();
            defl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(150);
            return null;
        }
    }

    public static byte[] decompress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out);
            infl.write(in);
            infl.flush();
            infl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(150);
            return null;
        }
    }
}
