package net.hdt.neutronia.base;

import net.hdt.neutronia.base.lib.LibMisc;
import net.hdt.neutronia.base.proxy.CommonProxy;
import net.hdt.neutronia.base.util.Reference;
import net.hdt.neutronia.events.ILifeCycleHandler;
import net.hdt.neutronia.events.handlers.EventHandlers;
import net.hdt.neutronia.init.NEnchantments;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.hdt.neutronia.base.util.Reference.*;

@Mod(modid = MOD_ID, name = NAME, version = VERSION, dependencies = DEPENDENCIES, guiFactory = LibMisc.GUI_FACTORY, updateJSON = UPDATE_JSON)
public class Neutronia {

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(I18n.format("itemGroup.main")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.BOOKSHELF);
        }
    };
    public static Logger LOGGER;
    @Mod.Instance
    public static Neutronia instance;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;
    private List<ILifeCycleHandler> handlers = new ArrayList<ILifeCycleHandler>() {{
        add(new EventHandlers());
    }};

    public static DamageSource func_203096_a(Entity p_203096_0_, @Nullable Entity p_203096_1_) {
        return (new EntityDamageSourceIndirect("trident", p_203096_0_, p_203096_1_)).setProjectile();
    }

    public static int func_203191_f(ItemStack p_203191_0_) {
        return EnchantmentHelper.getEnchantmentLevel(Objects.requireNonNull(NEnchantments.LOYALTY), p_203191_0_);
    }

    public static int func_203190_g(ItemStack p_203190_0_) {
        return EnchantmentHelper.getEnchantmentLevel(NEnchantments.RIPTIDE, p_203190_0_);
    }

    public static boolean func_203192_h(ItemStack p_203192_0_) {
        return EnchantmentHelper.getEnchantmentLevel(Objects.requireNonNull(NEnchantments.CHANNELING), p_203192_0_) > 0;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle(String.format("%s %s | Minecraft 1.13.1", LibMisc.MOD_NAME, LibMisc.VERSION));
        LOGGER = event.getModLog();
        handlers.forEach(handler -> handler.preInit(event));
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        handlers.forEach(handler -> handler.init(event));
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        handlers.forEach(handler -> handler.postInit(event));
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        handlers.forEach(handler -> handler.loadComplete(event));
    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        handlers.forEach(handler -> handler.serverInit(event));
    }

}