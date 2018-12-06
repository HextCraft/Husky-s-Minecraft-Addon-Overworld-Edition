package team.hdt.neutronia.base.client;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraftforge.fml.common.Loader;
import team.hdt.neutronia.base.Neutronia;
import team.hdt.neutronia.base.lib.LibMisc;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResourceProxy extends AbstractResourcePack {

    private static final String MINECRAFT = "minecraft";
    private static final String NEUTRONIA = "neutronia";
    private static final Set<String> RESOURCE_DOMAINS = ImmutableSet.of(MINECRAFT, NEUTRONIA);

    private static final String MINECRAFT_BARE_FORMAT = "assets/" + MINECRAFT + "/%s/%s";
    private static final String NEUTRONIA_BARE_FORMAT = "assets/" + NEUTRONIA + "/%s/%s";
    private static final String OVERRIDE_FORMAT = "/assets/" + LibMisc.MOD_ID + "/%s/overrides/%s";

    private static final Map<String, String> overrides = new HashMap<>();

    public ResourceProxy() {
        super(Loader.instance().activeModContainer().getSource());
        overrides.put("pack.mcmeta", "/proxypack.mcmeta");
    }

    public void addVanillaResource(String path, String file) {
        String bare = String.format(MINECRAFT_BARE_FORMAT, path, file);
        String override = String.format(OVERRIDE_FORMAT, path, file);
        overrides.put(bare, override);
    }

    public void addNeutroniaResource(String path, String file) {
        String bare = String.format(NEUTRONIA_BARE_FORMAT, path, file);
        String override = String.format(OVERRIDE_FORMAT, path, file);
        overrides.put(bare, override);
    }

    @Override
    public Set<String> getResourceDomains() {
        return RESOURCE_DOMAINS;
    }

    @Override
    protected InputStream getInputStreamByName(String name) {
        return Neutronia.class.getResourceAsStream(overrides.get(name));
    }

    @Override
    protected boolean hasResourceName(String name) {
        return overrides.containsKey(name);
    }

    @Override
    protected void logNameNotLowercase(String name) {
        // NO-OP
    }

    @Override
    public String getPackName() {
        return "neutronia-texture-proxy";
    }

}