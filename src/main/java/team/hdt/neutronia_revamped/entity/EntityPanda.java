package team.hdt.neutronia_revamped.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_revamped.Reference;
import team.hdt.neutronia_revamped.init.NBlocks;
import team.hdt.neutronia_revamped.init.NSoundEvents;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class EntityPanda extends EntityAnimal {

    private static final DataParameter<Integer> field_6764;
    private static final DataParameter<Integer> field_6771;
    private static final DataParameter<Integer> field_6780;
    private static final DataParameter<Byte> field_6766;
    private static final DataParameter<Byte> field_6781;
    private static final DataParameter<Byte> field_6768;
    private boolean field_6769;
    private boolean field_6770;
    public int field_6767;
    private double field_6776;
    private double field_6778;
    private float field_6777;
    private float field_6779;
    private float field_6774;
    private float field_6775;
    private float field_6772;
    private float field_6773;
    private static final Predicate<EntityItem> field_6765;

    public EntityPanda(World worldIn) {
        super(worldIn);
        this.setSize(1.3F, 1.25F);
        this.moveHelper = new EntityPanda.PandaMoveControl(this);
        if(!isChild()) {
            this.setCanPickUpLoot(true);
        }
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entityLivingBase) {
        super.setAttackTarget(entityLivingBase);
        if (entityLivingBase instanceof EntityPlayer) {
            List<EntityVillager> var2 = this.world.getEntitiesWithinAABB(EntityVillager.class, this.getEntityBoundingBox().grow(10.0D));
            boolean var3 = false;
            Iterator var4 = var2.iterator();

            while(var4.hasNext()) {
                EntityVillager var5 = (EntityVillager) var4.next();
                if (var5.isValid()) {
                    this.world.getClosestPlayerToEntity(var5, (byte)13);
                    if (!var3) {
                        VillageProperties var6 = var5.method_7232();
                        if (var6 != null) {
                            var3 = true;
                            var6.addAttacker(var1);
                            int var7 = -1;
                            if (this.isChild()) {
                                var7 = -3;
                            }

                            var6.method_6393(((PlayerEntity)var1).getGameProfile().getName(), var7);
                        }
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return new EntityPanda(world);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        this.method_6529(this.method_6543());
        this.method_6547(this.method_6543());
        this.method_6538();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void method_6515(EntityPanda var1, @Nullable EntityPanda var2) {
        if (var2 == null) {
            if (this.rand.nextBoolean()) {
                this.method_6529(var1.method_6519());
                this.method_6547(this.method_6543());
            } else {
                this.method_6529(this.method_6543());
                this.method_6547(var1.method_6519());
            }
        } else if (this.rand.nextBoolean()) {
            this.method_6529(var1.method_6519());
            this.method_6547(var2.method_6519());
        } else {
            this.method_6529(var2.method_6519());
            this.method_6547(var1.method_6519());
        }

        if (this.rand.nextInt(32) == 0) {
            this.method_6529(this.method_6543());
        }

        if (this.rand.nextInt(32) == 0) {
            this.method_6547(this.method_6543());
        }

    }

    private EntityPanda.class_1443 method_6519() {
        return this.rand.nextBoolean() ? this.method_6525() : this.method_6508();
    }

    public void method_6538() {
        if (this.method_6550()) {
            this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(10.0D);
        }

        if (this.method_6549()) {
            this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.07000000029802322D);
        }

    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack var3 = player.getHeldItem(hand);
        if (var3.getItem() instanceof ItemMonsterPlacer) {
            return super.processInteract(player, hand);
        } else if (this.method_6524()) {
            return false;
        } else if (this.method_6514()) {
            this.method_6505(false);
            return true;
        } else if (this.method_6481(var3)) {
            if (this.getRevengeTarget() != null) {
                this.field_6769 = true;
            }

            if (this.isChild()) {
                this.method_6475(var1, var3);
                this.method_5620((int)((float)(-this.getGrowingAge() / 20) * 0.1F), true);
            } else if (!this.world.isRemote && this.getGrowingAge() == 0 && this.method_6482()) {
                this.method_6475(var1, var3);
                this.method_6480(var1);
            } else {
                if (this.world.isRemote || this.method_6535()) {
                    return false;
                }

                this.method_6513(true);
                this.method_6552(true);
                ItemStack var4 = this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                if (!var4.isEmpty() && !player.capabilities.isCreativeMode) {
                    this.entityDropItem(var4, 0.0F);
                }

                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(var3.getItem(), 1));
                this.method_6475(var1, var3);
            }

            return true;
        } else {
            return false;
        }
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        if (this.method_6510()) {
            return NSoundEvents.ENTITY_PANDA_AGGRESSIVE_AMBIENT;
        } else {
            return this.method_6509() ? NSoundEvents.ENTITY_PANDA_WORRIED_AMBIENT : NSoundEvents.ENTITY_PANDA_AMBIENT;
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(NSoundEvents.ENTITY_PANDA_STEP, 0.15F, 1.0F);
    }

    public boolean method_6481(ItemStack var1) {
        return var1.getItem() == NBlocks.BAMBOO.getItem();
    }

    private boolean method_16106(ItemStack var1) {
        return this.method_6481(var1) || var1.getItem() == Item.getItemFromBlock(Blocks.CAKE);
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return NSoundEvents.ENTITY_PANDA_DEATH;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource var1) {
        return NSoundEvents.ENTITY_PANDA_HURT;
    }

    static {
        field_6764 = EntityDataManager.createKey(EntityPanda.class, DataSerializers.VARINT);
        field_6771 = EntityDataManager.createKey(EntityPanda.class, DataSerializers.VARINT);
        field_6780 = EntityDataManager.createKey(EntityPanda.class, DataSerializers.VARINT);
        field_6766 = EntityDataManager.createKey(EntityPanda.class, DataSerializers.BYTE);
        field_6781 = EntityDataManager.createKey(EntityPanda.class, DataSerializers.BYTE);
        field_6768 = EntityDataManager.createKey(EntityPanda.class, DataSerializers.BYTE);
        field_6765 = (var0) -> {
            Item var1 = var0.getItem().getItem();
            return (var1 == NBlocks.BAMBOO.getItem() || var1 == Blocks.CAKE.getItem()) && var0.isValid() && !var0.cannotPickup();
        };
    }

    public EntityPanda.class_1443 method_6525() {
        return EntityPanda.class_1443.method_6566(this.dataManager.get(field_6766));
    }

    public void method_6529(EntityPanda.class_1443 var1) {
        if (var1.method_6564() > 6) {
            var1 = this.method_6543();
        }

        this.dataManager.set(field_6766, (byte)var1.method_6564());
    }

    public EntityPanda.class_1443 method_6508() {
        return EntityPanda.class_1443.method_6566((Byte)this.dataManager.get(field_6781));
    }

    static class PandaMoveControl extends EntityMoveHelper {
        private final EntityPanda panda;

        public PandaMoveControl(EntityPanda var1) {
            super(var1);
            this.panda = var1;
        }

        @Override
        public void onUpdateMoveHelper() {
            if (!this.panda.method_6535() && !this.panda.method_6514() && !this.panda.method_6524()) {
                super.onUpdateMoveHelper();
            }
        }
    }

    public enum class_1443 {
        ENTITY_PANDA_PANDA_TEXTURE(0, "normal", false, "textures/entity/panda/panda.png"),
        ENTITY_PANDA_LAZY_PANDA_TEXTURE(1, "lazy", false, "textures/entity/panda/lazy_panda.png"),
        ENTITY_PANDA_WORRIED_PANDA_TEXTURE(2, "worried", false, "textures/entity/panda/worried_panda.png"),
        ENTITY_PANDA_PLAYFUL_PANDA_TEXTURE(3, "playful", false, "textures/entity/panda/playful_panda.png"),
        ENTITY_PANDA_BROWN_PANDA_TEXTURE(4, "brown", true, "textures/entity/panda/brown_panda.png"),
        ENTITY_PANDA_WEAK_PANDA_TEXTURE(5, "weak", true, "textures/entity/panda/weak_panda.png"),
        ENTITY_PANDA_AGGRESSIVE_PANDA_TEXTURE(6, "aggressive", false, "textures/entity/panda/aggressive_panda.png");

        private static final EntityPanda.class_1443[] field_6786 = Arrays.stream(values()).sorted(Comparator.comparingInt(EntityPanda.class_1443::method_6564)).toArray(class_1443[]::new);
        private final int field_6785;
        private final String field_6797;
        private final boolean field_6790;
        private final ResourceLocation field_6787;

        private class_1443(int var3, String var4, boolean var5, String var6) {
            this.field_6785 = var3;
            this.field_6797 = var4;
            this.field_6790 = var5;
            this.field_6787 = new ResourceLocation(Reference.MOD_ID, var6);
        }

        public int method_6564() {
            return this.field_6785;
        }

        public String method_6565() {
            return this.field_6797;
        }

        public boolean method_6568() {
            return this.field_6790;
        }

        private static EntityPanda.class_1443 method_6569(EntityPanda.class_1443 var0, EntityPanda.class_1443 var1) {
            if (var0.method_6568()) {
                return var0 == var1 ? var0 : ENTITY_PANDA_PANDA_TEXTURE;
            } else {
                return var0;
            }
        }

        @SideOnly(Side.CLIENT)
        public ResourceLocation method_6562() {
            return this.field_6787;
        }

        public static EntityPanda.class_1443 method_6566(int var0) {
            if (var0 < 0 || var0 >= field_6786.length) {
                var0 = 0;
            }

            return field_6786[var0];
        }

        public static EntityPanda.class_1443 method_6567(String var0) {
            EntityPanda.class_1443[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                EntityPanda.class_1443 var4 = var1[var3];
                if (var4.field_6797.equals(var0)) {
                    return var4;
                }
            }

            return ENTITY_PANDA_PANDA_TEXTURE;
        }
    }

}
