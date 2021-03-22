package com.lexmach.client.minecraft.packet.util;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import com.lexmach.client.minecraft.packet.Packet;
import com.lexmach.client.minecraft.packet.PacketState;
import com.lexmach.client.minecraft.packet.datatype.MinecraftCustom;
import com.lexmach.client.minecraft.packet.datatype.MinecraftData;
import com.lexmach.client.minecraft.packet.datatype.VarInt;
import com.lexmach.client.minecraft.packet.exceptions.UnknownPackageException;
import com.lexmach.client.minecraft.packet.packets.hanshaking.serverbound.HandshakePacket;
import com.lexmach.client.minecraft.packet.packets.login.clientbound.EncryptionRequestPacket;
import com.lexmach.client.minecraft.packet.packets.login.clientbound.LoginSuccessPacket;
import com.lexmach.client.minecraft.packet.packets.login.clientbound.SetCompressionPacket;
import com.lexmach.client.minecraft.packet.packets.login.serverbound.LoginStartPacket;
import com.lexmach.client.minecraft.packet.packets.play.clientbound.*;
import com.lexmach.client.minecraft.packet.packets.play.serverbound.ClientChatMessagePacket;
import com.lexmach.client.minecraft.packet.packets.play.serverbound.ClientSettingsPacket;
import com.lexmach.client.minecraft.packet.packets.status.clientbound.ResponsePacket;
import com.lexmach.client.minecraft.packet.packets.status.serverbound.PingPacket;
import com.lexmach.client.minecraft.packet.packets.status.serverbound.RequestPacket;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.NotImplementedException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class PacketUtil {

    private static HashMap<PacketState, Packet> packets = new HashMap<>();

    public static void registerPacket(Packet packet) throws Exception {
        packets.put(new PacketState(packet), packet);
    }

    public static void readFully(InputStream in, byte[] arr) throws IOException {
        in.readNBytes(arr, 0, arr.length);
    }

    public static void writeToFields(Class packet, Object... fields) throws IllegalAccessException {
        Field[] packetFields = packet.getFields();
        if (packetFields.length != fields.length) throw new IndexOutOfBoundsException("Wrong fields array");
        for (int i = 0; i < packetFields.length; ++i) {
            packetFields[i].set(packet, fields[i]);
        }
    }

    public static byte[] getBytesFromObject(Object obj) {
        if (obj.getClass().isArray()) {
            byte[] headerLength = new VarInt(Array.getLength(obj)).toBytes();
            byte[][] data = new byte[Array.getLength(obj)][];
            for (int i = 0; i < Array.getLength(obj); ++i) {
                data[i] = getBytesFromObject(Array.get(obj, i));
            }
            return ArrayUtils.addAll(headerLength, Bytes.concat(data));
        }
        if (obj instanceof MinecraftData) return ((MinecraftData) obj).toBytes();
        if (obj instanceof Boolean) {
            if ((Boolean) obj) return new byte[]{0x01};
            else return new byte[]{0x00};
        }
        if (obj instanceof Byte) return new byte[]{(byte) obj};
        if (obj instanceof Short) return Shorts.toByteArray((Short) obj);
        if (obj instanceof Integer) return Ints.toByteArray((Integer) obj);
        if (obj instanceof Long) return Longs.toByteArray((Long) obj);
        if (obj instanceof Double) return getBytesFromObject(Double.doubleToLongBits((Double) obj));
        if (obj instanceof Float) return getBytesFromObject(Float.floatToIntBits((Float) obj));
        throw new NotImplementedException(obj.getClass().toString());
    }
    public static <T> T getObjectFromStream(Class<T> clazz, InputStream in) throws Exception {
        if (clazz.isArray()) {
            VarInt length = PacketUtil.getObjectFromStream(VarInt.class, in);
            T arr = (T) Array.newInstance(clazz.getComponentType(), length.num);
            for (int i = 0; i < length.num; ++i) {
                Object element = PacketUtil.getObjectFromStream(clazz.getComponentType(), in);
                Array.set(arr, i, element);
            }
            return arr;
        } else if (MinecraftCustom.class.isAssignableFrom(clazz)) {
            T obj = clazz.getDeclaredConstructor().newInstance();
            for (Field field : clazz.getFields()) {
                Object newField = PacketUtil.getObjectFromStream(field.getType(), in);
                field.set(obj, newField);
            }
            return obj;
        } else if (MinecraftData.class.isAssignableFrom(clazz)) {
            T obj = clazz.getDeclaredConstructor().newInstance();
            ((MinecraftData) obj).fromStream(in);
            return obj;
        } else if (Boolean.class.isAssignableFrom(clazz) || boolean.class.isAssignableFrom(clazz)) {
            byte[] tmp = new byte[1];
            PacketUtil.readFully(in, tmp);
            return (T) Boolean.valueOf(tmp[0] == 0x01);
        } else if (Byte.class.isAssignableFrom(clazz) || byte.class.isAssignableFrom(clazz)) {
            byte[] tmp = new byte[1];
            PacketUtil.readFully(in, tmp);
            return (T) Byte.valueOf(tmp[0]);
        } else if (Integer.class.isAssignableFrom(clazz)) {
            byte[] tmp = new byte[Ints.BYTES];
            PacketUtil.readFully(in, tmp);
            return (T) Integer.valueOf(Ints.fromByteArray(tmp));
        } else if (Short.class.isAssignableFrom(clazz)) {
            byte[] tmp = new byte[Short.BYTES];
            PacketUtil.readFully(in, tmp);
            return (T) Short.valueOf(Shorts.fromByteArray(tmp));
        } else if (Long.class.isAssignableFrom(clazz)) {
            byte[] tmp = new byte[Long.BYTES];
            PacketUtil.readFully(in, tmp);
            return (T) Long.valueOf(Longs.fromByteArray(tmp));
        } else if (Float.class.isAssignableFrom(clazz)) {
            byte[] tmp = new byte[Float.BYTES];
            PacketUtil.readFully(in, tmp);
            return (T) Float.valueOf(Float.intBitsToFloat(Ints.fromByteArray(tmp)));
        } else if (Double.class.isAssignableFrom(clazz)) {
            byte[] tmp = new byte[Double.BYTES];
            PacketUtil.readFully(in, tmp);
            return (T) Double.valueOf(Double.longBitsToDouble(Longs.fromByteArray(tmp)));
        } else {
            throw new NotImplementedException(clazz.toString());
        }
    }

    public static Packet readPacket(byte[] data, PlayerState state) throws Exception {
        InputStream dataInput = new ByteArrayInputStream(data);
        VarInt packetId = PacketUtil.getObjectFromStream(VarInt.class, dataInput);

        Packet received = PacketUtil.getPacketByPacketState(new PacketState(state, packetId.num, false));
        if (received == null) {
            throw new UnknownPackageException("Package of signature %s is unknown".formatted(new PacketState(state, packetId.num, false)));
        }
        Method read = hasSpecialRead(received);

        if (read != null) {
//            System.out.println(received.getClass().getName());
            read.invoke(received, dataInput, new VarInt(data.length));
            return received;
        }
        for (Field field : received.getClass().getFields()) {
            Object obj = emptyObject(field.getType());

            obj = PacketUtil.getObjectFromStream(obj.getClass(), dataInput);
            field.set(received, obj);
        }
        return received;
    }

    public static Object emptyObject(Class type) throws Exception {
        if (type == null) {
            return null;
        }
        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 0);
        }
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            return type.getMethod("valueOf", String.class).invoke(null, "0");
        }
    }

    public static Method hasSpecialRead(Packet packet) {
        try {
            return packet.getClass().getMethod("specialRead", InputStream.class, VarInt.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }


    public static Packet getPacketByPacketState(PacketState state) {
        return packets.get(state);
    }

    static {
        try {
            //Client packets
            registerPacket(new ClientChatMessagePacket());
            registerPacket(new ClientSettingsPacket());
            registerPacket(new HandshakePacket());
            registerPacket(new LoginStartPacket());
            registerPacket(new PingPacket());
            registerPacket(new RequestPacket());


            //Server packets
            registerPacket(new ChunkDataPacket());
            registerPacket(new DeclareCommandsPacket());
            registerPacket(new DeclareRecipesPacket());
            registerPacket(new EncryptionRequestPacket());
            registerPacket(new EntityMetadataPacket());
            registerPacket(new EntityPropertiesPacket());
            registerPacket(new EntityStatusPacket());
            registerPacket(new HeldItemChangeEvent());
            registerPacket(new JoinGamePacket());
            registerPacket(new LoginSuccessPacket());
            registerPacket(new PlayerAbilitiesPacket());
            registerPacket(new PluginMessagePacket());
            registerPacket(new ResponsePacket());
            registerPacket(new ServerChatMessagePacket());
            registerPacket(new ServerDifficultyPacket());
            registerPacket(new ServerKeepAlivePacket());
            registerPacket(new SetCompressionPacket());
            registerPacket(new SpawnLivingEntityPacket());
            registerPacket(new TagsPacket());
            registerPacket(new UnlockRecipesPacket());
            registerPacket(new PlayerPositionAndLookPacket());
            registerPacket(new PlayerInfoPacket());
            registerPacket(new UpdateViewPositionPacket());
            registerPacket(new UpdateLightPacket());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
