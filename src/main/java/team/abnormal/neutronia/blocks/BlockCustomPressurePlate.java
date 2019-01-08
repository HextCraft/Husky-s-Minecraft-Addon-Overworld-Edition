package team.abnormal.neutronia.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaPressurePlate;

import java.util.List;

public class BlockCustomPressurePlate extends BlockNeutroniaPressurePlate {

    public BlockCustomPressurePlate(String variant) {
        super(variant + "_pressure_plate", Material.WOOD, Sensitivity.EVERYTHING);
        setHardness(0.5F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

    public BlockCustomPressurePlate(String variant, Material material, SoundType soundType, Sensitivity sensitivity) {
        super(variant + "_pressure_plate", material, sensitivity);
        setHardness(0.5F);
        setSoundType(soundType);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

    @Override
    protected List<Entity> getValidEntities(World world, AxisAlignedBB aabb) {
        return world.getEntitiesWithinAABBExcludingEntity(null, aabb);
    }

}
