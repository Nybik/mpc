package com.lexmach.client.minecraft.packet.util;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.client.*;
import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.datatype.MinecraftData;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.exceptions.UnknownPackageException;
import com.lexmach.client.minecraft.packet.server.*;
import org.apache.commons.lang3.NotImplementedException;

import javax.lang.model.element.UnknownElementException;
import javax.lang.model.type.UnknownTypeException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class PacketUtil {

    private static HashMap<Integer, Packet> serverPackets = new HashMap<>();
    private static HashMap<Integer, Packet> clientPackets = new HashMap<>();

    public static void registerPacket(Packet packet) throws Exception {
        if (packet.isServerBound()) {
            clientPackets.put(packet.getId(), packet);
        }
        else if (!packet.isServerBound()) {
            if (serverPackets.containsKey(packet.getId())) throw new Exception("Packet id %d is already in use".formatted(packet.getId()));
            serverPackets.put(packet.getId(), packet);
        }
    }

    public static void readFully(InputStream in, byte[] arr) throws IOException {
        int read = 0;
        while (read != arr.length) {
            read += in.read(arr, read, arr.length - read);
        }
        return;
    }

    public static void writeToFields(Class packet, Object... fields) throws IllegalAccessException {
        Field[] packetFields = packet.getFields();
        if (packetFields.length != fields.length) throw new IndexOutOfBoundsException("Wrong fields array");
        for (int i = 0; i < packetFields.length; ++i) {
            packetFields[i].set(packet, fields[i]);
        }
    }

    public static final byte[] getBytesFromObject(Object obj) {
        if (obj instanceof MinecraftData) return ((MinecraftData) obj).toBytes();
        if (obj instanceof Boolean) {
            if ((Boolean) obj) return new byte[]{0x01};
            else return new byte[]{0x00};
        }
        if (obj instanceof Byte) return new byte[]{(byte) obj};
        if (obj instanceof Short) return Shorts.toByteArray((Short) obj);
        if (obj instanceof Integer) return Ints.toByteArray((Integer) obj);
        if (obj instanceof Long) return Longs.toByteArray((Long) obj);
        throw new NotImplementedException(obj.getClass().toString());
    }
    public static void setObjectFromStream(Object obj, InputStream in) throws Exception {
        if (obj.getClass().isArray()) {
            VarInt length = new VarInt();
            PacketUtil.setObjectFromStream(length, in);
            obj = Array.newInstance(obj.getClass().arrayType(), length.num);
            for (int i = 0; i < length.num; ++i) {
                PacketUtil.setObjectFromStream(Array.get(obj, i), in);
            }
        }
        else if (obj instanceof MinecraftCustom) {
            for (Field field : obj.getClass().getFields()) {
                Object newField = field.getType().getDeclaredConstructor().newInstance();

                PacketUtil.setObjectFromStream(newField, in);
                field.set(obj, newField);
            }
        }
        else if (obj instanceof MinecraftData) {
            ((MinecraftData)obj).fromStream(in);
        }
        else if (obj instanceof Boolean) {
            byte[] tmp = new byte[1];
            in.read(tmp);
            obj = tmp[0];
        } else if (obj instanceof Byte) {
            byte[] tmp = new byte[1];
            in.read(tmp);
            obj = tmp[0];
        } else if (obj instanceof Integer) {
            byte[] tmp = new byte[Ints.BYTES];
            in.read(tmp);
            obj = Ints.fromByteArray(tmp);
        } else if (obj instanceof Long) {
            byte[] tmp = new byte[Long.BYTES];
            in.read(tmp);
            obj = Longs.fromByteArray(tmp);
        } else {
            throw new NotImplementedException(obj.getClass().toString());
        }
    }

    //TODO sendPacket

    public static Packet readPacket(InputStream in) throws Exception {
        VarInt packetSize = new VarInt();
        VarInt packetId = new VarInt();
        PacketUtil.setObjectFromStream(packetSize, in);
        PacketUtil.setObjectFromStream(packetId, in);

        Packet received = PacketUtil.getServerPacketById(packetId.num);
        if (received == null) {
            byte[] info = new byte[packetSize.num - packetId.toBytes().length];
            int readed = 0;
            while (readed != info.length) {
                readed += in.read(info, readed, info.length - readed);
            }
            throw new UnknownPackageException("Package of type %d is unknown".formatted(packetId.num));
        }
        Method read = hasSpecialRead(received);

        if (read != null) {
            read.invoke(received, packetSize);
            return received;
        }
        for (Field field : received.getClass().getFields()) {
            Object obj = field.getType().getDeclaredConstructor().newInstance();

            PacketUtil.setObjectFromStream(obj, in);
            field.set(received, obj);
        }
        return received;
    }

    public static Method hasSpecialRead(Packet packet) {
        try {
            return packet.getClass().getMethod("specialRead");
        } catch (NoSuchMethodException e) {
            return null;
        }
    }


    public static Packet getClientPacketById(int packetId) {
        return clientPackets.get(packetId);
    }
    public static Packet getServerPacketById(int packetId) {
        return serverPackets.get(packetId);
    }

    static {
        try {
            //Client packets
            registerPacket(new ClientSettingsPacket());
            registerPacket(new HandshakePacket());
            registerPacket(new LoginStartPacket());
            registerPacket(new PingPacket());
            registerPacket(new RequestPacket());


            //Server packets
            registerPacket(new DeclareCommandsPacket());
            registerPacket(new DeclareRecipesPacket());
            registerPacket(new EncryptionRequestPacket());
            registerPacket(new EntityStatusPacket());
            registerPacket(new HeldItemChangeEvent());
            registerPacket(new JoinGamePacket());
            registerPacket(new LoginSuccessPacket());
            registerPacket(new PlayerAbilitiesPacket());
            registerPacket(new PluginMessagePacket());
            registerPacket(new ResponsePacket());
            registerPacket(new ServerDifficultyPacket());
            registerPacket(new SetCompressionPacket());
            registerPacket(new TagsPacket());
            registerPacket(new UnlockRecipesPacket());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
