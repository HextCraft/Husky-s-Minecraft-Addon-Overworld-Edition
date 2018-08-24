package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RemoveSnowLayers extends Component {

    @SubscribeEvent
    public void onPlayerRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (event.getFace() != EnumFacing.UP)
            return;

        World world = event.getWorld();
        BlockPos pos = event.getPos();
        EntityPlayer player = event.getEntityPlayer();

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != Blocks.SNOW_LAYER && state.getBlock() != Blocks.SNOW)
            return;

        ItemStack stack = event.getEntityPlayer().getHeldItem(event.getHand());

        if (!stack.isEmpty() && stack.getItem() instanceof ItemSpade) {
            if (state.getBlock() == Blocks.SNOW)
                world.setBlockState(pos, Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, 7));
            else {
                int layers = state.getValue(BlockSnow.LAYERS);
                if (layers == 1)
                    world.setBlockToAir(pos);
                else world.setBlockState(pos, state.withProperty(BlockSnow.LAYERS, layers - 1));
            }

            world.playSound(event.getEntityPlayer(), pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            stack.damageItem(1, event.getEntityPlayer());
            player.swingArm(event.getHand());
            event.setCanceled(true);
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public String getComponentIngameConfigName() {
        return "Shave Snow Layers";
    }

}
