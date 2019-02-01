package team.abnormal.neutronia.blocks.pumpkin;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.blocks.BlockMinecraftBase;

public class BlockPumpkin extends BlockMinecraftBase {

    public BlockPumpkin(PumpkinHelper.FaceTypes type) {
        super("pumpkin" + type.ordinal(), Material.GOURD);
    }

    /*
     * default pumpkin, used for registry replacement
     */
    public BlockPumpkin(){
        super("pumpkin", Material.GOURD);
        PumpkinHelper.init();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack used = playerIn.getHeldItem(hand);
        if(used.getItem() == Items.SHEARS){
            worldIn.setBlockState(pos, PumpkinHelper.getNext(this.getRegistryName()));
            return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
