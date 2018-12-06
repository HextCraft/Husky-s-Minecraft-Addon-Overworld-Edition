package team.hdt.neutronia.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;
import team.hdt.neutronia.groups.decoration.features.MetalAndMineralBricks;

public class BlockSpecialBrick extends BlockMod implements INeutroniaBlock {

    public BlockSpecialBrick(Material material, String name, CreativeTabs creativetab, float hardness, float resistance) {
        super(name, material);

        setCreativeTab(creativetab);
        setHardness(hardness);
        setResistance(resistance);
    }

    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return this == MetalAndMineralBricks.diamondBricks || this == MetalAndMineralBricks.emeraldBricks || this == MetalAndMineralBricks.goldBricks || this == MetalAndMineralBricks.ironBricks;
    }

}