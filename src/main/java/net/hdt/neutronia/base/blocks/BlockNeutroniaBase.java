package net.hdt.neutronia.base.blocks;

import net.hdt.huskylib2.block.BlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;

public class BlockNeutroniaBase extends BlockMod implements INeutroniaBlock {

    public BlockNeutroniaBase(Material material, String name, boolean flammable) {
        super(name, material);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        if (flammable) {
            addFlammable(this);
        }
    }

    public BlockNeutroniaBase(Builder builder, String name) {
        super(builder, name);
    }

    public BlockNeutroniaBase(Material material, String name) {
        super(name, material);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    public BlockNeutroniaBase(Material material, String name, boolean flammable, String... variants) {
        super(name, material, variants);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        if (flammable) {
            addFlammable(this);
        }
    }

    public BlockNeutroniaBase(Material material, String name, float hardness, float resistance, float slipperiness, float lightValue, SoundType soundType) {
        super(name, material);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(hardness);
        setResistance(resistance);
        setDefaultSlipperiness(slipperiness);
        setLightLevel(lightValue);
        setSoundType(soundType);
    }

    public static void addFlammable(Block block) {
        Blocks.FIRE.setFireInfo(block, 5, 20);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * @deprecated call via {@link IBlockState#isFullCube()} whenever possible. Implementing/overriding is fine.
     */
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

}
