package net.hdt.neutronia.groups.vanity.feature;

import com.google.common.base.Predicate;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class SitOnStairsAndSlabs extends Component {

    private static boolean canBeAbove(World world, BlockPos pos) {
        BlockPos upPos = pos.up();
        IBlockState state = world.getBlockState(upPos);
        Block block = state.getBlock();
        return block.getDefaultState().getCollisionBoundingBox(world, upPos) == null;
    }

    @SubscribeEvent
    public void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player.getRidingEntity() != null)
            return;

        World world = event.getWorld();
        BlockPos pos = event.getPos();

        Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        double maxDist = 2;
        if ((vec.x - player.posX) * (vec.x - player.posX) + (vec.y - player.posY) * (vec.y - player.posY) + (vec.z - player.posZ) * (vec.z - player.posZ) > maxDist * maxDist)
            return;

        IBlockState state = world.getBlockState(pos);

        ItemStack stack1 = player.getHeldItemMainhand();
        ItemStack stack2 = player.getHeldItemOffhand();
        if (!stack1.isEmpty() || !stack2.isEmpty())
            return;

        if (state.getBlock() instanceof BlockStairs && state.getValue(BlockStairs.HALF) == EnumHalf.BOTTOM && !state.getBlock().isSideSolid(state, world, pos, event.getFace()) && canBeAbove(world, pos)) {
            List<SeatStair> seats = world.getEntitiesWithinAABB(SeatStair.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));

            if (seats.isEmpty()) {
                SeatStair seat = new SeatStair(world, pos);
                world.spawnEntity(seat);
                event.getEntityPlayer().startRiding(seat);
            }
        }

        if (state.getBlock() instanceof BlockSlab && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM && !state.getBlock().isSideSolid(state, world, pos, event.getFace()) && canBeAbove(world, pos)) {
            List<SeatSlab> seats = world.getEntitiesWithinAABB(SeatSlab.class, new AxisAlignedBB(pos, pos.add(1, 0.5, 1)));

            if (seats.isEmpty()) {
                SeatSlab seat = new SeatSlab(world, pos);
                world.spawnEntity(seat);
                event.getEntityPlayer().startRiding(seat);
            }
        }
    }

    @SubscribeEvent
    public void onInteractWithEntity(PlayerInteractEvent.EntityInteract event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player.getRidingEntity() != null)
            return;

        World world = event.getWorld();

        if (event.getTarget() instanceof EntityChicken) {
            List<? extends Entity> seats = world.getEntities(event.getTarget().getClass(), (Predicate<Entity>) input -> true);

            if (seats.isEmpty()) {
                SeatEntity seat = new SeatEntity(player, world);
                world.spawnEntity(seat);
            }
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    public static class SeatStair extends Entity {

        SeatStair(World world, BlockPos pos) {
            this(world);
            setPosition(pos.getX() + 0.5, pos.getY() + 0.29, pos.getZ() + 0.5);
        }

        SeatStair(World par1World) {
            super(par1World);
            setSize(0F, 0F);
        }

        @Override
        public void onUpdate() {
            super.onUpdate();

            BlockPos pos = getPosition();
            if (!(getEntityWorld().getBlockState(pos).getBlock() instanceof BlockStairs) || !canBeAbove(getEntityWorld(), pos)) {
                setDead();
                return;
            }

            List<Entity> passengers = getPassengers();
            if (passengers.isEmpty())
                setDead();
            for (Entity e : passengers)
                if (e.isSneaking())
                    setDead();
        }

        @Override
        protected void entityInit() {
        }

        @Override
        protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        }

        @Override
        protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        }
    }

    public static class SeatEntity extends Entity {

        SeatEntity(EntityPlayer player, World par1World) {
            this(par1World);
            player.rotationYaw = this.rotationYaw;
            player.rotationPitch = this.rotationPitch;
        }

        SeatEntity(World worldIn) {
            super(worldIn);
            setSize(0F, 0F);
        }

        @Override
        public void onUpdate() {
            super.onUpdate();
        }

        @Override
        protected void entityInit() {
        }

        @Override
        protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        }

        @Override
        protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        }
    }

    public static class SeatSlab extends Entity {

        SeatSlab(World world, BlockPos pos) {
            this(world);

            setPosition(pos.getX() + 0.5, pos.getY() + 0.25, pos.getZ() + 0.5);
        }

        SeatSlab(World par1World) {
            super(par1World);

            setSize(0F, 0F);
        }

        @Override
        public void onUpdate() {
            super.onUpdate();

            BlockPos pos = getPosition();
            if (!(getEntityWorld().getBlockState(pos).getBlock() instanceof BlockSlab) || !canBeAbove(getEntityWorld(), pos)) {
                setDead();
                return;
            }

            List<Entity> passengers = getPassengers();
            if (passengers.isEmpty())
                setDead();
            for (Entity e : passengers)
                if (e.isSneaking())
                    setDead();
        }

        @Override
        protected void entityInit() {
        }

        @Override
        protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        }

        @Override
        protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        }
    }

}