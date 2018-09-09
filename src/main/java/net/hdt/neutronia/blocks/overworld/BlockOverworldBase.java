package net.hdt.neutronia.blocks.overworld;

import net.hdt.huskylib2.block.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.init.NCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;

public class BlockOverworldBase extends BlockMod implements INeutroniaBlock {

    public BlockOverworldBase(Material material, String name, boolean flammable) {
        super(name, material);
        setCreativeTab(NCreativeTabs.OVERWORLD_EXPANSION_TAB);
        if (flammable) {
            addFlammable(this);
        }
    }

    public BlockOverworldBase(Material material, String name, boolean flammable, String... variants) {
        super(name, material, variants);
        setCreativeTab(NCreativeTabs.OVERWORLD_EXPANSION_TAB);
        if (flammable) {
            addFlammable(this);
        }
    }

    public static void addFlammable(Block block) {
        Blocks.FIRE.setFireInfo(block, 5, 20);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
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
