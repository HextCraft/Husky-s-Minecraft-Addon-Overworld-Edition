package team.hdt.neutronia.base.util;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Loader;
import team.hdt.neutronia.base.lib.LibMisc;

import java.io.File;

public class Reference {

    public static final String MOD_ID = LibMisc.MOD_ID;
    public static final String NAME = LibMisc.MOD_NAME;
    public static final String VERSION = LibMisc.VERSION;
    public static final String DEPENDENCIES = LibMisc.DEPENDENCIES;
    public static final String UPDATE_JSON = "https://github.com/HuskysDevelopmentTeam/Neutronia/tree/module-system/update.json";
    public static final String CLIENT_PROXY = LibMisc.PROXY_CLIENT;
    public static final String SERVER_PROXY = LibMisc.PROXY_COMMON;
    public static final boolean IS_DEV_ENV = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    public static final File CONFIG_DIRECTORY = Loader.instance().getConfigDir();

    public static final int GUI_DIRT_CRAFTING_TABLE = 1;

}
