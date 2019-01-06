package team.hdt.neutronia.events;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.base.Reference;
import team.hdt.neutronia.init.NBlocks;
import team.hdt.neutronia.tileentities.TileCustomChest;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
public class RenderingRelatedEvents {

    @SubscribeEvent
    public static void onRegisterModel(ModelRegistryEvent event) {

        TileCustomChest customChest = new TileCustomChest();
        Item.getItemFromBlock(NBlocks.CUSTOM_CHEST).setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
            @Override
            public void renderByItem(ItemStack itemStackIn) {
                TileEntityRendererDispatcher.instance.render(customChest, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            }
        });
        Item.getItemFromBlock(NBlocks.CUSTOM_TRAPPED_CHEST).setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
            @Override
            public void renderByItem(ItemStack itemStackIn) {
                TileEntityRendererDispatcher.instance.render(customChest, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            }
        });
    }

}
