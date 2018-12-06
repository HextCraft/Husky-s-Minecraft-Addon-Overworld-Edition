package team.hdt.neutronia.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia.entity.ai.EntityAIFlyingWander;
import team.hdt.neutronia.entity.ai.EntityAISeekRainShelter;
import team.hdt.neutronia.entity.movement.FlightMoveHelper;

public class EntityBird extends EntityFlyingMob {

    public EntityBird(World worldIn) {
        super(worldIn);
        setSize(0.5F, 0.5F);
        this.moveHelper = new FlightMoveHelper(this);
        setPathPriority(PathNodeType.WATER, -8F);
        setPathPriority(PathNodeType.BLOCKED, -8.0F);
        setPathPriority(PathNodeType.OPEN, 8.0F);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return this.world.isRainingAt(pos) ? -1.0F : 0.0F;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISeekRainShelter(this, 0.8D));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIFlyingWander(this, 0.5D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.035D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (getEntityWorld().getBlockState(getPosition().down()).isSideSolid(getEntityWorld(), getPosition().down(), EnumFacing.UP))
            getMoveHelper().setMoveTo(this.posX, this.posY + 1, this.posZ, 0.32D);
    }

    @Override
    protected boolean canDespawn() {
        return true;
    }

}
