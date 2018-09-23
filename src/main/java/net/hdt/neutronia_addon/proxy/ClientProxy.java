package net.hdt.neutronia_addon.proxy;

import net.hdt.neutronia.base.groups.GroupLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber(modid = "neutronia_addon")
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        GroupLoader.preInitClient(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        GroupLoader.initClient(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        GroupLoader.postInitClient(event);
    }

}
