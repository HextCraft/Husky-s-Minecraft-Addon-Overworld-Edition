package net.hdt.neutronia_addon;

import net.hdt.neutronia_addon.modules.NAGroups;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "neutronia_addon", name = "Neutronia Addon", version = "0.0.1")
public class NeutroniaAddon {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NAGroups.registerGroups();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
