package net.hdt.neutronia.base.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.IOException;

public class ConfigFileGenerator {

    public ConfigFileGenerator(File configFile) {
        generateConfig(configFile);
    }

    private void generateConfig(File configFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject root = new JsonObject();
        root.addProperty("_comment", "Generated using Neutronia's Config File Generator v1.");

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(configFile, StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", configFile.getName()));
        }

    }

}
