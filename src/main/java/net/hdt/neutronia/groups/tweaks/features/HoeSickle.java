package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HoeSickle extends Component {

    @SubscribeEvent
    public void onBlockBroken(BlockEvent.BreakEvent event) {
        ItemStack stack = event.getPlayer().getHeldItemMainhand();
        if (!stack.isEmpty() && stack.getItem() instanceof ItemHoe && canHarvest(event.getState())) {
            World world = event.getWorld();
            EntityPlayer player = event.getPlayer();
            BlockPos basePos = event.getPos();

            int range = 1;
            if (stack.getItem() == Items.DIAMOND_HOE)
                range++;

            for (int i = -range; i < range + 1; i++)
                for (int k = -range; k < range + 1; k++) {
                    if (i == 0 && k == 0)
                        continue;

                    BlockPos pos = basePos.add(i, 0, k);
                    IBlockState state = world.getBlockState(pos);
                    if (canHarvest(state)) {
                        Block block = state.getBlock();
                        if (block.canHarvestBlock(world, pos, player))
                            block.harvestBlock(world, player, pos, state, world.getTileEntity(pos), stack);
                        world.setBlockToAir(pos);
                        world.playEvent(2001, pos, Block.getIdFromBlock(block) + (block.getMetaFromState(state) << 12));
                    }
                }

            stack.damageItem(1, player);
        }
    }

    private boolean canHarvest(IBlockState state) {
        Block block = state.getBlock();
        return (block instanceof BlockBush && !(block instanceof BlockLilyPad)) || block instanceof BlockReed;
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}
