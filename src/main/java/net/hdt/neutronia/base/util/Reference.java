package net.hdt.neutronia.base.util;

import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Loader;

import java.io.File;

public class Reference {

    public static final String MOD_ID = LibMisc.MOD_ID;
    public static final String NAME = LibMisc.MOD_NAME;
    public static final String VERSION = LibMisc.VERSION;
    public static final String DEPENDENCIES = LibMisc.DEPENDENCIES;
    public static final String UPDATE_JSON = "https://gist.githubusercontent.com/sindrefag/be19331edf8ae73628450304918911cc/raw/2618e4673854b1155a1acff8aabd58114a2c44c7/update_json_neutronia.json";
    public static final String CLIENT_PROXY = LibMisc.PROXY_CLIENT;
    public static final String SERVER_PROXY = LibMisc.PROXY_COMMON;
    public static final boolean IS_DEV_ENV = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    public static final File CONFIG_DIRECTORY = Loader.instance().getConfigDir();

}
