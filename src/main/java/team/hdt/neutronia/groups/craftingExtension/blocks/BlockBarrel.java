package team.hdt.neutronia.groups.craftingExtension.blocks;

import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockBarrel extends BlockFacing implements INeutroniaBlock {

    public BlockBarrel(BlockPlanks.EnumType type) {
        super(String.format("%s_barrel", type.getName()), Material.WOOD);
    }

    /*@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBarrel();
    }*/

}