package com.lexmach.client.minecraft.packet.datatype;

import de.piegames.nbt.Tag;
import de.piegames.nbt.stream.NBTInputStream;
import de.piegames.nbt.stream.NBTOutputStream;
import org.apache.commons.lang3.NotImplementedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VarTag implements MinecraftData {
    Tag NBTTag;

    @Override
    public byte[] toBytes() {
        try {
            var byteOutput = new ByteArrayOutputStream();
            var out= new NBTOutputStream(byteOutput, 0);
            out.writeTag(NBTTag);
            byte[] arr = byteOutput.toByteArray();
            Tag check = new NBTInputStream(new ByteArrayInputStream(arr),0).readTag();
            assert (check.equals(NBTTag));
            return byteOutput.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void fromStream(InputStream in) throws IOException {
        var NBT = new NBTInputStream(in, 0);
        NBTTag = NBT.readTag();
    }

    @Override
    public String toString() {
        return "VarTag{" +
                "NBTTag=" + NBTTag +
                '}';
    }
}
