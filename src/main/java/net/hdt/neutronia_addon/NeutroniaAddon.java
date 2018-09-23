package net.hdt.neutronia_addon;

import net.hdt.neutronia_addon.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "neutronia_addon", name = "Neutronia Addon", version = "0.0.1", dependencies = "required-before:neutronia;")
public class NeutroniaAddon {

    private static final String PROXY_COMMON = "net.hdt.neutronia_addon.proxy.CommonProxy";
    private static final String PROXY_CLIENT = "net.hdt.neutronia_addon.proxy.ClientProxy";

    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
