package team.hdt.neutronia.entity;

import com.mojang.datafixers.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.Raid;
import net.minecraft.entity.raid.RaidVictim;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particle.ParticleParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import team.hdt.neutronia.base.utils.registry.Registry;
import team.hdt.neutronia.init.NDataSerializers;
import team.hdt.neutronia.village.VillagerData;
import team.hdt.neutronia.village.VillagerDataContainer;
import team.hdt.neutronia.village.VillagerProfession;
import team.hdt.neutronia.village.VillagerType;

import javax.annotation.Nullable;
import java.util.Iterator;

public class VillagerEntity extends EntityAgeable implements RaidVictim, class_1655, VillagerDataContainer, Villager {
   private static final DataParameter<VillagerData> VILLAGER_DATA;
   private int findVillageCountdown;
   private boolean inMating;
   private boolean staring;
   private VillageProperties village;
   @Nullable
   private EntityPlayer currentCustomer;
   @Nullable
   private VillagerRecipeList recipeList;
   private int unlockTradeCountdown;
   private boolean unlockTrade;
   private boolean willingToMate;
   private String customerName;
   private boolean recentlyRescued;
   private boolean goalsSet;
   private final InventoryBasic inventory;

   public VillagerEntity(World world_1) {
      this(world_1, VillagerType.PLAINS);
   }

   public VillagerEntity(World world_1, VillagerType villagerType_1) {
      super(world_1);
      this.inventory = new InventoryBasic(new TextComponentString("Items"), 8);
      this.setSize(0.6F, 1.95F);
      ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
      this.setCanPickUpLoot(true);
      this.setVillagerData(this.getVillagerData().withProfession((VillagerProfession) Registry.VILLAGER_PROFESSION.getRandom(this.random)));
   }

   protected void method_5959() {
      this.tasks.addTask(0, new SwimGoal(this));
      this.goalSelector.add(1, new FleeEntityGoal(this, ZombieEntity.class, 8.0F, 0.6D, 0.6D));
      this.goalSelector.add(1, new FleeEntityGoal(this, EvokerEntity.class, 12.0F, 0.8D, 0.8D));
      this.goalSelector.add(1, new FleeEntityGoal(this, VindicatorEntity.class, 8.0F, 0.8D, 0.8D));
      this.goalSelector.add(1, new FleeEntityGoal(this, VexEntity.class, 8.0F, 0.6D, 0.6D));
      this.goalSelector.add(1, new FleeEntityGoal(this, PillagerEntity.class, 15.0F, 0.6D, 0.6D));
      this.goalSelector.add(1, new FleeEntityGoal(this, IllusionerEntity.class, 12.0F, 0.6D, 0.6D));
      this.goalSelector.add(1, new class_1390(this));
      this.goalSelector.add(1, new class_1364(this));
      this.goalSelector.add(2, new class_1365(this));
      this.goalSelector.add(3, new StayInsideGoal(this));
      this.goalSelector.add(4, new OpenDoorGoal(this, true));
      this.goalSelector.add(5, new class_1370(this, 0.6D));
      this.goalSelector.add(6, new VillagerBreedGoal(this));
      this.goalSelector.add(7, new AcceptPoppyGoal(this));
      this.goalSelector.add(9, new class_1358(this, PlayerEntity.class, 3.0F, 1.0F));
      this.goalSelector.add(9, new VillagerInteractionGoal(this));
      this.goalSelector.add(9, new class_1394(this, 0.6D));
      this.goalSelector.add(10, new class_1361(this, MobEntity.class, 8.0F));
   }

   private void setSpecificGoals() {
      if (!this.goalsSet) {
         this.goalsSet = true;
         if (this.isChild()) {
            this.tasks.addTask(8, new VillagerStareGoal(this, 0.32D));
         } else if (this.getVillagerData().getProfession() == VillagerProfession.FARMER) {
            this.tasks.addTask(6, new VillagerFarmGoal(this, 0.6D));
         }

      }
   }

   protected void method_5619() {
      if (this.getVillagerData().getProfession() == VillagerProfession.FARMER) {
         this.tasks.addTask(8, new VillagerFarmGoal(this, 0.6D));
      }

      super.method_5619();
   }

   protected void initAttributes() {
      super.initAttributes();
      this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
   }

   protected void mobTick() {
      if (--this.findVillageCountdown <= 0) {
         BlockPos blockPos_1 = new BlockPos(this);
         this.world.getVillageManager().addRecentVillagerPosition(blockPos_1);
         this.findVillageCountdown = 70 + this.random.nextInt(50);
         this.village = this.world.getVillageManager().getNearestVillage(blockPos_1, 32);
         if (this.village == null) {
            this.setAiRangeUnlimited();
         } else {
            BlockPos blockPos_2 = this.village.getCenter();
            this.setAiHome(blockPos_2, this.village.getRadius());
            if (this.recentlyRescued) {
               this.recentlyRescued = false;
               this.village.changeAllRatings(5);
            }
         }
      }

      if (!this.isTrading() && this.unlockTradeCountdown > 0) {
         --this.unlockTradeCountdown;
         if (this.unlockTradeCountdown <= 0) {
            if (this.unlockTrade) {
               Iterator var3 = this.getRecipes().iterator();

               while(var3.hasNext()) {
                  VillagerRecipe villagerRecipe_1 = (VillagerRecipe)var3.next();
                  if (villagerRecipe_1.isDisabled()) {
                     villagerRecipe_1.increasedMaxUses(this.random.nextInt(6) + this.random.nextInt(6) + 2);
                  }
               }

               this.levelUp();
               this.unlockTrade = false;
               if (this.village != null && this.customerName != null) {
                  this.world.summonParticle(this, (byte)14);
                  this.village.changeRating(this.customerName, 1);
               }
            }

            this.addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0));
         }
      }

      super.mobTick();
   }

   public boolean interactMob(PlayerEntity playerEntity_1, Hand hand_1) {
      ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
      boolean boolean_1 = itemStack_1.getItem() == Items.NAME_TAG;
      if (boolean_1) {
         itemStack_1.interactWithEntity(playerEntity_1, this, hand_1);
         return true;
      } else if (itemStack_1.getItem() != Items.VILLAGER_SPAWN_EGG && this.isValid() && !this.isTrading() && !this.isChild()) {
         if (hand_1 == Hand.MAIN) {
            playerEntity_1.increaseStat(Stats.TALKED_TO_VILLAGER);
         }

         if (this.getRecipes().isEmpty()) {
            return super.interactMob(playerEntity_1, hand_1);
         } else {
            if (!this.world.isClient && !this.recipeList.isEmpty()) {
               if (this.village != null && this.village.getRaid() != null && this.village.getRaid().isOnGoing()) {
                  this.world.summonParticle(this, (byte)42);
               } else {
                  this.setCurrentCustomer(playerEntity_1);
                  playerEntity_1.openVillagerTrade(this);
               }
            }

            return true;
         }
      } else {
         return super.interactMob(playerEntity_1, hand_1);
      }
   }

   protected void initDataTracker() {
      super.initDataTracker();
      this.dataTracker.startTracking(VILLAGER_DATA, new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1));
   }

   public void writeCustomDataToTag(CompoundTag compoundTag_1) {
      super.writeCustomDataToTag(compoundTag_1);
      compoundTag_1.put("VillagerData", (Tag)this.getVillagerData().serialize(NbtOps.INSTANCE));
      compoundTag_1.putBoolean("Willing", this.willingToMate);
      VillagerRecipeList villagerRecipeList_1 = this.getRecipes();
      if (!villagerRecipeList_1.isEmpty()) {
         compoundTag_1.put("Offers", villagerRecipeList_1.deserialize());
      }

      ListTag listTag_1 = new ListTag();

      for(int int_1 = 0; int_1 < this.inventory.getInvSize(); ++int_1) {
         ItemStack itemStack_1 = this.inventory.getInvStack(int_1);
         if (!itemStack_1.isEmpty()) {
            listTag_1.add((Tag)itemStack_1.toTag(new CompoundTag()));
         }
      }

      compoundTag_1.put("Inventory", listTag_1);
   }

   public void readCustomDataFromTag(CompoundTag compoundTag_1) {
      super.readCustomDataFromTag(compoundTag_1);
      if (compoundTag_1.containsKey("VillagerData", 10)) {
         this.setVillagerData(new VillagerData(new Dynamic(NbtOps.INSTANCE, compoundTag_1.getTag("VillagerData"))));
      }

      if (compoundTag_1.containsKey("Offers", 10)) {
         this.recipeList = new VillagerRecipeList(compoundTag_1.getCompound("Offers"));
      }

      this.willingToMate = compoundTag_1.getBoolean("Willing");
      ListTag listTag_1 = compoundTag_1.getList("Inventory", 10);

      for(int int_1 = 0; int_1 < listTag_1.size(); ++int_1) {
         ItemStack itemStack_1 = ItemStack.fromTag(listTag_1.getCompoundTag(int_1));
         if (!itemStack_1.isEmpty()) {
            this.inventory.add(itemStack_1);
         }
      }

      this.setCanPickUpLoot(true);
      this.setSpecificGoals();
   }

   public boolean canImmediatelyDespawn(double double_1) {
      return false;
   }

   protected SoundEvent getAmbientSound() {
      return this.isTrading() ? SoundEvents.ENTITY_VILLAGER_TRADE : SoundEvents.ENTITY_VILLAGER_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
      return SoundEvents.ENTITY_VILLAGER_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_VILLAGER_DEATH;
   }

   public void setVillagerData(VillagerData villagerData_1) {
      VillagerData villagerData_2 = this.getVillagerData();
      if (villagerData_2.getProfession() != villagerData_1.getProfession()) {
         this.recipeList = null;
      }

      this.dataManager.set(VILLAGER_DATA, villagerData_1);
   }

   public VillagerData getVillagerData() {
      return (VillagerData)this.dataTracker.get(VILLAGER_DATA);
   }

   public boolean isInMating() {
      return this.inMating;
   }

   public void setInMating(boolean boolean_1) {
      this.inMating = boolean_1;
   }

   public void setStaring(boolean boolean_1) {
      this.staring = boolean_1;
   }

   public boolean isStaring() {
      return this.staring;
   }

   @Nullable
   public VillageProperties getVillage() {
      return this.village;
   }

   public void setAttacker(@Nullable LivingEntity livingEntity_1) {
      super.setAttackTarget(livinEntity_1);
      if (this.village != null && livingEntity_1 != null) {
         this.village.addAttacker(livingEntity_1);
         if (livingEntity_1 instanceof PlayerEntity) {
            int int_1 = -1;
            if (this.isChild()) {
               int_1 = -3;
            }

            this.village.changeRating(((PlayerEntity)livingEntity_1).getGameProfile().getName(), int_1);
            if (this.isValid()) {
               this.world.summonParticle(this, (byte)13);
            }
         }
      }

   }

   public void onDeath(DamageSource damageSource_1) {
      if (this.village != null) {
         Entity entity_1 = damageSource_1.getImmediateSource();
         if (entity_1 != null) {
            if (entity_1 instanceof EntityPlayer) {
               this.village.changeRating(((EntityPlayer)entity_1).getGameProfile().getName(), -2);
            } else if (entity_1 instanceof Monster) {
               this.village.onVillagerDeath();
            }
         } else {
             EntityPlayer playerEntity_1 = this.world.getClosestPlayerToEntity(this, 16.0D);
            if (playerEntity_1 != null) {
               this.village.onVillagerDeath();
            }
         }
      }

      super.onDeath(damageSource_1);
   }

   public void setCurrentCustomer(@Nullable EntityPlayer playerEntity_1) {
      this.currentCustomer = playerEntity_1;
   }

   @Nullable
   public EntityPlayer getCurrentCustomer() {
      return this.currentCustomer;
   }

   public boolean isTrading() {
      return this.currentCustomer != null;
   }

   public boolean isWillingToMate(boolean boolean_1) {
      if (!this.willingToMate && boolean_1 && this.hasFoodForWilling()) {
         boolean boolean_2 = false;

         for(int int_1 = 0; int_1 < this.inventory.getSizeInventory(); ++int_1) {
            ItemStack itemStack_1 = this.inventory.getStackInSlot(int_1);
            if (!itemStack_1.isEmpty()) {
               if (itemStack_1.getItem() == Items.BREAD && itemStack_1.getCount() >= 3) {
                  boolean_2 = true;
                  this.inventory.decrStackSize(int_1, 3);
               } else if ((itemStack_1.getItem() == Items.POTATO || itemStack_1.getItem() == Items.CARROT) && itemStack_1.getCount() >= 12) {
                  boolean_2 = true;
                  this.inventory.decrStackSize(int_1, 12);
               }
            }

            if (boolean_2) {
               this.world.summonParticle(this, (byte)18);
               this.willingToMate = true;
               break;
            }
         }
      }

      return this.willingToMate;
   }

   public void setWillingToMate(boolean boolean_1) {
      this.willingToMate = boolean_1;
   }

   public void useRecipe(VillagerRecipe villagerRecipe_1) {
      villagerRecipe_1.use();
      this.field_6191 = -this.method_5970();
      this.playSoundAtEntity(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
      int int_1 = 3 + this.random.nextInt(4);
      if (villagerRecipe_1.getUses() == 1 || this.random.nextInt(5) == 0) {
         this.unlockTradeCountdown = 40;
         this.unlockTrade = true;
         this.willingToMate = true;
         this.customerName = this.currentCustomer == null ? null : this.currentCustomer.getGameProfile().getName();
         int_1 += 5;
      }

      if (villagerRecipe_1.getRewardExp()) {
         this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.x, this.y + 0.5D, this.z, int_1));
      }

      if (this.currentCustomer instanceof ServerPlayerEntity) {
         Criterions.VILLAGER_TRADE.handle((ServerPlayerEntity)this.currentCustomer, this, villagerRecipe_1.getSellItem());
      }

   }

   public void onSellingItem(ItemStack itemStack_1) {
      if (!this.world.isRemote && this.field_6191 > -this.method_5970() + 20) {
         this.field_6191 = -this.method_5970();
         this.playSound(itemStack_1.isEmpty() ? SoundEvents.ENTITY_VILLAGER_NO : SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   public VillagerRecipeList getRecipes() {
      if (this.recipeList == null) {
         this.recipeList = new VillagerRecipeList();
         this.addTrades();
      }

      return this.recipeList;
   }

   public void setRecipes(VillagerRecipeList villagerRecipeList_1) {
      this.recipeList = villagerRecipeList_1;
   }

   private void levelUp() {
      this.setVillagerData(this.getVillagerData().withLevel(this.getVillagerData().getLevel() + 1));
      this.addTrades();
   }

   private void addTrades() {
      VillagerData villagerData_1 = this.getVillagerData();
      Int2ObjectMap<VillagerTrades.Factory[]> int2ObjectMap_1 = (Int2ObjectMap)VillagerTrades.PROFESSION_TO_LEVELED_TRADE.get(villagerData_1.getProfession());
      if (int2ObjectMap_1 != null && !int2ObjectMap_1.isEmpty()) {
         VillagerTrades.Factory[] villagerTrades$Factorys_1 = (VillagerTrades.Factory[])int2ObjectMap_1.get(villagerData_1.getLevel());
         if (villagerTrades$Factorys_1 != null) {
            VillagerRecipeList villagerRecipeList_1 = this.getRecipes();
            VillagerTrades.Factory[] var5 = villagerTrades$Factorys_1;
            int var6 = villagerTrades$Factorys_1.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               VillagerTrades.Factory villagerTrades$Factory_1 = var5[var7];
               VillagerRecipe villagerRecipe_1 = villagerTrades$Factory_1.create(this, this.random);
               if (villagerRecipe_1 != null) {
                  villagerRecipeList_1.add(villagerRecipe_1);
               }
            }
         }

      }
   }

   @Environment(EnvType.CLIENT)
   public void setServerRecipes(@Nullable VillagerRecipeList villagerRecipeList_1) {
   }

   public World getVillagerWorld() {
      return this.world;
   }

   public BlockPos getVillagerPos() {
      return new BlockPos(this);
   }

   public float getEyeHeight() {
      return this.isChild() ? 0.81F : 1.62F;
   }

   @Nullable
   public Raid getRaid() {
      return this.village != null ? this.village.getRaid() : null;
   }

   @Environment(EnvType.CLIENT)
   public void method_5711(byte byte_1) {
      if (byte_1 == 12) {
         this.method_7233(ParticleTypes.HEART);
      } else if (byte_1 == 13) {
         this.method_7233(ParticleTypes.ANGRY_VILLAGER);
      } else if (byte_1 == 14) {
         this.method_7233(ParticleTypes.HAPPY_VILLAGER);
      } else if (byte_1 == 42) {
         this.method_7233(ParticleTypes.SPLASH);
      } else {
         super.method_5711(byte_1);
      }

   }

   @Environment(EnvType.CLIENT)
   private void method_7233(ParticleParameters particleParameters_1) {
      for(int int_1 = 0; int_1 < 5; ++int_1) {
         double double_1 = this.rand.nextGaussian() * 0.02D;
         double double_2 = this.rand.nextGaussian() * 0.02D;
         double double_3 = this.rand.nextGaussian() * 0.02D;
         this.world.spawnParticle(particleParameters_1, this.x + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.y + 1.0D + (double)(this.rand.nextFloat() * this.height), this.z + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, double_1, double_2, double_3);
      }

   }

   @Nullable
   public EntityData prepareEntityData(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, @Nullable EntityData entityData_1, @Nullable CompoundTag compoundTag_1) {
      this.setSpecificGoals();
      if (spawnType_1 == SpawnType.BREEDING) {
         this.setVillagerData(this.getVillagerData().withProfession(VillagerProfession.NONE));
      }

      if (spawnType_1 == SpawnType.COMMAND || spawnType_1 == SpawnType.SPAWN_EGG || spawnType_1 == SpawnType.SPAWNER) {
         this.setVillagerData(this.getVillagerData().withType(VillagerType.forBiome(iWorld_1.getBiome(new BlockPos(this)))));
      }

      return super.prepareEntityData(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
   }

   public void setRecentlyRescued() {
      this.recentlyRescued = true;
   }

   public VillagerEntity createChild(EntityAgeable passiveEntity_1) {
      double double_1 = this.rand.nextDouble();
      VillagerType villagerType_3;
      if (double_1 < 0.5D) {
         villagerType_3 = VillagerType.forBiome(this.world.getBiome(new BlockPos(this)));
      } else if (double_1 < 0.75D) {
         villagerType_3 = this.getVillagerData().getType();
      } else {
         villagerType_3 = ((VillagerEntity)passiveEntity_1).getVillagerData().getType();
      }

      VillagerEntity villagerEntity_1 = new VillagerEntity(this.world, villagerType_3);
      villagerEntity_1.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(villagerEntity_1)), null);
      return villagerEntity_1;
   }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return false;
    }

    public void onStruckByLightning(EntityLightningBolt lightningEntity_1) {
      if (!this.world.isRemote && !this.isDead) {
         EntityWitch witchEntity_1 = new EntityWitch(this.world);
         witchEntity_1.setPositionAndRotation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         witchEntity_1.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(witchEntity_1)), null);
         witchEntity_1.setNoAI(this.isAIDisabled());
         if (this.hasCustomName()) {
            witchEntity_1.setCustomNameTag(this.getCustomNameTag());
            witchEntity_1.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
         }

         this.world.spawnEntity(witchEntity_1);
         this.setDead();
      }
   }

   public InventoryBasic getInventory() {
      return this.inventory;
   }

   protected void pickupItem(EntityItem itemEntity_1) {
      ItemStack itemStack_1 = itemEntity_1.getItem();
      Item item_1 = itemStack_1.getItem();
      if (this.canPickUp(item_1)) {
         ItemStack itemStack_2 = this.inventory.addItem(itemStack_1);
         if (itemStack_2.isEmpty()) {
            itemEntity_1.setDead();
         } else {
            itemStack_1.setCount(itemStack_2.getCount());
         }
      }

   }

   private boolean canPickUp(Item item_1) {
      return item_1 == Items.BREAD || item_1 == Items.POTATO || item_1 == Items.CARROT || item_1 == Items.WHEAT || item_1 == Items.WHEAT_SEEDS || item_1 == Items.BEETROOT || item_1 == Items.BEETROOT_SEEDS;
   }

   public boolean hasFoodForWilling() {
      return this.hasEnoughFood(1);
   }

   public boolean method_7234() {
      return this.hasEnoughFood(2);
   }

   public boolean canBreed() {
      boolean boolean_1 = this.getVillagerData().getProfession() == VillagerProfession.FARMER;
      if (boolean_1) {
         return !this.hasEnoughFood(5);
      } else {
         return !this.hasEnoughFood(1);
      }
   }

   private boolean hasEnoughFood(int int_1) {
      boolean boolean_1 = this.getVillagerData().getProfession() == VillagerProfession.FARMER;

      for(int int_2 = 0; int_2 < this.inventory.getSizeInventory(); ++int_2) {
         ItemStack itemStack_1 = this.inventory.getStackInSlot(int_2);
         Item item_1 = itemStack_1.getItem();
         int int_3 = itemStack_1.getCount();
         if (item_1 == Items.BREAD && int_3 >= 3 * int_1 || item_1 == Items.POTATO && int_3 >= 12 * int_1 || item_1 == Items.CARROT && int_3 >= 12 * int_1 || item_1 == Items.BEETROOT && int_3 >= 12 * int_1) {
            return true;
         }

         if (boolean_1 && item_1 == Items.WHEAT && int_3 >= 9 * int_1) {
            return true;
         }
      }

      return false;
   }

   public boolean hasSeed() {
      for(int int_1 = 0; int_1 < this.inventory.getSizeInventory(); ++int_1) {
         Item item_1 = this.inventory.getStackInSlot(int_1).getItem();
         if (item_1 == Items.WHEAT_SEEDS || item_1 == Items.POTATO || item_1 == Items.CARROT || item_1 == Items.BEETROOT_SEEDS) {
            return true;
         }
      }

      return false;
   }

   public boolean method_5758(int int_1, ItemStack itemStack_1) {
      if (super.method_5758(int_1, itemStack_1)) {
         return true;
      } else {
         int int_2 = int_1 - 300;
         if (int_2 >= 0 && int_2 < this.inventory.getInvSize()) {
            this.inventory.setInvStack(int_2, itemStack_1);
            return true;
         } else {
            return false;
         }
      }
   }

   static {
      VILLAGER_DATA = EntityDataManager.createKey(VillagerEntity.class, NDataSerializers.VILLAGER_DATA);
   }
}
