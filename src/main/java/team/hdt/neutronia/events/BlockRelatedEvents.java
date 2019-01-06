package team.hdt.neutronia.events;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.Reference;
import team.hdt.neutronia.blocks.BlockWood;
import team.hdt.neutronia.init.NBlocks;
import team.hdt.neutronia.init.NSoundEvents;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class BlockRelatedEvents {

    private static Map<IBlockState, Block> BLOCK_STRIPPING_MAP = (new ImmutableMap.Builder<IBlockState, Block>())
            .put(NBlocks.WOOD[0].getDefaultState(), NBlocks.STRIPPED_WOOD[0]).put(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK), NBlocks.STRIPPED_LOGS[0])
            .put(NBlocks.WOOD[5].getDefaultState(), NBlocks.STRIPPED_WOOD[5]).put(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK), NBlocks.STRIPPED_LOGS[5])
            .put(NBlocks.WOOD[4].getDefaultState(), NBlocks.STRIPPED_WOOD[4]).put(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA), NBlocks.STRIPPED_LOGS[4])
            .put(NBlocks.WOOD[2].getDefaultState(), NBlocks.STRIPPED_WOOD[2]).put(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH), NBlocks.STRIPPED_LOGS[2])
            .put(NBlocks.WOOD[3].getDefaultState(), NBlocks.STRIPPED_WOOD[3]).put(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), NBlocks.STRIPPED_LOGS[3])
            .put(NBlocks.WOOD[1].getDefaultState(), NBlocks.STRIPPED_WOOD[1]).put(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), NBlocks.STRIPPED_LOGS[1]).build();

    @SubscribeEvent
    public static void onLogStripped(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos blockpos = event.getPos();
        IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = BLOCK_STRIPPING_MAP.get(iblockstate);

        if(event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemAxe) {
            if(iblockstate.getBlock() instanceof BlockLog) {
                EntityPlayer entityplayer = event.getEntityPlayer();
                world.playSound(entityplayer, blockpos, NSoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote) {
                    Reference.LOGGER.info(iblockstate.getBlock().getRegistryName() + " -> " + block.getRegistryName());
                    world.setBlockState(blockpos, block.getDefaultState().withProperty(BlockLog.LOG_AXIS, iblockstate.getValue(BlockLog.LOG_AXIS)), 11);
                    if (entityplayer != null) {
                        event.getItemStack().getItem().setDamage(event.getItemStack(), 1);
                    }
                }
            }
            if(iblockstate.getBlock() instanceof BlockWood) {
                EntityPlayer player = event.getEntityPlayer();
                world.playSound(player, blockpos, NSoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote) {
                    world.setBlockState(blockpos, block.getDefaultState(), 11);
                    if (player != null) {
                        event.getItemStack().getItem().setDamage(event.getItemStack(), 1);
                    }
                }
            }
        }
    }

}
