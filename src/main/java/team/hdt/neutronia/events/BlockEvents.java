package team.hdt.neutronia.events;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.groups.building.features.LogBlocks;

public class BlockEvents {

    @SubscribeEvent
    public static void onBlockRightClicked(PlayerInteractEvent.RightClickBlock e) {
        World world = e.getWorld();
        BlockPos pos = e.getPos();
        IBlockState state = world.getBlockState(pos);
        ItemStack stack = e.getItemStack();
        EntityPlayer player = e.getEntityPlayer();
        if (stack.getItem().getToolClasses(stack).contains("axe") && player.getCooldownTracker().getCooldown(stack.getItem(), 0) <= 0) {
            if (state instanceof BlockLog) {
                for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
                    player.sendStatusMessage(new TextComponentString(String.format("Broke a block called %s at [X: %s, Y:%s, Z:%s] and replaced with %s", state.getBlock().getRegistryName(), pos.getX(), pos.getY(), pos.getZ(), LogBlocks.strippedLogs[type.getMetadata()].getRegistryName())), false);
                    world.setBlockState(pos, LogBlocks.strippedLogs[type.getMetadata()].getDefaultState());
                }
            }
        }
    }

}