/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hdt.neutronia_legacy.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.class_2902.Type;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.TagCompound;
import net.minecraft.util.TagHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public abstract class class_3732 extends HostileEntity {
    private BlockPos field_16478;
    private boolean field_16479;
    private boolean field_16477;

    protected class_3732(EntityType<?> var1, World var2) {
        super(var1, var2);
    }

    protected void method_5959() {
        super.method_5959();
        this.goalSelector.add(4, new class_3732.class_3733(this, 0.7D, 0.595D));
    }

    public void serializeCustomData(TagCompound var1) {
        super.serializeCustomData(var1);
        if (this.field_16478 != null) {
            var1.setTag("PatrolTarget", TagHelper.serializeBlockPos(this.field_16478));
        }

        var1.setBoolean("PatrolLeader", this.field_16479);
        var1.setBoolean("Patrolling", this.field_16477);
    }

    public void deserializeCustomData(TagCompound var1) {
        super.deserializeCustomData(var1);
        if (var1.containsKey("PatrolTarget")) {
            this.field_16478 = TagHelper.deserializeBlockPos(var1.getTagCompound("PatrolTarget"));
        }

        this.field_16479 = var1.getBoolean("PatrolLeader");
        this.field_16477 = var1.getBoolean("Patrolling");
    }

    public double getHeightOffset() {
        return -0.45D;
    }

    @Nullable
    public EntityData method_5943(IWorld var1, LocalDifficulty var2, class_3730 var3, @Nullable EntityData var4, @Nullable TagCompound var5) {
        if (this.method_16219()) {
            this.setEquippedStack(EquipmentSlot.HEAD, class_3765.field_16609);
            this.setEquipmentDropChance(EquipmentSlot.HEAD, 2.0F);
        }

        if (var3 == class_3730.PATROL) {
            this.field_16477 = true;
        }

        return super.method_5943(var1, var2, var3, var4, var5);
    }

    public boolean method_5974(double var1) {
        return !this.field_16477 || var1 > 16384.0D;
    }

    public void method_16216(BlockPos var1) {
        this.field_16478 = var1;
        this.field_16477 = true;
    }

    public BlockPos method_16215() {
        return this.field_16478;
    }

    public boolean method_16220() {
        return this.field_16478 != null;
    }

    public void method_16217(boolean var1) {
        this.field_16479 = var1;
        this.field_16477 = true;
    }

    public boolean method_16219() {
        return this.field_16479;
    }

    public boolean method_16472() {
        return true;
    }

    public void method_16218() {
        this.field_16478 = (new BlockPos(this)).add(-500 + this.rand.nextInt(1000), 0, -500 + this.rand.nextInt(1000));
        this.field_16477 = true;
    }

    public static class class_3733<T extends class_3732> extends AiGoal {
        private final T field_16481;
        private final double field_16480;
        private final double field_16535;

        public class_3733(T var1, double var2, double var4) {
            this.field_16481 = var1;
            this.field_16480 = var2;
            this.field_16535 = var4;
            this.setCategoryBits(1);
        }

        public boolean canStart() {
            return this.field_16481.getTarget() == null && !this.field_16481.hasPassengers() && this.field_16481.method_16220();
        }

        public void start() {
        }

        public void onRemove() {
        }

        public void tick() {
            boolean var1 = this.field_16481.method_16219();
            EntityNavigation var2 = this.field_16481.method_5942();
            if (var2.method_6357()) {
                double var3 = this.field_16481.squaredDistanceTo(this.field_16481.method_16215());
                if (var1 && var3 < 100.0D) {
                    this.field_16481.method_16218();
                } else {
                    Vec3d var5 = new Vec3d(this.field_16481.method_16215());
                    Vec3d var6 = new Vec3d(this.field_16481.x, this.field_16481.y, this.field_16481.z);
                    Vec3d var7 = var6.subtract(var5);
                    var5 = var7.rotateY(90.0F).multiply(0.4D).add(var5);
                    Vec3d var8 = var5.subtract(var6).normalize().multiply(10.0D).add(var6);
                    BlockPos var9 = new BlockPos((int)var8.x, (int)var8.y, (int)var8.z);
                    var9 = this.field_16481.world.getTopPosition(Type.MOTION_BLOCKING_NO_LEAVES, var9);
                    if (!var2.method_6337((double)var9.getX(), (double)var9.getY(), (double)var9.getZ(), var1 ? this.field_16535 : this.field_16480)) {
                        this.method_16222();
                    } else if (var1) {
                        List<class_3732> var10 = this.field_16481.world.getEntities(class_3732.class, this.field_16481.getBoundingBox().expand(16.0D), (var0) -> {
                            return !var0.method_16219() && var0.method_16472();
                        });
                        Iterator var11 = var10.iterator();

                        while(var11.hasNext()) {
                            class_3732 var12 = (class_3732)var11.next();
                            var12.method_16216(var9);
                        }
                    }
                }
            }

        }

        private void method_16222() {
            Random var1 = this.field_16481.getRand();
            BlockPos var2 = this.field_16481.world.getTopPosition(Type.MOTION_BLOCKING_NO_LEAVES, (new BlockPos(this.field_16481)).add(-8 + var1.nextInt(16), 0, -8 + var1.nextInt(16)));
            this.field_16481.method_5942().method_6337((double)var2.getX(), (double)var2.getY(), (double)var2.getZ(), this.field_16480);
        }
    }
}
*/
