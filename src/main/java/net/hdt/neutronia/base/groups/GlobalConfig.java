package net.hdt.neutronia.base.groups;

import net.minecraftforge.common.config.Property;

public final class GlobalConfig {

    public static boolean debug;
    public static boolean enableVariants;
    public static boolean enableNButton;
    public static boolean NButtonOnRight;
    public static boolean enableExperimentalFeatures;
    public static Property NButtonProp;
    static boolean enableAntiOverlap;

    static void initGlobalConfig() {
        String category = "_global";

        ConfigHelper.needsRestart = ConfigHelper.allNeedRestart = true;

        debug = ConfigHelper.loadPropBool("Debug", category, "Enables debug features", false);

        enableAntiOverlap = ConfigHelper.loadPropBool("Enable Anti-Overlap", category,
                "Set this to false to remove the system that has components turn themselves off automatically when "
                        + "other mods are present that add similar components."
                        + "\nNote that you can force components to be enabled individually through their respective configs.", true);

        enableVariants = ConfigHelper.loadPropBool("Allow Block Variants", category,
                "Set this to false to disable stairs, slabs, and walls, mod-wide. As these blocks can use a lot of Block IDs,\n"
                        + "this is helpful to reduce the load, if you intend on running a really large modpack.\n"
                        + "Note: Blocks that require stairs and/or slabs for their recipes (such as Soul Sandstone or Midori) won't be affected.", true);

        enableExperimentalFeatures = ConfigHelper.loadPropBool("Enable Experimental Features", category,
                    "Set this to true to enable features that are either not finished, or will be added"
                        + " in a newer version", false);

        ConfigHelper.needsRestart = ConfigHelper.allNeedRestart = false;

        enableNButton = ConfigHelper.loadPropBool("Enable N Button", category,
                "Set this to false to disable the q button in the main and pause menus.\n"
                        + "If you disable this, you can still access the neutronia config from Mod Options > Neutronia > Config", true);
        NButtonProp = ConfigHelper.lastProp;

        NButtonOnRight = ConfigHelper.loadPropBool("N Button on the Right", category,
                "Set this to true to move the N button to the right of the buttons, instead\n"
                        + "of to the left as it is by default.", false);

    }

}
