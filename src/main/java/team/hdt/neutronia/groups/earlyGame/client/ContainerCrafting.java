package team.hdt.neutronia.groups.earlyGame.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ContainerCrafting extends Container {
    World worldIn;
    BlockPos pos;
    Random r = new Random();
    int i = 0;

    public ContainerCrafting(World worldIn, BlockPos pos) {
        this.worldIn = worldIn;
        this.pos = pos;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        if (!worldIn.isRemote && i > 0) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.DIRT, r.nextInt(2) + 1));
            super.onContainerClosed(playerIn);
        }
        i++;
    }

}