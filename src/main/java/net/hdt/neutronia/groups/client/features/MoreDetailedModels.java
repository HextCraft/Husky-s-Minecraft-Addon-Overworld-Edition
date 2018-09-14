package net.hdt.neutronia.groups.client.features;

import com.google.gson.Gson;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.InputStreamReader;
import java.util.List;

public class MoreDetailedModels extends Component {

    private static final String OVERRIDES_JSON_FILE = "/assets/" + LibMisc.MOD_ID + "/models/overridden_models.json";
    private static final Gson GSON = new Gson();

    MoreDetailedModels.OverrideHolder overrides = null;

    @Override
    public void setupConfig() {
        if(overrides == null) {
            InputStreamReader reader = new InputStreamReader(Neutronia.class.getResourceAsStream(OVERRIDES_JSON_FILE));
            overrides = GSON.fromJson(reader, MoreDetailedModels.OverrideHolder.class);
        }

        for(MoreDetailedModels.OverrideEntry e : overrides.overrides)
            e.configVal = loadPropBool("Enable " + e.name, "", !e.disabled);
    }

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        overrides.overrides.forEach(MoreDetailedModels.OverrideEntry::apply);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    private static class OverrideHolder {
        List<MoreDetailedModels.OverrideEntry> overrides;
    }

    private static class OverrideEntry {

        String name;
        String[] files;
        boolean disabled = false;

        boolean configVal;

        void apply() {
            if(configVal)
                for(String file : files) {
                    String[] tokens = file.split("\\/\\/");
                    Neutronia.proxy.addVanillaResourceOverride(tokens[0], tokens[1]);
                    Neutronia.proxy.addNeutroniaResourceOverride(tokens[0], tokens[1]);
                }
        }

    }

}
