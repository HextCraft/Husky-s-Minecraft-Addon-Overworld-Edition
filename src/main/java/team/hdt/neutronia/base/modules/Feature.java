package team.hdt.neutronia.base.modules;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.text.WordUtils;
import team.hdt.neutronia.base.Reference;

import java.util.List;

public class Feature implements Comparable<Feature> {

    public Module module;
    public FeatureStateManager stateManager = new FeatureStateManager(this);
    public String configCategory;
    public Property prop;
    public String configName;
    boolean loadtimeDone;
    boolean enabledAtLoadtime;
    boolean prevEnabled;
    boolean forceLoad;

    public Feature(boolean enabled) {
        stateManager.enabled = enabled;
    }

    public Feature(boolean enabled, boolean enabledByDefault) {
        stateManager.enabled = enabled;
        stateManager.enabledByDefault = enabledByDefault;
    }

    public Feature() {
        this(true, true);
    }

    public static void registerTile(Class<? extends TileEntity> clazz, String key) {
        GameRegistry.registerTileEntity(clazz, Reference.PREFIX_MOD + key);
    }

    final void setupConstantConfig() {
        String[] incompat = getIncompatibleMods();
        if (incompat != null && incompat.length > 0) {
            StringBuilder desc = new StringBuilder("This feature disables itself if any of the following mods are loaded: \n");
            for (String s : incompat)
                desc.append(" - ").append(s).append("\n");
            desc.append("This is done to prevent content overlap.\nYou can turn this on to force the feature to be loaded even if the above mods are also loaded.");

            ConfigHelper.needsRestart = true;
            forceLoad = loadPropBool("Force Enabled", desc.toString(), false);
        }
    }

    public void setupConfig() {
        // NO-OP
    }

    public void onEnabled() {
        // NO-OP
    }

    public void onDisabled() {
        // NO-OP
    }

    public void preInit(FMLPreInitializationEvent event) {
        // NO-OP
    }

    public void postPreInit(FMLPreInitializationEvent event) {
        // NO-OP
    }

    public void init(FMLInitializationEvent event) {
        // NO-OP
    }

    public void postInit(FMLPostInitializationEvent event) {
        // NO-OP
    }

    public void finalInit(FMLPostInitializationEvent event) {
        // NO-OP
    }

    public void postFinalInit(FMLPostInitializationEvent event) {
        // NO-OP
    }

    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {
        // NO-OP
    }

    @SideOnly(Side.CLIENT)
    public void initClient(FMLInitializationEvent event) {
        // NO-OP
    }

    @SideOnly(Side.CLIENT)
    public void postInitClient(FMLPostInitializationEvent event) {
        // NO-OP
    }

    public void serverStarting(FMLServerStartingEvent event) {
        // NO-OP
    }

    public void serverStopped(FMLServerStoppedEvent event) {
        // NO-OP
    }

    public String[] getIncompatibleMods() {
        return null;
    }

    public boolean hasSubscriptions() {
        return false;
    }

    public boolean hasTerrainSubscriptions() {
        return false;
    }

    public boolean hasOreGenSubscriptions() {
        return false;
    }

    public String getFeatureDescription() {
        return "";
    }

    public String getFeatureInGameConfigName() {
        return WordUtils.capitalizeFully(configName);
    }

    public boolean requiresMinecraftRestartToEnable() {
        return hasSubscriptions() || hasOreGenSubscriptions() || hasTerrainSubscriptions();
    }

    public final boolean isClient() {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    public final int loadPropInt(String propName, String desc, String comment, int default_, int min, int max) {
        return ConfigHelper.loadPropInt(propName, configCategory, desc, comment, default_, min, max);
    }

    public final int loadPropInt(String propName, String desc, int default_) {
        return ConfigHelper.loadPropInt(propName, configCategory, desc, default_);
    }

    protected final double loadPropDouble(String propName, String desc, double default_) {
        return ConfigHelper.loadPropDouble(propName, configCategory, desc, default_);
    }

    public final boolean loadPropBool(String propName, String desc, boolean default_) {
        return ConfigHelper.loadPropBool(propName, configCategory, desc, default_);
    }

    protected final String loadPropString(String propName, String desc, String default_) {
        return ConfigHelper.loadPropString(propName, configCategory, desc, default_);
    }

    protected final String[] loadPropStringList(String propName, String desc, String[] default_) {
        return ConfigHelper.loadPropStringList(propName, configCategory, desc, default_);
    }

    protected final List<ItemStack> loadItemStackList(String propName, String comment, String[] default_) {
        return ConfigHelper.loadItemStackList(propName, configCategory, comment, default_);
    }

    @Override
    public int compareTo(Feature o) {
        return configName.compareTo(o.configName);
    }

    public boolean isEnabledAtLoadtime() {
        return enabledAtLoadtime;
    }

    public boolean isPrevEnabled() {
        return prevEnabled;
    }

}
