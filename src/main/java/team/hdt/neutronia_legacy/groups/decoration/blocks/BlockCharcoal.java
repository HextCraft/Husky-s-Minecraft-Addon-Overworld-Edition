package team.hdt.neutronia_legacy.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;

public class BlockCharcoal extends BlockMod implements INeutroniaBlock {

    public BlockCharcoal() {
        super("charcoal_block", Material.ROCK);
        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return true;
    }

}