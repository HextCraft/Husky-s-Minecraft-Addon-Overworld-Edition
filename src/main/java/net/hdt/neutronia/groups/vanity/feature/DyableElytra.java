package net.hdt.neutronia.groups.vanity.feature;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.lib.LibObfuscation;
import net.hdt.neutronia.groups.tweaks.util.ItemNBTHelper;
import net.hdt.neutronia.groups.vanity.client.layer.LayerBetterElytra;
import net.hdt.neutronia.groups.vanity.recipes.ElytraDyingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;

public class DyableElytra extends Component {

    public static final String TAG_ELYTRA_DYE = "neutronia:elytraDye";

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        new ElytraDyingRecipe();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void postInitClient(FMLPostInitializationEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        RenderManager manager = mc.getRenderManager();
        Map<String, RenderPlayer> renders = manager.getSkinMap();
        for (RenderPlayer render : renders.values())
            messWithRender(render);

        mc.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            int color = ItemNBTHelper.getInt(stack, TAG_ELYTRA_DYE, -1);
            if (color == -1 || color == 15)
                return -1;

            return ItemDye.DYE_COLORS[color];
        }, Items.ELYTRA);
    }

    @SideOnly(Side.CLIENT)
    private void messWithRender(RenderPlayer render) {
        List<LayerRenderer> list = ReflectionHelper.getPrivateValue(RenderLivingBase.class, render, LibObfuscation.LAYER_RENDERERS);
        LayerRenderer remove = null;
        for (LayerRenderer layer : list)
            if (layer instanceof LayerElytra) {
                remove = layer;
                break;
            }

        list.remove(remove);
        list.add(new LayerBetterElytra(render));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty() && stack.getItem() == Items.ELYTRA) {
            int color = ItemNBTHelper.getInt(stack, TAG_ELYTRA_DYE, 15);
            EnumDyeColor dye = EnumDyeColor.byDyeDamage(color);
            if (dye != EnumDyeColor.WHITE)
                event.getToolTip().add(I18n.format("neutronia.dyedElytra", I18n.format("neutronia.dye." + dye.getTranslationKey())));
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return isClient();
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}