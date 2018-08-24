package net.hdt.neutronia.base.groups;

import net.hdt.neutronia.base.BWMRecipes;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.handler.client.ClientHandler;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
    private String iconFile = "";
    private ResourceLocation icon;
    private boolean isColored;

    public Component() {
    }

    public Component(Builder builder) {

    }

    public static void registerTile(Class<? extends TileEntity> clazz, String key) {
        GameRegistry.registerTileEntity(clazz, new ResourceLocation(LibMisc.PREFIX_MOD + key));
    }

    public static IRecipe registerHardcoreRecipe(String ID, IRecipe recipe) {
        BWMRecipes.addHardcoreRecipe(ID, recipe);
        return recipe;
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredBlocks(Block[] coloredBlocks) {
        ClientHandler.blocks.add(coloredBlocks);
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredSlabs(Block[] coloredSlabSingle, Block[] coloredSlabDouble) {
        ClientHandler.slabs.add(coloredSlabSingle);
        ClientHandler.slabs.add(coloredSlabDouble);
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredStairs(Block[] coloredBlocks) {
        ClientHandler.stairs.add(coloredBlocks);
    }

    @SideOnly(Side.CLIENT)
    public static void addColoredWalls(Block[] coloredBlocks) {
        ClientHandler.walls.add(coloredBlocks);
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

    public String getComponentIngameConfigName() {
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

    public final double loadPropDouble(String propName, String desc, double default_) {
        return ConfigHelper.loadPropDouble(propName, configCategory, desc, default_);
    }

    public final boolean loadPropBool(String propName, String desc, boolean default_) {
        return ConfigHelper.loadPropBool(propName, configCategory, desc, default_);
    }

    public final String loadPropString(String propName, String desc, String default_) {
        return ConfigHelper.loadPropString(propName, configCategory, desc, default_);
    }

    public final String[] loadPropStringList(String propName, String desc, String[] default_) {
        return ConfigHelper.loadPropStringList(propName, configCategory, desc, default_);
    }

    public final HashSet<String> loadPropStringHashSet(String propName, String desc, String[] default_) {
        return new HashSet<>(Arrays.asList(ConfigHelper.loadPropStringList(propName, configCategory, desc, default_)));
    }


    public final int[] loadPropIntList(String propName, String comment, int[] default_) {
        return ConfigHelper.loadPropIntList(propName, configCategory, comment, default_);
    }


    public final ItemStack[] loadItemStackArray(String propName, String comment, ItemStack[] default_) {
        return ConfigHelper.loadItemStackArray(propName, configCategory, comment, default_);
    }

    public final List<ItemStack> loadItemStackList(String propName, String comment, ItemStack[] default_) {
        return ConfigHelper.loadItemStackList(propName, configCategory, comment, default_);
    }

    public final List<ItemStack> loadItemStackList(String propName, String comment, String[] default_) {
        return ConfigHelper.loadItemStackList(propName, configCategory, comment, default_);
    }

    public final HashMap<Ingredient, Integer> loadItemStackIntMap(String propName, String comment, String[] _default) {
        return ConfigHelper.loadItemStackIntMap(propName, configCategory, comment, _default);
    }

    public final boolean loadRecipeCondition(String jsonName, String propName, String comment, boolean _default) {
        return ConfigHelper.loadRecipeCondition(jsonName, propName, configCategory, comment, _default);
    }

    protected void addHardcoreRecipe(IRecipe recipe) {
        registerHardcoreRecipe(getClass().getSimpleName(), recipe);
    }

    public void overrideBlock(String str) {
        Neutronia.proxy.addResourceOverride("textures", "block", str, "png");
    }

    public void overrideItem(String str) {
        Neutronia.proxy.addResourceOverride("textures", "items", str, "png");
    }

    @Override
    public int compareTo(Component o) {
        return configName.compareTo(o.configName);
    }

    public static class Builder {

        private String name, desc;
        private ItemStack icon;
        private Component component;
        private boolean enabled;

        public Builder(Component component) {
            this.component = component;
        }

        public Component.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Component.Builder withDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Component.Builder withIcon(ItemStack icon) {
            this.icon = icon;
            return this;
        }

        public Component.Builder withEnabled(boolean isEnabled) {
            this.enabled = isEnabled;
            return this;
        }

        public Component register() {
            return new Component(this);
        }

    }

}
