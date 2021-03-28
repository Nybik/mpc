package com.lexmach.client.minecraft.data.reader.reports.blocks;

import com.lexmach.client.minecraft.data.datatype.block.BlockState;
import org.apache.commons.lang3.builder.JsonToStringStyleTest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Read blockStates from generated/reports/blocks.json
 * generated from minecraft server followed by this command
 * java -cp minecraft_server.jar net.minecraft.data.Main --reports
 *
 * for more info look https://wiki.vg/Data_Generators
 */
public class BlocksReader {

    public static List<BlockState> registerBlockStates(JSONObject reportBlocks) {
        List<BlockState> blockStates = new ArrayList<>();

        for (String blockStateName : reportBlocks.keySet()) {
            JSONArray states = reportBlocks.getJSONObject(blockStateName).getJSONArray("states");
            for (Object currentState : states) {
                JSONObject state = (JSONObject) currentState;

                Map<String, String> propertiesMap = new HashMap<>();
                if (state.has("properties")) {
                    JSONObject properties = state.getJSONObject("properties");
                    for (String propertyName : properties.keySet()) {
                        properties.put(propertyName, properties.getString(propertyName));
                    }
                }
                int id = (int) state.get("id");
                blockStates.add(new BlockState(blockStateName, id, propertiesMap));
            }
        }

        return blockStates;
    }

    public static List<BlockState> registerBlockStates(File file) throws IOException {

        JSONObject json = new JSONObject(Files.readString(file.toPath()));

        return registerBlockStates(json);
    }
}
