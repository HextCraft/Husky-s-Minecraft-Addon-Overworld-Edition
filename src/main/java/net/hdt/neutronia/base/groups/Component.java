package net.hdt.neutronia.base.groups;

import net.hdt.neutronia.base.handler.client.ClientEventHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class Component implements Comparable<Component> {

    public Group group;
    public boolean enabledByDefault;
    public boolean enabled;
    public String configCategory;
    public Property prop;
    boolean loadtimeDone;
    boolean enabledAtLoadtime;
    boolean prevEnabled;
    String configName;
    boolean forceLoad;

    public Component(boolean enabled) {
        this.enabled = enabled;
    }

    public Component() {
        this(true);
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredBlocks(Block[] coloredBlocks) {
        ClientEventHandler.blocks.add(coloredBlocks);
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredSlabs(Block[] coloredSlabSingle, Block[] coloredSlabDouble) {
        ClientEventHandler.slabs.add(coloredSlabSingle);
        ClientEventHandler.slabs.add(coloredSlabDouble);
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredStairs(Block[] coloredBlocks) {
        ClientEventHandler.stairs.add(coloredBlocks);
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredWalls(Block[] coloredBlocks) {
        ClientEventHandler.walls.add(coloredBlocks);
    }

    final void setupConstantConfig() {
        String[] incompat = getIncompatibleMods();
        if (incompat != null && incompat.length > 0) {
            StringBuilder desc = new StringBuilder("This component disables itself if any of the following mods are loaded: \n");
            for (String s : incompat)
                desc.append(" - ").append(s).append("\n");
            desc.append("This is done to prevent content overlap.\nYou can turn this on to force the component to be loaded even if the above mods are also loaded.");

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

    public String getComponentInGameConfigName() {
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
    public int compareTo(Component o) {
        return configName.compareTo(o.configName);
    }

    public boolean isEnabledByDefault() {
        return enabledByDefault;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isEnabledAtLoadtime() {
        return enabledAtLoadtime;
    }

    public boolean isPrevEnabled() {
        return prevEnabled;
    }

}