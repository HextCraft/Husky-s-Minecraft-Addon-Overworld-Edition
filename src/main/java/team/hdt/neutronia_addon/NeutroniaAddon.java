package team.hdt.neutronia_addon;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "neutronia_addon", name = "Neutronia Addon", version = "0.0.1", dependencies = "required-before:neutronia")
public class NeutroniaAddon {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TestGroups.init();
    }

}