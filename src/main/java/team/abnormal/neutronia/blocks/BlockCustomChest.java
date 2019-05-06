package team.abnormal.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.abnormalib.util.ProxyRegistry;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.init.NBlocks;
import team.abnormal.neutronia.items.ItemChestBlock;
import team.abnormal.neutronia.properties.ChestType;
import team.abnormal.neutronia.tileentities.TileCustomChest;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockCustomChest extends BlockChest implements INeutroniaBlock {

    private final String[] variants;
    private final String bareName;

    public BlockCustomChest(String name, Type type) {
        super(type);

        variants = new String[]{name};
        bareName = name;
        register(name);
        setHardness(2.5F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(type == NBlocks.CUSTOM_TYPE_NEUTRONIA_TRAP ? CreativeTabs.REDSTONE : CreativeTabs.DECORATIONS);


    }

    public Block register(String name) {
        super.setTranslationKey(name);
        setRegistryName(Reference.PREFIX_MOD + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemChestBlock(this, new ResourceLocation(Reference.PREFIX_MOD + name)));

        return this;
    }

    @Override
    public String getBareName() {
        return bareName;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    @Override
    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return createBlockState().getProperties().toArray(new IProperty[0]);
    }

    @Override
    public IProperty getVariantProp() {
        return null;
    }

    @Override
    public Class getVariantEnum() {
        return null;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        ChestType myType = getCustomType(source, pos);
        return getCustomType(source, pos.north()) == myType ? NORTH_CHEST_AABB : getCustomType(source, pos.south()) == myType ? SOUTH_CHEST_AABB : getCustomType(source, pos.west()) == myType ? WEST_CHEST_AABB : getCustomType(source, pos.east()) == myType ? EAST_CHEST_AABB : NOT_CONNECTED_AABB;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        // NO-OP
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing facing = EnumFacing.byHorizontalIndex(MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, facing);
        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();
        BlockPos westPos = pos.west();
        BlockPos eastPos = pos.east();

        ChestType myType = getCustomType(stack);

        boolean northChest = myType == getCustomType(worldIn, northPos);
        boolean southChest = myType == getCustomType(worldIn, southPos);
        boolean westChest = myType == getCustomType(worldIn, westPos);
        boolean eastChest = myType == getCustomType(worldIn, eastPos);

        if (!northChest && !southChest && !westChest && !eastChest) {
            worldIn.setBlockState(pos, state, 3);
        } else if (facing.getAxis() != EnumFacing.Axis.X || !northChest && !southChest) {
            if (facing.getAxis() == EnumFacing.Axis.Z && (westChest || eastChest)) {
                if (westChest)
                    setState(worldIn, westPos, state, 3);
                else
                    setState(worldIn, eastPos, state, 3);

                worldIn.setBlockState(pos, state, 3);
            } else {
                EnumFacing corrected = facing.rotateY();
                setState(worldIn, pos, state.withProperty(FACING, corrected), 3);
                if (northChest)
                    setState(worldIn, northPos, state.withProperty(FACING, corrected), 3);
                else if (southChest)
                    setState(worldIn, southPos, state.withProperty(FACING, corrected), 3);
                else if (westChest)
                    setState(worldIn, westPos, state.withProperty(FACING, corrected), 3);
                else if (eastChest)
                    setState(worldIn, eastPos, state.withProperty(FACING, corrected), 3);
            }
        } else {
            if (northChest)
                setState(worldIn, northPos, state, 3);
            else
                setState(worldIn, southPos, state, 3);

            worldIn.setBlockState(pos, state, 3);
        }

        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileCustomChest) {
            TileCustomChest chest = (TileCustomChest) te;
            if (stack.hasDisplayName())
                chest.setCustomName(stack.getDisplayName());

            chest.chestType = myType;
        }

        onBlockAdded(worldIn, pos, state);
    }

    public void setState(World worldIn, BlockPos pos, IBlockState state, int flag) {
        TileEntity te = worldIn.getTileEntity(pos);
        worldIn.setBlockState(pos, state, flag);
        if (te != null) {
            te.validate();
            worldIn.setTileEntity(pos, te);

            if (te instanceof TileCustomChest)
                ((TileCustomChest) te).adjacentChestChecked = false;
        }
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return chestType == NBlocks.CUSTOM_TYPE_NEUTRONIA_TRAP;
    }

    @Override
    @Deprecated
    public IBlockState checkForSurroundingChests(World worldIn, BlockPos pos, IBlockState state) {
        return state;
    }

    @Override
    @Deprecated
    public IBlockState correctFacing(World worldIn, BlockPos pos, IBlockState state) {
        return correctFacing(worldIn, pos, state, ChestType.NONE);
    }

    public IBlockState correctFacing(World worldIn, BlockPos pos, IBlockState state, ChestType myType) {
        EnumFacing facing = null;

        for (EnumFacing horizFace : EnumFacing.Plane.HORIZONTAL) {
            if (getCustomType(worldIn, pos.offset(horizFace)) == myType)
                return state;

            if (worldIn.getBlockState(pos.offset(horizFace)).isFullBlock()) {
                if (facing != null) {
                    facing = null;
                    break;
                }

                facing = horizFace;
            }
        }

        if (facing != null) {
            return state.withProperty(FACING, facing.getOpposite());
        } else {
            EnumFacing enumfacing2 = state.getValue(FACING);

            if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock())
                enumfacing2 = enumfacing2.getOpposite();

            if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock())
                enumfacing2 = enumfacing2.rotateY();

            if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock())
                enumfacing2 = enumfacing2.getOpposite();

            return state.withProperty(FACING, enumfacing2);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return true;
    }

    public boolean isDoubleChest(World worldIn, BlockPos pos, ChestType myType) {
        if (getCustomType(worldIn, pos) != myType) {
            return false;
        } else {
            ChestType theType = getCustomType(worldIn, pos);
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                if (getCustomType(worldIn, pos.offset(enumfacing)) == theType)
                    return true;

            return false;
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return willHarvest || super.removedByPlayer(state, world, pos, player, false);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);

        worldIn.setBlockToAir(pos);

        if (te instanceof TileCustomChest)
            te.invalidate();
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCustomChest();
    }

    public ChestType getCustomType(IBlockAccess source, BlockPos pos) {
        if (source.getBlockState(pos).getBlock() == this) {
            TileEntity te = source.getTileEntity(pos);
            if (te instanceof TileCustomChest)
                return ((TileCustomChest) te).chestType;
        }

        return ChestType.NONE;
    }

    public static ChestType getCustomType(ItemStack stack) {
        return ChestType.class.getEnumConstants()[Math.min(9, stack.getItemDamage() + 1)];
    }

    public ItemStack setCustomType(ItemStack stack, ChestType type) {
        stack.setItemDamage(type.ordinal() - 1);

        return stack;
    }

    @Override
    public ILockableContainer getContainer(World world, BlockPos pos, boolean locked) {
        TileEntity tile = world.getTileEntity(pos);

        if (!(tile instanceof TileCustomChest)) {
            return null;
        } else {
            ILockableContainer myChest = (TileCustomChest) tile;
            ChestType myType = ((TileCustomChest) tile).chestType;

            if (!locked && isBlocked(world, pos)) {
                return null;
            } else {
                for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
                    BlockPos adjPos = pos.offset(facing);

                    TileEntity adjTile = world.getTileEntity(adjPos);

                    if (world.getBlockState(adjPos).getBlock() == this && adjTile instanceof TileCustomChest && ((TileCustomChest) adjTile).chestType == myType) {
                        if (isBlocked(world, adjPos))
                            return null;

                        if (facing != EnumFacing.WEST && facing != EnumFacing.NORTH)
                            myChest = new InventoryLargeChest("container.chestDouble", myChest, (TileCustomChest) adjTile);
                        else
                            myChest = new InventoryLargeChest("container.chestDouble", (TileCustomChest) adjTile, myChest);
                    }
                }

                return myChest;
            }
        }
    }

    private boolean isBlocked(World worldIn, BlockPos pos) {
        return isBelowSolidBlock(worldIn, pos) || isOcelotSittingOnChest(worldIn, pos);
    }

    private boolean isBelowSolidBlock(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.up()).isSideSolid(worldIn, pos.up(), EnumFacing.DOWN);
    }

    private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos) {
        for (Entity entity : worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1))) {
            EntityOcelot cat = (EntityOcelot) entity;

            if (cat.isSitting())
                return true;
        }

        return false;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return new ArrayList<>(Collections.singletonList(setCustomType(new ItemStack(this, 1), getCustomType(world, pos))));
    }


    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
     */
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     *
     * @return an approximation of the form of the given face
     * @deprecated call via {@link IBlockState#getBlockFaceShape(IBlockAccess,BlockPos,EnumFacing)} whenever possible.
     * Implementing/overriding is fine.
     */
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return setCustomType(new ItemStack(this, 1), getCustomType(world, pos));
    }

}