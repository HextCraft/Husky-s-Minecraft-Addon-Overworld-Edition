package net.hdt.neutronia.base.proxy;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.client.ResourceProxy;
import net.hdt.neutronia.base.client.gui.ConfigEvents;
import net.hdt.neutronia.base.client.gui.GuiReplacementEvents;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.lib.LibObfuscation;
import net.hdt.neutronia.base.util.handlers.EntityEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.Timer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.List;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ClientProxy extends CommonProxy {

    public static final Minecraft minecraft = Minecraft.getMinecraft();
    private static final Timer timer = ReflectionHelper.getPrivateValue(Minecraft.class, ClientProxy.minecraft, "timer", "field_71428_T", "aa");
    private static ResourceProxy resourceProxy;

    static {
        List<IResourcePack> packs = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), LibObfuscation.DEFAULT_RESOURCE_PACKS);
        resourceProxy = new ResourceProxy();
        packs.add(resourceProxy);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(EntityEventHandler.class);
        Neutronia.MODULE_LOADER.preInitClient(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Neutronia.MODULE_LOADER.initClient(event);
        MinecraftForge.EVENT_BUS.register(GuiReplacementEvents.class);
        MinecraftForge.EVENT_BUS.register(ConfigEvents.class);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        Neutronia.MODULE_LOADER.postInitClient(event);
    }

    @Override
    public void addVanillaResourceOverride(String path, String file) {
        resourceProxy.addVanillaResource(path, file);
    }

    @Override
    public void addNeutroniaResourceOverride(String path, String file) {
        resourceProxy.addNeutroniaResource(path, file);
    }

    @Override
    public float getPartialTicks() {
        return ClientProxy.timer.renderPartialTicks;
    }

}
