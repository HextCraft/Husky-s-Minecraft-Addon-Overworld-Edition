package team.abnormal.neutronia.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaPillar;

public class BlockCactusBundle extends BlockNeutroniaPillar {

    public BlockCactusBundle() {
        super(Material.CACTUS, "cactus_bundle");
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.fall(fallDistance * fallDistance, 0.2F);
    }

}