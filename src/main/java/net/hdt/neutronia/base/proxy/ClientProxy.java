package net.hdt.neutronia.base.proxy;

import net.hdt.neutronia.base.client.ContributorRewardHandler;
import net.hdt.neutronia.base.client.ResourceProxy;
import net.hdt.neutronia.base.client.gui.ConfigEvents;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.base.lib.LibObfuscation;
import net.hdt.neutronia.base.util.Localization;
import net.hdt.neutronia.base.util.handlers.EntityEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.Timer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.Display;

import java.util.List;

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
        Display.setTitle(String.format("%s %s | Minecraft 1.12.2", LibMisc.MOD_NAME, LibMisc.VERSION));
        Localization.initialize(Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(EntityEventHandler.class);
//        MinecraftForge.EVENT_BUS.register(new GuiDrawHandler());
        GroupLoader.preInitClient(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        GroupLoader.initClient(event);
        MinecraftForge.EVENT_BUS.register(ConfigEvents.class);
        ContributorRewardHandler.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        GroupLoader.postInitClient(event);
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
