package net.hdt.neutronia.base.groups;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.Group;
import net.hdt.neutronia.base.groups.GroupLoader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.IOException;

public class ConfigFileGenerator {

    public ConfigFileGenerator(File configFile, Group group) {
        generateConfig(configFile, group);
    }

    private void generateConfig(File configFile, Group group) {
        Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();

        JsonObject root = new JsonObject();
        root.addProperty("name", group.name);
        root.addProperty("description", group.desc);
        root.addProperty("icon_stack", group.getIconStack().getItem().getRegistryName().toString());
        root.addProperty("enabled", group.enabled);

        JsonObject feature = new JsonObject();

        for(Component component : group.enabledComponents) {
            feature.addProperty("name", component.getComponentIngameConfigName());
            feature.addProperty("description", component.getFeatureDescription());
        }

        JsonArray features = new JsonArray();
        features.add(feature);

        root.add("features", features);

        String json = gson.toJson(root);

        try {
            FileUtils.writeStringToFile(configFile, StringEscapeUtils.unescapeJson(json), CharEncoding.UTF_8);
        } catch (IOException e) {
            System.out.print(String.format("Error creating file %s.json" + "\n", configFile.getName()));
        }

    }

}
