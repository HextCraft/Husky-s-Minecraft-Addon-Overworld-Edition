package team.hdt.neutronia_legacy.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockChain extends BlockMod implements INeutroniaBlock {

    public BlockChain(String name) {
        super(name + "_chain", Material.IRON);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    public BlockChain(Material material, String name) {
        super(name, material);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}