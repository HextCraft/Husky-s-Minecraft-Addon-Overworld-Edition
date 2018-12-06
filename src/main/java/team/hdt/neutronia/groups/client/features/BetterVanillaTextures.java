package team.hdt.neutronia.groups.client.features;

import com.google.gson.Gson;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.Neutronia;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.base.lib.LibMisc;

import java.io.InputStreamReader;
import java.util.List;

public class BetterVanillaTextures extends Component {

    private static final String OVERRIDES_JSON_FILE = "/assets/" + LibMisc.MOD_ID + "/overrides.json";
    private static final Gson GSON = new Gson();

    OverrideHolder overrides = null;

    @Override
    public void setupConfig() {
        if (overrides == null) {
            InputStreamReader reader = new InputStreamReader(Neutronia.class.getResourceAsStream(OVERRIDES_JSON_FILE));
            overrides = GSON.fromJson(reader, OverrideHolder.class);
        }

        for (OverrideEntry e : overrides.overrides)
            e.configVal = loadPropBool("Enable " + e.name, "", !e.disabled);
    }

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        overrides.overrides.forEach(OverrideEntry::apply);
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    private static class OverrideHolder {

        List<OverrideEntry> overrides;

    }

    private static class OverrideEntry {

        String name;
        String[] files;
        boolean disabled = false;

        boolean configVal;

        void apply() {
            if (configVal)
                for (String file : files) {
                    String[] tokens = file.split("\\/\\/");
                    Neutronia.proxy.addVanillaResourceOverride(tokens[0], tokens[1]);
                }
        }

    }

}
