package net.hdt.neutronia.groups.tweaks.features;

import com.google.common.collect.Lists;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Objects;

import static net.hdt.neutronia.groups.tweaks.features.HCMovement.dirtpathQuality;

public class GrassPath extends Component {
    public static List<ItemStack> SHOVEL_BLACKLIST = Lists.newArrayList();

    public static int getShovelQuality(ItemStack stack) {
        Item item = stack.getItem();
        if (!item.getToolClasses(stack).contains("shovel"))
            return -1;
        if (!dirtpathQuality) {
            return 3;
        } else {
            return item.getHarvestLevel(stack, "shovel", null, null);
        }
    }

    @Override
    public void setupConfig() {
        SHOVEL_BLACKLIST.add(new ItemStack(Objects.requireNonNull(Item.getByNameOrId("psi:cad"))));
    }

    @Override
    public String getDescription() {
        return "Allows turning more than just grass into path. Turns off when dirt2path is installed";
    }

    @Override
    public String[] getIncompatibleMods() {
        return new String[]{"dirt2path"};
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    private boolean isBlockDirt(IBlockState state) {
        return state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS;
    }

    private void setPathOrDirt(World world, IBlockState blockState, BlockPos blockPos, SoundEvent soundEvent, EntityPlayer player, ItemStack itemStack, EnumHand hand) {
        world.playSound(player, blockPos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
        player.swingArm(hand);
        if (!world.isRemote) {
            world.setBlockState(blockPos, blockState, 11);
            itemStack.damageItem(1, player);
        }
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (event.getResult() != Event.Result.DEFAULT || event.isCanceled())
            return;

        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos blockPos = event.getPos();
        ItemStack stack = player.getHeldItem(event.getHand());

        IBlockState iBlockState = world.getBlockState(blockPos);

        if (stack.isEmpty() || !isBlockDirt(iBlockState))
            return;

        int quality = getShovelQuality(stack);
        if (quality == -1)
            return;
        if (quality < 2) {
            event.setCanceled(true);
            return;
        }

        if (world.getBlockState(blockPos.up()).getMaterial() == Material.AIR) {
            if (isBlockDirt(iBlockState)) {
                IBlockState pathState = Blocks.GRASS_PATH.getDefaultState();
                setPathOrDirt(world, pathState, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, player, stack, event.getHand());
            }
        }
    }

}
