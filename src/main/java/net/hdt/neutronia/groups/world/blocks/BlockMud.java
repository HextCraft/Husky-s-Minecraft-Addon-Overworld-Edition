package net.hdt.neutronia.groups.world.blocks;

import net.hdt.neutronia.blocks.base.BlockFalling;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockMud extends BlockFalling
{
	public BlockMud()
	{
		super(Material.GROUND, "mud");
		this.setHardness(0.6F);
		this.setSoundType(SoundType.GROUND);
	}
	
	@Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
		return plantable instanceof BlockSapling || plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Plains;
	}
	

	@Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		if(!(entityIn instanceof EntityPig))
		{
	        entityIn.motionX *= 0.4D;
	        entityIn.motionZ *= 0.4D;	
		}
    }

}