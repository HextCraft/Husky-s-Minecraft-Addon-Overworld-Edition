package team.hdt.neutronia_legacy.groups.building.blocks.slab;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_legacy.base.blocks.BlockNeutroniaColoredSlab;

public class BlockVanillaColoredSlab extends BlockNeutroniaColoredSlab {

    public BlockVanillaColoredSlab(String name, EnumDyeColor color, IBlockState state, boolean doubleSlab) {
        super(color.getName() + "_" + name, color, state.getMaterial(), doubleSlab);

        setHardness(state.getBlockHardness(null, new BlockPos(0, 0, 0)));
        setResistance(state.getBlock().getExplosionResistance(null) * 5F / 3F);
        setSoundType(state.getBlock().getSoundType());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

}
