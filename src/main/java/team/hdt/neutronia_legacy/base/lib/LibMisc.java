package team.hdt.neutronia_legacy.base.lib;

import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public final class LibMisc {

    // Logger
    public static final Logger LOGGER = LogManager.getLogger("Neutronia");

    // Mod Constants
    public static final String MOD_ID = "neutronia_legacy";
    public static final String MOD_NAME = "Neutronia";
    public static final String VERSION = "0.4.3-1";
    public static final String DEPENDENCIES = "required-before:huskylib@[1.6.3,);";
    public static final String PREFIX_MOD = MOD_ID + ":";

    // Proxy Constants
    public static final String PROXY_COMMON = "team.hdt.neutronia_legacy.base.proxy.CommonProxy";
    public static final String PROXY_CLIENT = "team.hdt.neutronia_legacy.base.proxy.ClientProxy";
    public static final String GUI_FACTORY = "team.hdt.neutronia_legacy.base.client.gui.GuiFactory";

    public static final List<String> OREDICT_DYES = Arrays.asList("dyeBlack",
            "dyeRed",
            "dyeGreen",
            "dyeBrown",
            "dyeBlue",
            "dyePurple",
            "dyeCyan",
            "dyeLightGray",
            "dyeGray",
            "dyePink",
            "dyeLime",
            "dyeYellow",
            "dyeLightBlue",
            "dyeMagenta",
            "dyeOrange",
            "dyeWhite");

    public static final ResourceLocation GENERAL_ICONS_RESOURCE = new ResourceLocation(MOD_ID, "textures/misc/general_icons.png");
    public static final ResourceLocation GENERAL_ICONS_RESOURCE2 = new ResourceLocation(MOD_ID, "textures/misc/general_icons_1.png");

    public static final String MOD_WEBSITE = "https://github.com/HuskysDevelopmentTeam/Neutronia";
    public static final String MOD_DISCORD = "https://discord.gg/TR9rcQK";

    public static ResourceLocation location(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

}