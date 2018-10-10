package net.hdt.neutronia.groups.building.blocks;

import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.BlockMod;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.groups.building.items.ItemVerticalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

import java.util.Random;

public class BlockVerticalSlab extends BlockMod implements INeutroniaBlock {

    public boolean isDouble;

    public BlockVerticalSlab(String name, Material materialIn, boolean isDouble)
    {
        super(name, materialIn);
        setDefaultState(blockState.getBaseState().withProperty(PROPERTYFACING, EnumFacing.NORTH));
        setCreativeTab(isDouble ? CreativeTabs.SEARCH : Neutronia.CREATIVE_TAB);
        if (isDouble)
            name += "_double";
        if(registerInConstruction())
            register(name);
        if (!isDouble) {
            useNeighborBrightness = true;
        }
    }

    public Block register(String name) {
        setRegistryName(getPrefix() + name);
        ProxyRegistry.register(this);
        if (!isDouble())
            ProxyRegistry.register(createItemBlock(new ResourceLocation(getPrefix() + name)));
        return this;
    }

    public ItemBlock createItemBlock(ResourceLocation res) {
        return new ItemVerticalSlab(this, res);
    }

    public boolean isDouble()
    {
        return isDouble;
    }

    protected boolean canSilkHarvest()
    {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if(isDouble())
            return FULL_BLOCK_AABB;
        if(state.getValue(PROPERTYFACING) == EnumFacing.NORTH)
            return AABB_NORTH;
        if(state.getValue(PROPERTYFACING) == EnumFacing.SOUTH)
            return AABB_SOUTH;
        if(state.getValue(PROPERTYFACING) == EnumFacing.EAST)
            return AABB_EAST;
        if(state.getValue(PROPERTYFACING) == EnumFacing.WEST)
            return AABB_WEST;
        else
            return FULL_BLOCK_AABB;
    }

    public boolean isTopSolid(IBlockState state)
    {
        return isDouble();
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return isDouble();
    }

    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        if(ForgeModContainer.disableStairSlabCulling)
            return super.doesSideBlockRendering(state, world, pos, face);
        if(state.isOpaqueCube())
        {
            return true;
        } else
        {
            EnumFacing facing = state.getValue(PROPERTYFACING);
            return facing == EnumFacing.NORTH && face == EnumFacing.SOUTH || facing == EnumFacing.SOUTH && face == EnumFacing.NORTH || facing == EnumFacing.EAST && face == EnumFacing.WEST || facing == EnumFacing.WEST && face == EnumFacing.EAST;
        }
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, 
            EntityLivingBase placer)
    {
        if(placer.getHorizontalFacing() == EnumFacing.NORTH)
        {
            if(facing == EnumFacing.SOUTH)
                return getDefaultState().withProperty(PROPERTYFACING, facing);
            if((double)hitZ < 0.5D && !isDouble())
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.SOUTH);
            else
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.NORTH);
        }
        if(placer.getHorizontalFacing() == EnumFacing.SOUTH)
        {
            if(facing == EnumFacing.NORTH)
                return getDefaultState().withProperty(PROPERTYFACING, facing);
            if((double)hitZ < 0.5D || isDouble())
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.SOUTH);
            else
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.NORTH);
        }
        if(placer.getHorizontalFacing() == EnumFacing.EAST)
        {
            if(facing == EnumFacing.WEST)
                return getDefaultState().withProperty(PROPERTYFACING, facing);
            if((double)hitX < 0.5D || isDouble())
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.EAST);
            else
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.WEST);
        }
        if(placer.getHorizontalFacing() == EnumFacing.WEST)
        {
            if(facing == EnumFacing.EAST)
                return getDefaultState().withProperty(PROPERTYFACING, facing);
            if((double)hitX < 0.5D && !isDouble())
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.EAST);
            else
                return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.WEST);
        } else
        {
            return getDefaultState().withProperty(PROPERTYFACING, EnumFacing.NORTH);
        }
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(PROPERTYFACING, rot.rotate(state.getValue(PROPERTYFACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(PROPERTYFACING)));
    }

    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.byHorizontalIndex(meta);
        return getDefaultState().withProperty(PROPERTYFACING, facing);
    }

    public int getMetaFromState(IBlockState state)
    {
        EnumFacing facing = state.getValue(PROPERTYFACING);
        int facingbits = facing.getHorizontalIndex();
        return facingbits;
    }

    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, PROPERTYFACING);
    }

    public int quantityDropped(Random random)
    {
        return isDouble() ? 2 : 1;
    }

    public boolean isFullCube(IBlockState state)
    {
        return isDouble();
    }

    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if(isDouble())
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        else
            return true;
    }

    public static final PropertyDirection PROPERTYFACING;
    protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
    protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    static 
    {
        PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    }
}
