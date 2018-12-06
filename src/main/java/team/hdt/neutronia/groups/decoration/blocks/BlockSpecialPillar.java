package team.hdt.neutronia.groups.decoration.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;
import team.hdt.neutronia.groups.decoration.features.MetalAndMineralPillars;

public class BlockSpecialPillar extends BlockNeutroniaPillar implements INeutroniaBlock {

    public BlockSpecialPillar(Material material, String name, CreativeTabs creativeTab, float hardness, float resistance) {
        super(material, name);

        setCreativeTab(creativeTab);
        setHardness(hardness);
        setResistance(resistance);
    }

    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return this == MetalAndMineralPillars.diamondPillar || this == MetalAndMineralPillars.emeraldPillar || this == MetalAndMineralPillars.goldPillar || this == MetalAndMineralPillars.ironPillar;
    }

}