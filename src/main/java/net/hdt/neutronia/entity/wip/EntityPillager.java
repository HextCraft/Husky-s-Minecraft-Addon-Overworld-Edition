package net.hdt.neutronia.entity.wip;

import net.minecraft.util.Hand;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityArrowBase;
import net.minecraft.util.math.MathUtils;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.reference.Sounds;
import net.minecraft.sound.Sound;
import net.minecraft.enchantment.DamageCategory;
import net.minecraft.entity.EntityLiving;
import java.util.Map;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.Enchantment;
import java.util.HashMap;
import net.minecraft.util.IItemProvider;
import javax.annotation.Nullable;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.reference.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NamedBinaryTag;
import net.minecraft.nbt.NBTList;
import net.minecraft.nbt.NBTCompound;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.entity.attribute.AttributeManager;
import net.minecraft.entity.passive.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.ai.mob.AiGoalTrackAttackableTarget;
import net.minecraft.entity.ai.AiGoalGangAttack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.AiGoalWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.PathfinerMob;
import net.minecraft.entity.ai.AiGoalRoam;
import net.minecraft.entity.ai.AiGoal;
import net.minecraft.entity.ai.AiGoalSwim;
import net.minecraft.text.ITextComponent;
import net.minecraft.text.TextComponentString;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.tracker.TrackedData;
import net.minecraft.entity.ai.IDistantAttacker;
import net.minecraft.entity.EntityAbstractIllager;

public class EntityPillager extends EntityAbstractIllager implements IDistantAttacker
{
    private static final TrackedData<Boolean> b;
    private final Inventory c;
    
    public EntityPillager(final World aWorld) {
        super(EntityType.PILLAGER, aWorld);
        this.c = new Inventory(new TextComponentString("Inventory"), 5);
        this.setSize(0.6f, 1.95f);
    }
    
    @Override
    protected void n() {
        super.n();
        this.goalSelector.add(0, new AiGoalSwim(this));
        this.goalSelector.add(2, new a());
        this.goalSelector.add(3, new ail<Object>(this, 1.0, 15.0f));
        this.goalSelector.add(8, new AiGoalRoam(this, 0.6));
        this.goalSelector.add(9, new AiGoalWatchClosest(this, EntityPlayer.class, 3.0f, 1.0f));
        this.goalSelector.add(10, new AiGoalWatchClosest(this, EntityMob.class, 8.0f));
        this.targetSelector.add(1, new AiGoalGangAttack(this, (Class<?>[])new Class[] { EntityAbstractIllager.class }).a((Class<?>[])new Class[0]));
        this.targetSelector.add(2, new AiGoalTrackAttackableTarget<Object>(this, EntityPlayer.class, true));
        this.targetSelector.add(3, new AiGoalTrackAttackableTarget<Object>(this, EntityVillager.class, true));
        this.targetSelector.add(3, new AiGoalTrackAttackableTarget<Object>(this, EntityIronGolem.class, true));
    }
    
    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(AttributeManager.MOVEMENT_SPEED).setBaseValue(0.3499999940395355);
        this.getAttributeInstance(AttributeManager.FOLLOW_RANGE).setBaseValue(12.0);
        this.getAttributeInstance(AttributeManager.MAX_HEALTH).setBaseValue(24.0);
        this.getAttributeInstance(AttributeManager.ATTACK_DAMAGE).setBaseValue(5.0);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Boolean>startTracking(EntityPillager.b, false);
    }
    
    @Override
    protected Identifier getLootTableId() {
        return LootTables.aD;
    }
    
    @Sided(Side.CLIENT)
    public boolean dA() {
        return this.dataTracker.<Boolean>get(EntityPillager.b);
    }
    
    public void a(final boolean aBoolean) {
        this.dataTracker.<Boolean>set(EntityPillager.b, aBoolean);
    }
    
    @Sided(Side.CLIENT)
    public boolean dB() {
        return this.a(1);
    }
    
    @Override
    public void setArmsRaised(final boolean aBoolean) {
        this.a(1, aBoolean);
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        final NBTList vNBTList2 = new NBTList();
        for (int vInteger3 = 0; vInteger3 < this.c.getInvSize(); ++vInteger3) {
            final ItemStack vItemStack4 = this.c.getInvStack(vInteger3);
            if (!vItemStack4.isEmpty()) {
                vNBTList2.a(vItemStack4.serialize(new NBTCompound()));
            }
        }
        aNBTCompound.setTag("Inventory", vNBTList2);
    }
    
    @Sided(Side.CLIENT)
    @Override
    public EnumAttackType l() {
        if (this.dB()) {
            return EnumAttackType.ATTACKING;
        }
        if (this.dA()) {
            return EnumAttackType.f;
        }
        if (!this.getMainHandStack().isEmpty() && this.getMainHandStack().getItem() == Items.po) {
            return EnumAttackType.e;
        }
        return EnumAttackType.CROSSED;
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        final NBTList vNBTList2 = aNBTCompound.getList("Inventory", 10);
        for (int vInteger3 = 0; vInteger3 < vNBTList2.size(); ++vInteger3) {
            final ItemStack vItemStack4 = ItemStack.a(vNBTList2.getCompound(vInteger3));
            if (!vItemStack4.isEmpty()) {
                this.c.a(vItemStack4);
            }
        }
        this.setCanPickUpLoot(true);
    }
    
    @Nullable
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable final IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        final IEntityData vIEntityData5 = super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
        this.initEquipment(aLocalDifficulty2);
        this.b(aLocalDifficulty2);
        return vIEntityData5;
    }
    
    @Override
    protected void initEquipment(final LocalDifficulty aLocalDifficulty) {
        final ItemStack vItemStack2 = new ItemStack(Items.po);
        if (this.rand.nextInt(300) == 0) {
            final Map<Enchantment, Integer> vMap3 = new HashMap<Enchantment, Integer>();
            vMap3.put(Enchantments.I, 1);
            EnchantmentHelper.setEnchantments(vMap3, vItemStack2);
        }
        this.setEquippedStack(EquipmentSlot.MAINHAND, vItemStack2);
    }
    
    @Override
    public boolean isTeammate(final Entity aEntity) {
        return super.isTeammate(aEntity) || (aEntity instanceof EntityLiving && ((EntityLiving)aEntity).getEntityAttribute() == DamageCategory.ILLAGER && this.getScoreboardTeam() == null && aEntity.getScoreboardTeam() == null);
    }
    
    @Override
    protected Sound getSoundAmbient() {
        return Sounds.hr;
    }
    
    @Override
    protected Sound getSoundDeath() {
        return Sounds.hs;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.ht;
    }
    
    @Override
    public void attackDistant(final EntityLiving aEntityLiving1, final float aFloat2) {
        final EntityArrowBase vEntityArrowBase3 = this.b(aEntityLiving1.cY(), aFloat2);
        final double vDouble4 = aEntityLiving1.x - this.x;
        final double vDouble5 = aEntityLiving1.getBoundingBox().minY + aEntityLiving1.height / 3.0f - vEntityArrowBase3.y;
        final double vDouble6 = aEntityLiving1.z - this.z;
        final double vDouble7 = MathUtils.sqrt(vDouble4 * vDouble4 + vDouble6 * vDouble6);
        vEntityArrowBase3.setVelocity(vDouble4, vDouble5 + vDouble7 * 0.20000000298023224, vDouble6, 1.6f, 14 - this.world.getDifficulty().getId() * 4);
        this.playSoundAtEntity(Sounds.bq, 1.0f, 1.0f / (this.getRand().nextFloat() * 0.4f + 0.8f));
        this.world.spawnEntity(vEntityArrowBase3);
        asv.a(this.getMainHandStack(), false);
    }
    
    protected EntityArrowBase b(final ItemStack aItemStack1, final float aFloat2) {
        final EntityArrowBase vEntityArrowBase3 = new EntityArrow(this.world, this);
        vEntityArrowBase3.a(this, aFloat2);
        vEntityArrowBase3.p(true);
        vEntityArrowBase3.a(Sounds.bj);
        final int vInteger4 = EnchantmentHelper.a(Enchantments.I, aItemStack1);
        if (vInteger4 > 0) {
            vEntityArrowBase3.b((byte)vInteger4);
        }
        return vEntityArrowBase3;
    }
    
    @Override
    protected void a(final EntityItem aEntityItem) {
        final ItemStack vItemStack2 = aEntityItem.getStack();
        final Item vItem3 = vItemStack2.getItem();
        if (this.a(vItem3)) {
            final ItemStack vItemStack3 = this.c.a(vItemStack2);
            if (vItemStack3.isEmpty()) {
                aEntityItem.invalidate();
            }
            else {
                vItemStack2.setAmount(vItemStack3.getAmount());
            }
        }
    }
    
    private boolean a(final Item aItem) {
        return aItem == Items.je;
    }
    
    @Override
    public boolean c(final int aInteger1, final ItemStack aItemStack2) {
        if (super.c(aInteger1, aItemStack2)) {
            return true;
        }
        final int vInteger3 = aInteger1 - 300;
        if (vInteger3 >= 0 && vInteger3 < this.c.getInvSize()) {
            this.c.setInvStack(vInteger3, aItemStack2);
            return true;
        }
        return false;
    }
    
    static {
        b = DataTracker.<Boolean>registerData(EntityPillager.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
    
    class a extends AiGoal
    {
        public a() {
            this.setCategoryBits(1);
        }
        
        @Override
        public boolean canStart() {
            return !EntityPillager.this.getMainHandStack().isEmpty() && EntityPillager.this.getMainHandStack().getItem() == Items.po && !asv.d(EntityPillager.this.getMainHandStack());
        }
        
        @Override
        public void start() {
            EntityPillager.this.setCurrentHand(Hand.MAIN_HAND);
            EntityPillager.this.a(true);
        }
        
        @Override
        public void stop() {
            EntityPillager.this.dc();
            EntityPillager.this.a(false);
        }
        
        @Override
        public void tick() {
            final int vInteger1 = EntityPillager.this.da();
            if (vInteger1 >= asv.e(EntityPillager.this.getMainHandStack())) {
                asv.a(EntityPillager.this.getMainHandStack(), true);
                this.stop();
            }
        }
    }
}
