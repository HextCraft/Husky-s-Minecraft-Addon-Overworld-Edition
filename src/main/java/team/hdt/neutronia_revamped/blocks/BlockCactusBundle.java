package team.hdt.neutronia_revamped.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia_revamped.base.blocks.BlockNeutroniaBase;

public class BlockCactusBundle extends BlockNeutroniaBase {

    public BlockCactusBundle() {
        super("cactus_bundle", Material.CACTUS);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance, 0.2F);
    }

}