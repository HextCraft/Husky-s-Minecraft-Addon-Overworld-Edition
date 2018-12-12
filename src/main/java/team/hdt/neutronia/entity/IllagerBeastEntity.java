/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hdt.neutronia.entity;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.class_1361;
import net.minecraft.class_1366;
import net.minecraft.class_1394;
import net.minecraft.class_1399;
import net.minecraft.class_1400;
import net.minecraft.class_1543;
import net.minecraft.class_3763;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.StateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.AiGoalSwim;
import net.minecraft.entity.ai.pathing.EntityMobNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeMakerLand;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.TagCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.Sound;
import net.minecraft.sound.Sounds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class IllagerBeastEntity extends class_3763 {
    private static final Predicate<Entity> field_7301 = (var0) -> {
        return var0.isValid() && !(var0 instanceof IllagerBeastEntity);
    };
    private int field_7303;
    private int field_7302;
    private int field_7305;

    public IllagerBeastEntity(World var1) {
        super(EntityType.ILLAGER_BEAST, var1);
        this.setSize(1.95F, 2.2F);
        this.stepHeight = 1.0F;
        this.experiencePoints = 20;
    }

    protected void method_5959() {
        super.method_5959();
        this.goalSelector.add(0, new AiGoalSwim(this));
        this.goalSelector.add(4, new IllagerBeastEntity.class_1585());
        this.goalSelector.add(5, new class_1394(this, 0.4D));
        this.goalSelector.add(6, new class_1361(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(10, new class_1361(this, MobEntity.class, 8.0F));
        this.targetSelector.add(2, (new class_1399(this, new Class[]{class_1543.class})).method_6318(new Class[0]));
        this.targetSelector.add(3, new class_1400(this, PlayerEntity.class, true));
        this.targetSelector.add(4, new class_1400(this, VillagerEntity.class, true));
        this.targetSelector.add(4, new class_1400(this, IronGolemEntity.class, true));
    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
        this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
        this.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK).setBaseValue(1.5D);
        this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
    }

    public void serializeCustomData(TagCompound var1) {
        super.serializeCustomData(var1);
        var1.setInt("AttackTick", this.field_7303);
        var1.setInt("StunTick", this.field_7302);
        var1.setInt("RoarTick", this.field_7305);
    }

    public void deserializeCustomData(TagCompound var1) {
        super.deserializeCustomData(var1);
        this.field_7303 = var1.getInt("AttackTick");
        this.field_7302 = var1.getInt("StunTick");
        this.field_7305 = var1.getInt("RoarTick");
    }

    protected EntityNavigation createNavigation(World var1) {
        return new IllagerBeastEntity.class_1586(this, var1);
    }

    public int method_5986() {
        return 45;
    }

    public double getMountedHeightOffset() {
        return 2.1D;
    }

    public boolean method_5956() {
        return true;
    }

    @Nullable
    public Entity method_5642() {
        return this.getPassengerList().isEmpty() ? null : (Entity)this.getPassengerList().get(0);
    }

    public void updateMovement() {
        super.updateMovement();
        if (this.method_6062()) {
            this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        } else {
            double var1 = this.getTarget() != null ? 0.35D : 0.3D;
            double var3 = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getBaseValue();
            this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(MathHelper.lerp(0.1D, var3, var1));
        }

        if (this.field_5976 && this.world.getGameRules().getBoolean("mobGriefing")) {
            boolean var7 = false;
            BoundingBox var2 = this.getBoundingBox().expand(0.2D);
            Iterator var8 = BlockPos.method_10068(MathHelper.floor(var2.minX), MathHelper.floor(var2.minY), MathHelper.floor(var2.minZ), MathHelper.floor(var2.maxX), MathHelper.floor(var2.maxY), MathHelper.floor(var2.maxZ)).iterator();

            label58:
            while(true) {
                Mutable var4;
                Block var6;
                do {
                    if (!var8.hasNext()) {
                        if (!var7 && this.onGround) {
                            this.method_6043();
                        }
                        break label58;
                    }

                    var4 = (Mutable)var8.next();
                    StateBlock var5 = this.world.getBlockState(var4);
                    var6 = var5.getBlock();
                } while(!(var6 instanceof LeavesBlock));

                var7 = this.world.breakBlock(var4, true) || var7;
            }
        }

        if (this.field_7305 > 0) {
            --this.field_7305;
            if (this.field_7305 == 10) {
                this.method_7071();
            }
        }

        if (this.field_7303 > 0) {
            --this.field_7303;
        }

        if (this.field_7302 > 0) {
            --this.field_7302;
            this.method_7073();
            if (this.field_7302 == 0) {
                this.playSoundAtEntity(Sounds.ENTITY_ILLAGER_BEAST_ROAR, 1.0F, 1.0F);
                this.field_7305 = 20;
            }
        }

    }

    private void method_7073() {
        if (this.rand.nextInt(6) == 0) {
            double var1 = this.x - (double)this.width * Math.sin((double)(this.field_6283 * 0.017453292F)) + (this.rand.nextDouble() * 0.6D - 0.3D);
            double var3 = this.y + (double)this.height - 0.3D;
            double var5 = this.z + (double)this.width * Math.cos((double)(this.field_6283 * 0.017453292F)) + (this.rand.nextDouble() * 0.6D - 0.3D);
            this.world.method_8406(ParticleTypes.ENTITY_EFFECT, var1, var3, var5, 0.4980392156862745D, 0.5137254901960784D, 0.5725490196078431D);
        }

    }

    protected boolean method_6062() {
        return super.method_6062() || this.field_7303 > 0 || this.field_7302 > 0 || this.field_7305 > 0;
    }

    public boolean canSee(Entity var1) {
        return this.field_7302 <= 0 && this.field_7305 <= 0 ? super.canSee(var1) : false;
    }

    protected void method_6060(LivingEntity var1) {
        if (this.field_7305 == 0) {
            if (this.rand.nextDouble() < 0.5D) {
                this.field_7302 = 40;
                this.playSoundAtEntity(Sounds.ENTITY_ILLAGER_BEAST_STUNNED, 1.0F, 1.0F);
                this.world.method_8421(this, (byte)39);
                var1.pushAwayFrom(this);
            } else {
                this.method_7068(var1);
            }

            var1.field_6037 = true;
        }

    }

    private void method_7071() {
        if (this.isValid()) {
            List<Entity> var1 = this.world.getEntities(LivingEntity.class, this.getBoundingBox().expand(4.0D), field_7301);

            Entity var3;
            for(Iterator var2 = var1.iterator(); var2.hasNext(); this.method_7068(var3)) {
                var3 = (Entity)var2.next();
                if (!(var3 instanceof class_1543)) {
                    var3.damage(DamageSource.byMob(this), 6.0F);
                }
            }

            Vec3d var10 = this.getBoundingBox().getCenter();

            for(int var11 = 0; var11 < 40; ++var11) {
                double var4 = this.rand.nextGaussian() * 0.2D;
                double var6 = this.rand.nextGaussian() * 0.2D;
                double var8 = this.rand.nextGaussian() * 0.2D;
                this.world.method_8406(ParticleTypes.POOF, var10.x, var10.y, var10.z, var4, var6, var8);
            }
        }

    }

    private void method_7068(Entity var1) {
        double var2 = var1.x - this.x;
        double var4 = var1.z - this.z;
        double var6 = var2 * var2 + var4 * var4;
        var1.addVelocity(var2 / var6 * 4.0D, 0.2D, var4 / var6 * 4.0D);
    }

    @Sided(Side.CLIENT)
    public void method_5711(byte var1) {
        if (var1 == 4) {
            this.field_7303 = 10;
            this.playSoundAtEntity(Sounds.ENTITY_ILLAGER_BEAST_ATTACK, 1.0F, 1.0F);
        } else if (var1 == 39) {
            this.field_7302 = 40;
        }

        super.method_5711(var1);
    }

    @Sided(Side.CLIENT)
    public int method_7070() {
        return this.field_7303;
    }

    @Sided(Side.CLIENT)
    public int method_7074() {
        return this.field_7302;
    }

    @Sided(Side.CLIENT)
    public int method_7072() {
        return this.field_7305;
    }

    public boolean method_6121(Entity var1) {
        this.field_7303 = 10;
        this.world.method_8421(this, (byte)4);
        this.playSoundAtEntity(Sounds.ENTITY_ILLAGER_BEAST_ATTACK, 1.0F, 1.0F);
        return super.method_6121(var1);
    }

    @Nullable
    protected Sound getSoundAmbient() {
        return Sounds.ENTITY_ILLAGER_BEAST_AMBIENT;
    }

    protected Sound getSoundHurt(DamageSource var1) {
        return Sounds.ENTITY_ILLAGER_BEAST_HURT;
    }

    protected Sound getSoundDeath() {
        return Sounds.ENTITY_ILLAGER_BEAST_DEATH;
    }

    protected void playStepSound(BlockPos var1, StateBlock var2) {
        this.playSoundAtEntity(Sounds.ENTITY_ILLAGER_BEAST_STEP, 0.15F, 1.0F);
    }

    public boolean method_5957(ViewableWorld var1) {
        return !var1.method_8599(this.getBoundingBox()) && var1.method_8587(this, this.getBoundingBox());
    }

    public void method_16484(int var1, boolean var2) {
    }

    protected boolean method_16485() {
        return false;
    }

    static class class_1587 extends PathNodeMakerLand {
        private class_1587() {
        }

        protected PathNodeType method_61(BlockView var1, boolean var2, boolean var3, BlockPos var4, PathNodeType var5) {
            return var5 == PathNodeType.LEAVES ? PathNodeType.AIR : super.method_61(var1, var2, var3, var4, var5);
        }
    }

    static class class_1586 extends EntityMobNavigation {
        public class_1586(MobEntity var1, World var2) {
            super(var1, var2);
        }

        protected PathNodeNavigator createPathNodeNavigator() {
            this.field_6678 = new IllagerBeastEntity.class_1587();
            return new PathNodeNavigator(this.field_6678);
        }
    }

    class class_1585 extends class_1366 {
        public class_1585() {
            super(IllagerBeastEntity.this, 1.0D, true);
        }

        protected double method_6289(LivingEntity var1) {
            float var2 = IllagerBeastEntity.this.width - 0.1F;
            return (double)(var2 * 2.0F * var2 * 2.0F + var1.width);
        }
    }
}
*/
