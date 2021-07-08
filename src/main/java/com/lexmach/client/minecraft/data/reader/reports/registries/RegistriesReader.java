package com.lexmach.client.minecraft.data.reader.reports.registries;

import com.lexmach.client.minecraft.data.datatype.NamespacedID;
import com.lexmach.client.minecraft.data.datatype.Registry;
import com.lexmach.client.minecraft.data.datatype.block.BlockState;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Read blockStates from generated/reports/blocks.json
 * generated from minecraft server followed by this command
 * java -cp minecraft_server.jar net.minecraft.data.Main --registries
 *
 * for more info look https://wiki.vg/Data_Generators
 */
public class RegistriesReader {

    public static List<Registry> registerRegistries(JSONObject reportBlocks) {
        List<Registry> registries = new ArrayList<>();

        for (String registryName : reportBlocks.keySet()) {
            JSONObject states = reportBlocks.getJSONObject(registryName).getJSONObject("entries");

            List<NamespacedID> entries = new ArrayList<>();
            for (String registryEntry : states.keySet()) {
                //TODO add parameters support
                entries.add(new NamespacedID(registryEntry));
            }

            registries.add(new Registry(new NamespacedID(registryName), entries));
        }

        return registries;
    }

    public static List<Registry> registerRegistries(File file) throws IOException {

        JSONObject json = new JSONObject(Files.readString(file.toPath()));

        return registerRegistries(json);
    }
}
