package team.abnormal.neutronia.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.abnormal.neutronia.datafixers.NbtOps;
import team.abnormal.neutronia.init.NDataSerializers;
import team.abnormal.neutronia.village.VillagerData;
import team.abnormal.neutronia.village.VillagerProfession;
import team.abnormal.neutronia.village.VillagerType;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

public class EntityVillager extends EntityAgeable implements INpc, IMerchant
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DataParameter<VillagerData> PROFESSION = EntityDataManager.createKey(EntityVillager.class, NDataSerializers.VILLAGER_DATA);
    private int randomTickDivider;
    private boolean isMating;
    private boolean isPlaying;
    Village village;
    /** This villager's current customer. */
    @Nullable
    private EntityPlayer buyingPlayer;
    /** Initialises the MerchantRecipeList.java */
    @Nullable
    private MerchantRecipeList buyingList;
    private int timeUntilReset;
    /** addDefaultEquipmentAndRecipies is called if this is true */
    private boolean needsInitilization;
    private boolean isWillingToMate;
    private int wealth;
    /**
     * Last player to trade with this villager, used for aggressivity.
     *  
     * MODDERS: Do not reference directly; will have a different type under forge
     */
    private java.util.UUID lastBuyingPlayer;
    private int careerId;
    /** This is the EntityVillager's career level value */
    private int careerLevel;
    private boolean isLookingForHome;
    private boolean areAdditionalTasksSet;
    private final InventoryBasic villagerInventory;
    /** A multi-dimensional array mapping the various professions, careers and career levels that a Villager may offer */
    private static final EntityVillager.ITradeList[][][][] DEFAULT_TRADE_LIST_MAP = new EntityVillager.ITradeList[][][][] {{{{new EntityVillager.EmeraldForItems(Items.WHEAT, new EntityVillager.PriceInfo(18, 22)), new EntityVillager.EmeraldForItems(Items.POTATO, new EntityVillager.PriceInfo(15, 19)), new EntityVillager.EmeraldForItems(Items.CARROT, new EntityVillager.PriceInfo(15, 19)), new EntityVillager.ListItemForEmeralds(Items.BREAD, new EntityVillager.PriceInfo(-4, -2))}, {new EntityVillager.EmeraldForItems(Item.getItemFromBlock(Blocks.PUMPKIN), new EntityVillager.PriceInfo(8, 13)), new EntityVillager.ListItemForEmeralds(Items.PUMPKIN_PIE, new EntityVillager.PriceInfo(-3, -2))}, {new EntityVillager.EmeraldForItems(Item.getItemFromBlock(Blocks.MELON_BLOCK), new EntityVillager.PriceInfo(7, 12)), new EntityVillager.ListItemForEmeralds(Items.APPLE, new EntityVillager.PriceInfo(-7, -5))}, {new EntityVillager.ListItemForEmeralds(Items.COOKIE, new EntityVillager.PriceInfo(-10, -6)), new EntityVillager.ListItemForEmeralds(Items.CAKE, new EntityVillager.PriceInfo(1, 1))}}, {{new EntityVillager.EmeraldForItems(Items.STRING, new EntityVillager.PriceInfo(15, 20)), new EntityVillager.EmeraldForItems(Items.COAL, new EntityVillager.PriceInfo(16, 24)), new EntityVillager.ItemAndEmeraldToItem(Items.FISH, new EntityVillager.PriceInfo(6, 6), Items.COOKED_FISH, new EntityVillager.PriceInfo(6, 6))}, {new EntityVillager.ListEnchantedItemForEmeralds(Items.FISHING_ROD, new EntityVillager.PriceInfo(7, 8))}}, {{new EntityVillager.EmeraldForItems(Item.getItemFromBlock(Blocks.WOOL), new EntityVillager.PriceInfo(16, 22)), new EntityVillager.ListItemForEmeralds(Items.SHEARS, new EntityVillager.PriceInfo(3, 4))}, {new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL)), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 1), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 2), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 3), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 4), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 5), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 6), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 7), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 8), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 9), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 10), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 11), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 12), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 13), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 14), new EntityVillager.PriceInfo(1, 2)), new EntityVillager.ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, 15), new EntityVillager.PriceInfo(1, 2))}}, {{new EntityVillager.EmeraldForItems(Items.STRING, new EntityVillager.PriceInfo(15, 20)), new EntityVillager.ListItemForEmeralds(Items.ARROW, new EntityVillager.PriceInfo(-12, -8))}, {new EntityVillager.ListItemForEmeralds(Items.BOW, new EntityVillager.PriceInfo(2, 3)), new EntityVillager.ItemAndEmeraldToItem(Item.getItemFromBlock(Blocks.GRAVEL), new EntityVillager.PriceInfo(10, 10), Items.FLINT, new EntityVillager.PriceInfo(6, 10))}}}, {{{new EntityVillager.EmeraldForItems(Items.PAPER, new EntityVillager.PriceInfo(24, 36)), new EntityVillager.ListEnchantedBookForEmeralds()}, {new EntityVillager.EmeraldForItems(Items.BOOK, new EntityVillager.PriceInfo(8, 10)), new EntityVillager.ListItemForEmeralds(Items.COMPASS, new EntityVillager.PriceInfo(10, 12)), new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.BOOKSHELF), new EntityVillager.PriceInfo(3, 4))}, {new EntityVillager.EmeraldForItems(Items.WRITTEN_BOOK, new EntityVillager.PriceInfo(2, 2)), new EntityVillager.ListItemForEmeralds(Items.CLOCK, new EntityVillager.PriceInfo(10, 12)), new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.GLASS), new EntityVillager.PriceInfo(-5, -3))}, {new EntityVillager.ListEnchantedBookForEmeralds()}, {new EntityVillager.ListEnchantedBookForEmeralds()}, {new EntityVillager.ListItemForEmeralds(Items.NAME_TAG, new EntityVillager.PriceInfo(20, 22))}}, {{new EntityVillager.EmeraldForItems(Items.PAPER, new EntityVillager.PriceInfo(24, 36))}, {new EntityVillager.EmeraldForItems(Items.COMPASS, new EntityVillager.PriceInfo(1, 1))}, {new EntityVillager.ListItemForEmeralds(Items.MAP, new EntityVillager.PriceInfo(7, 11))}, {new EntityVillager.TreasureMapForEmeralds(new EntityVillager.PriceInfo(12, 20), "Monument", MapDecoration.Type.MONUMENT), new EntityVillager.TreasureMapForEmeralds(new EntityVillager.PriceInfo(16, 28), "Mansion", MapDecoration.Type.MANSION)}}}, {{{new EntityVillager.EmeraldForItems(Items.ROTTEN_FLESH, new EntityVillager.PriceInfo(36, 40)), new EntityVillager.EmeraldForItems(Items.GOLD_INGOT, new EntityVillager.PriceInfo(8, 10))}, {new EntityVillager.ListItemForEmeralds(Items.REDSTONE, new EntityVillager.PriceInfo(-4, -1)), new EntityVillager.ListItemForEmeralds(new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()), new EntityVillager.PriceInfo(-2, -1))}, {new EntityVillager.ListItemForEmeralds(Items.ENDER_PEARL, new EntityVillager.PriceInfo(4, 7)), new EntityVillager.ListItemForEmeralds(Item.getItemFromBlock(Blocks.GLOWSTONE), new EntityVillager.PriceInfo(-3, -1))}, {new EntityVillager.ListItemForEmeralds(Items.EXPERIENCE_BOTTLE, new EntityVillager.PriceInfo(3, 11))}}}, {{{new EntityVillager.EmeraldForItems(Items.COAL, new EntityVillager.PriceInfo(16, 24)), new EntityVillager.ListItemForEmeralds(Items.IRON_HELMET, new EntityVillager.PriceInfo(4, 6))}, {new EntityVillager.EmeraldForItems(Items.IRON_INGOT, new EntityVillager.PriceInfo(7, 9)), new EntityVillager.ListItemForEmeralds(Items.IRON_CHESTPLATE, new EntityVillager.PriceInfo(10, 14))}, {new EntityVillager.EmeraldForItems(Items.DIAMOND, new EntityVillager.PriceInfo(3, 4)), new EntityVillager.ListEnchantedItemForEmeralds(Items.DIAMOND_CHESTPLATE, new EntityVillager.PriceInfo(16, 19))}, {new EntityVillager.ListItemForEmeralds(Items.CHAINMAIL_BOOTS, new EntityVillager.PriceInfo(5, 7)), new EntityVillager.ListItemForEmeralds(Items.CHAINMAIL_LEGGINGS, new EntityVillager.PriceInfo(9, 11)), new EntityVillager.ListItemForEmeralds(Items.CHAINMAIL_HELMET, new EntityVillager.PriceInfo(5, 7)), new EntityVillager.ListItemForEmeralds(Items.CHAINMAIL_CHESTPLATE, new EntityVillager.PriceInfo(11, 15))}}, {{new EntityVillager.EmeraldForItems(Items.COAL, new EntityVillager.PriceInfo(16, 24)), new EntityVillager.ListItemForEmeralds(Items.IRON_AXE, new EntityVillager.PriceInfo(6, 8))}, {new EntityVillager.EmeraldForItems(Items.IRON_INGOT, new EntityVillager.PriceInfo(7, 9)), new EntityVillager.ListEnchantedItemForEmeralds(Items.IRON_SWORD, new EntityVillager.PriceInfo(9, 10))}, {new EntityVillager.EmeraldForItems(Items.DIAMOND, new EntityVillager.PriceInfo(3, 4)), new EntityVillager.ListEnchantedItemForEmeralds(Items.DIAMOND_SWORD, new EntityVillager.PriceInfo(12, 15)), new EntityVillager.ListEnchantedItemForEmeralds(Items.DIAMOND_AXE, new EntityVillager.PriceInfo(9, 12))}}, {{new EntityVillager.EmeraldForItems(Items.COAL, new EntityVillager.PriceInfo(16, 24)), new EntityVillager.ListEnchantedItemForEmeralds(Items.IRON_SHOVEL, new EntityVillager.PriceInfo(5, 7))}, {new EntityVillager.EmeraldForItems(Items.IRON_INGOT, new EntityVillager.PriceInfo(7, 9)), new EntityVillager.ListEnchantedItemForEmeralds(Items.IRON_PICKAXE, new EntityVillager.PriceInfo(9, 11))}, {new EntityVillager.EmeraldForItems(Items.DIAMOND, new EntityVillager.PriceInfo(3, 4)), new EntityVillager.ListEnchantedItemForEmeralds(Items.DIAMOND_PICKAXE, new EntityVillager.PriceInfo(12, 15))}}}, {{{new EntityVillager.EmeraldForItems(Items.PORKCHOP, new EntityVillager.PriceInfo(14, 18)), new EntityVillager.EmeraldForItems(Items.CHICKEN, new EntityVillager.PriceInfo(14, 18))}, {new EntityVillager.EmeraldForItems(Items.COAL, new EntityVillager.PriceInfo(16, 24)), new EntityVillager.ListItemForEmeralds(Items.COOKED_PORKCHOP, new EntityVillager.PriceInfo(-7, -5)), new EntityVillager.ListItemForEmeralds(Items.COOKED_CHICKEN, new EntityVillager.PriceInfo(-8, -6))}}, {{new EntityVillager.EmeraldForItems(Items.LEATHER, new EntityVillager.PriceInfo(9, 12)), new EntityVillager.ListItemForEmeralds(Items.LEATHER_LEGGINGS, new EntityVillager.PriceInfo(2, 4))}, {new EntityVillager.ListEnchantedItemForEmeralds(Items.LEATHER_CHESTPLATE, new EntityVillager.PriceInfo(7, 12))}, {new EntityVillager.ListItemForEmeralds(Items.SADDLE, new EntityVillager.PriceInfo(8, 10))}}}, {new EntityVillager.ITradeList[0][]}};
    protected team.abnormal.neutronia.village.Village village2;


    public EntityVillager(World worldIn)
    {
        this(VillagerData.DEFAULT, worldIn);
    }

    public EntityVillager(VillagerData profession, World worldIn)
    {
        super(worldIn);
        this.villagerInventory = new InventoryBasic("Items", false, 8);
        this.setProfession(profession);
        this.setSize(0.6F, 1.95F);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.setCanPickUpLoot(true);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityEvoker.class, 12.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityVindicator.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity<>(this, EntityVex.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(1, new team.abnormal.neutronia.entity.ai.EntityAITradePlayer(this));
        this.tasks.addTask(1, new team.abnormal.neutronia.entity.ai.EntityAILookAtTradePlayer(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(6, new team.abnormal.neutronia.entity.ai.EntityAIVillagerMate(this));
        this.tasks.addTask(7, new team.abnormal.neutronia.entity.ai.EntityAIFollowGolem(this));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new team.abnormal.neutronia.entity.ai.EntityAIVillagerInteract(this));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    private void setAdditionalAItasks()
    {
        if (!this.areAdditionalTasksSet)
        {
            this.areAdditionalTasksSet = true;

            if (this.isChild())
            {
                this.tasks.addTask(8, new team.abnormal.neutronia.entity.ai.EntityAIPlay(this, 0.32D));
            }
            else if (this.getProfession() == 0)
            {
                this.tasks.addTask(6, new team.abnormal.neutronia.entity.ai.EntityAIHarvestFarmland(this, 0.6D));
            }
        }
    }

    /**
     * This is called when Entity's growing age timer reaches 0 (negative values are considered as a child, positive as
     * an adult)
     */
    protected void onGrowingAdult()
    {
        if (this.getProfession() == 0)
        {
            this.tasks.addTask(8, new team.abnormal.neutronia.entity.ai.EntityAIHarvestFarmland(this, 0.6D));
        }

        super.onGrowingAdult();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }

    protected void updateAITasks()
    {
        if (--this.randomTickDivider <= 0)
        {
            BlockPos blockpos = new BlockPos(this);
            this.world.getVillageCollection().addToVillagerPositionList(blockpos);
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.village = this.world.getVillageCollection().getNearestVillage(blockpos, 32);

            if (this.village == null)
            {
                this.detachHome();
            }
            else
            {
                BlockPos blockpos1 = this.village.getCenter();
                this.setHomePosAndDistance(blockpos1, this.village.getVillageRadius());

                if (this.isLookingForHome)
                {
                    this.isLookingForHome = false;
                    this.village.setDefaultPlayerReputation(5);
                }
            }
        }

        if (!this.isTrading() && this.timeUntilReset > 0)
        {
            --this.timeUntilReset;

            if (this.timeUntilReset <= 0)
            {
                if (this.needsInitilization)
                {
                    for (MerchantRecipe merchantrecipe : this.buyingList)
                    {
                        if (merchantrecipe.isRecipeDisabled())
                        {
                            merchantrecipe.increaseMaxTradeUses(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                        }
                    }

                    this.populateBuyingList();
                    this.needsInitilization = false;

                    if (this.village != null && this.lastBuyingPlayer != null)
                    {
                        this.world.setEntityState(this, (byte)14);
                        this.village.modifyPlayerReputation(this.lastBuyingPlayer, 1);
                    }
                }

                this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
            }
        }

        super.updateAITasks();
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = itemstack.getItem() == Items.NAME_TAG;

        if (flag)
        {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        }
        else if (!this.holdingSpawnEggOfClass(itemstack, this.getClass()) && this.isEntityAlive() && !this.isTrading() && !this.isChild() && !player.isSneaking())
        {
            if (this.buyingList == null)
            {
                this.populateBuyingList();
            }

            if (hand == EnumHand.MAIN_HAND)
            {
                player.addStat(StatList.TALKED_TO_VILLAGER);
            }

            if (!this.world.isRemote && !this.buyingList.isEmpty())
            {
                this.setCustomer(player);
                player.displayVillagerTradeGui(this);
            }
            else if (this.buyingList.isEmpty())
            {
                return super.processInteract(player, hand);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(PROFESSION, new VillagerData(VillagerType.PLAINS, VillagerProfession.NITWIT, 0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setString("VillagerData", this.getProfession().toString();
        compound.setString("ProfessionName", this.getProfessionForge().getRegistryName().toString());
        compound.setInteger("Riches", this.wealth);
        compound.setBoolean("Willing", this.isWillingToMate);

        if (this.buyingList != null)
        {
            compound.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

            if (!itemstack.isEmpty())
            {
                nbttaglist.appendTag(itemstack.writeToNBT(new NBTTagCompound()));
            }
        }

        compound.setTag("Inventory", nbttaglist);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setProfession(compound.getString("VillagerData"));
        if (compound.hasKey("ProfessionName"))
        {
            net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession p =
                net.minecraftforge.fml.common.registry.ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new net.minecraft.util.ResourceLocation(compound.getString("ProfessionName")));
            if (p == null)
                p = net.minecraftforge.fml.common.registry.ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new net.minecraft.util.ResourceLocation("minecraft:farmer"));
            this.setProfession(p);
        }
        this.wealth = compound.getInteger("Riches");
        this.isWillingToMate = compound.getBoolean("Willing");

        if (compound.hasKey("Offers", 10))
        {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(nbttagcompound);
        }

        NBTTagList nbttaglist = compound.getTagList("Inventory", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            ItemStack itemstack = new ItemStack(nbttaglist.getCompoundTagAt(i));

            if (!itemstack.isEmpty())
            {
                this.villagerInventory.addItem(itemstack);
            }
        }

        this.setCanPickUpLoot(true);
        this.setAdditionalAItasks();
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return this.isTrading() ? SoundEvents.ENTITY_VILLAGER_TRADING : SoundEvents.ENTITY_VILLAGER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_VILLAGER;
    }

    public void setProfession(VillagerData professionId)
    {
        this.dataManager.set(PROFESSION, professionId);
//        net.minecraftforge.fml.common.registry.VillagerRegistry.onSetProfession(this, professionId);
    }

    @Deprecated //Use Forge Variant below
    public VillagerData getProfession()
    {
        return this.dataManager.get(PROFESSION);
    }

    private net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof;
    public void setProfession(net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof)
    {
        this.prof = prof;
        this.setProfession(net.minecraftforge.fml.common.registry.VillagerRegistry.getId(prof));
    }

    public net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession getProfessionForge()
    {
        if (this.prof == null)
        {
            this.prof = net.minecraftforge.fml.common.registry.VillagerRegistry.getById(this.getProfession());
            if (this.prof == null)
                return net.minecraftforge.fml.common.registry.VillagerRegistry.getById(0); //Farmer
        }
        return this.prof;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key)
    {
        super.notifyDataManagerChange(key);
        if (key.equals(PROFESSION))
        {
//            net.minecraftforge.fml.common.registry.VillagerRegistry.onSetProfession(this, this.dataManager.get(PROFESSION));
        }
    }

    public boolean isMating()
    {
        return this.isMating;
    }

    public void setMating(boolean mating)
    {
        this.isMating = mating;
    }

    public void setPlaying(boolean playing)
    {
        this.isPlaying = playing;
    }

    public boolean isPlaying()
    {
        return this.isPlaying;
    }

    /**
     * Hint to AI tasks that we were attacked by the passed EntityLivingBase and should retaliate. Is not guaranteed to
     * change our actual active target (for example if we are currently busy attacking someone else)
     */
    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (this.village != null && livingBase != null)
        {
            this.village.addOrRenewAgressor(livingBase);

            if (livingBase instanceof EntityPlayer)
            {
                int i = -1;

                if (this.isChild())
                {
                    i = -3;
                }

                this.village.modifyPlayerReputation(livingBase.getUniqueID(), i);

                if (this.isEntityAlive())
                {
                    this.world.setEntityState(this, (byte)13);
                }
            }
        }
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        if (this.village != null)
        {
            Entity entity = cause.getTrueSource();

            if (entity != null)
            {
                if (entity instanceof EntityPlayer)
                {
                    this.village.modifyPlayerReputation(entity.getUniqueID(), -2);
                }
                else if (entity instanceof IMob)
                {
                    this.village.endMatingSeason();
                }
            }
            else
            {
                EntityPlayer entityplayer = this.world.getClosestPlayerToEntity(this, 16.0D);

                if (entityplayer != null)
                {
                    this.village.endMatingSeason();
                }
            }
        }

        super.onDeath(cause);
    }

    public void setCustomer(@Nullable EntityPlayer player)
    {
        this.buyingPlayer = player;
    }

    @Nullable
    public EntityPlayer getCustomer()
    {
        return this.buyingPlayer;
    }

    public boolean isTrading()
    {
        return this.buyingPlayer != null;
    }

    /**
     * Returns current or updated value of {@link #isWillingToMate}
     */
    public boolean getIsWillingToMate(boolean updateFirst)
    {
        if (!this.isWillingToMate && updateFirst && this.hasEnoughFoodToBreed())
        {
            boolean flag = false;

            for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

                if (!itemstack.isEmpty())
                {
                    if (itemstack.getItem() == Items.BREAD && itemstack.getCount() >= 3)
                    {
                        flag = true;
                        this.villagerInventory.decrStackSize(i, 3);
                    }
                    else if ((itemstack.getItem() == Items.POTATO || itemstack.getItem() == Items.CARROT) && itemstack.getCount() >= 12)
                    {
                        flag = true;
                        this.villagerInventory.decrStackSize(i, 12);
                    }
                }

                if (flag)
                {
                    this.world.setEntityState(this, (byte)18);
                    this.isWillingToMate = true;
                    break;
                }
            }
        }

        return this.isWillingToMate;
    }

    public void setIsWillingToMate(boolean isWillingToMate)
    {
        this.isWillingToMate = isWillingToMate;
    }

    public void useRecipe(MerchantRecipe recipe)
    {
        recipe.incrementToolUses();
        this.livingSoundTime = -this.getTalkInterval();
        this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
        int i = 3 + this.rand.nextInt(4);

        if (recipe.getToolUses() == 1 || this.rand.nextInt(5) == 0)
        {
            this.timeUntilReset = 40;
            this.needsInitilization = true;
            this.isWillingToMate = true;

            if (this.buyingPlayer != null)
            {
                this.lastBuyingPlayer = this.buyingPlayer.getUniqueID();
            }
            else
            {
                this.lastBuyingPlayer = null;
            }

            i += 5;
        }

        if (recipe.getItemToBuy().getItem() == Items.EMERALD)
        {
            this.wealth += recipe.getItemToBuy().getCount();
        }

        if (recipe.getRewardsExp())
        {
            this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY + 0.5D, this.posZ, i));
        }

        if (this.buyingPlayer instanceof EntityPlayerMP)
        {
//            CriteriaTriggers.VILLAGER_TRADE.trigger((EntityPlayerMP)this.buyingPlayer, this, recipe.getItemToSell());
        }
    }

    /**
     * Notifies the merchant of a possible merchantrecipe being fulfilled or not. Usually, this is just a sound byte
     * being played depending if the suggested itemstack is not null.
     */
    public void verifySellingItem(ItemStack stack)
    {
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20)
        {
            this.livingSoundTime = -this.getTalkInterval();
            this.playSound(stack.isEmpty() ? SoundEvents.ENTITY_VILLAGER_NO : SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Nullable
    public MerchantRecipeList getRecipes(EntityPlayer player)
    {
        if (this.buyingList == null)
        {
            this.populateBuyingList();
        }

        return this.buyingList;
    }

    private void populateBuyingList()
    {
        if (this.careerId != 0 && this.careerLevel != 0)
        {
            ++this.careerLevel;
        }
        else
        {
            this.careerId = this.getProfessionForge().getRandomCareer(this.rand) + 1;
            this.careerLevel = 1;
        }

        if (this.buyingList == null)
        {
            this.buyingList = new MerchantRecipeList();
        }

        int i = this.careerId - 1;
        int j = this.careerLevel - 1;
        /*java.util.List<EntityVillager.ITradeList> trades = this.getProfessionForge().getCareer(i).getTrades(j);

        if (trades != null)
        {
            for (EntityVillager.ITradeList entityvillager$itradelist : trades)
            {
                entityvillager$itradelist.addMerchantRecipe(this, this.buyingList, this.rand);
            }
        }*/
    }

    @SideOnly(Side.CLIENT)
    public void setRecipes(@Nullable MerchantRecipeList recipeList)
    {
    }

    public World getWorld()
    {
        return this.world;
    }

    public BlockPos getPos()
    {
        return new BlockPos(this);
    }

    /**
     * Returns a displayable component representing this thing's name. This method should be implemented slightly
     * differently depending on the interface (for <a href="https://github.com/ModCoderPack/MCPBot-
     * Issues/issues/14">technical reasons</a> the same method is used for both IWorldNameable and ICommandSender), but
     * unlike {@link #getName()} this method will generally behave sanely.
     *  
     * <dl>
     * <dt> </dt>
     * <dd>A normal component. Might be a translation component or a text component depending on the context. Usually
     * implemented as:</dd>
     * <dd><pre><code>return this. ? new
     * TextComponentString(this.{@link #getName()}) : new TextComponentTranslation(this.{@link
     * #getName()});</code></pre></dd>
     * <dt>{@link net.minecraft.command.ICommandSender#getDisplayName() ICommandSender.getDisplayName()} and {@link
     * net.minecraft.entity.Entity#getDisplayName() Entity.getDisplayName()}</dt>
     * <dd>For most entities, this returns the result of {@link #getName()}, with {@linkplain
     * net.minecraft.scoreboard.ScorePlayerTeam#formatPlayerName scoreboard formatting} and a {@linkplain
     * net.minecraft.entity.Entity#getHoverEvent special hover event}.</dd>
     * <dd>For non-entity command senders, this will return the result of {@link #getName()} in a text component.</dd>
     * </dl>
     */
    public ITextComponent getDisplayName()
    {
        Team team = this.getTeam();
        String s = this.getCustomNameTag();

        if (!s.isEmpty())
        {
            TextComponentString textcomponentstring = new TextComponentString(ScorePlayerTeam.formatPlayerName(team, s));
            textcomponentstring.getStyle().setHoverEvent(this.getHoverEvent());
            textcomponentstring.getStyle().setInsertion(this.getCachedUniqueIdString());
            return textcomponentstring;
        }
        else
        {
            if (this.buyingList == null)
            {
                this.populateBuyingList();
            }

            String s1;

            switch (this.getProfession())
            {
                case 0:

                    if (this.careerId == 1) s1 = "farmer";
                    else if (this.careerId == 2)
                    {
                        s1 = "fisherman";
                    }
                    else if (this.careerId == 3)
                    {
                        s1 = "shepherd";
                    }
                    else if (this.careerId == 4)
                    {
                        s1 = "fletcher";
                    }

                    break;
                case 1:

                    if (this.careerId == 1)
                    {
                        s1 = "librarian";
                    }
                    else if (this.careerId == 2)
                    {
                        s1 = "cartographer";
                    }

                    break;
                case 2:
                    s1 = "cleric";
                    break;
                case 3:

                    if (this.careerId == 1)
                    {
                        s1 = "armor";
                    }
                    else if (this.careerId == 2)
                    {
                        s1 = "weapon";
                    }
                    else if (this.careerId == 3)
                    {
                        s1 = "tool";
                    }

                    break;
                case 4:

                    if (this.careerId == 1)
                    {
                        s1 = "butcher";
                    }
                    else if (this.careerId == 2)
                    {
                        s1 = "leather";
                    }

                    break;
                case 5:
                    s1 = "nitwit";
            }

            s1 = this.getProfessionForge().getCareer(this.careerId-1).getName();
            {
                ITextComponent itextcomponent = new TextComponentTranslation("entity.Villager." + s1);
                itextcomponent.getStyle().setHoverEvent(this.getHoverEvent());
                itextcomponent.getStyle().setInsertion(this.getCachedUniqueIdString());

                if (team != null)
                {
                    itextcomponent.getStyle().setColor(team.getColor());
                }

                return itextcomponent;
            }
        }
    }

    public float getEyeHeight()
    {
        return this.isChild() ? 0.81F : 1.62F;
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 12)
        {
            this.spawnParticles(EnumParticleTypes.HEART);
        }
        else if (id == 13)
        {
            this.spawnParticles(EnumParticleTypes.VILLAGER_ANGRY);
        }
        else if (id == 14)
        {
            this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(EnumParticleTypes particleType)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(particleType, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory.
     *  
     * The livingdata parameter is used to pass data between all instances during a pack spawn. It will be null on the
     * first call. Subclasses may check if it's null, and then create a new one and return it if so, initializing all
     * entities in the pack with the contained data.
     *  
     * @return The IEntityLivingData to pass to this method for other instances of this entity class within the same
     * pack
     *  
     * @param difficulty The current local difficulty
     * @param livingdata Shared spawn data. Will usually be null. (See return value for more information)
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        return this.finalizeMobSpawn(difficulty, livingdata, true);
    }

    public IEntityLivingData finalizeMobSpawn(DifficultyInstance p_190672_1_, @Nullable IEntityLivingData p_190672_2_, boolean p_190672_3_)
    {
        p_190672_2_ = super.onInitialSpawn(p_190672_1_, p_190672_2_);

        if (p_190672_3_)
        {
//            net.minecraftforge.fml.common.registry.VillagerRegistry.setRandomProfession(this, this.world.rand);
        }

        this.setAdditionalAItasks();
        this.populateBuyingList();
        return p_190672_2_;
    }

    public void setLookingForHome()
    {
        this.isLookingForHome = true;
    }

    public EntityVillager createChild(EntityAgeable ageable)
    {
        EntityVillager entityvillager = new EntityVillager(this.world);
        entityvillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityvillager)), null);
        return entityvillager;
    }

    public boolean canBeLeashedTo(EntityPlayer player)
    {
        return false;
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        if (!this.world.isRemote && !this.isDead)
        {
            EntityWitch entitywitch = new EntityWitch(this.world);
            entitywitch.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entitywitch.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entitywitch)), null);
            entitywitch.setNoAI(this.isAIDisabled());

            if (this.hasCustomName())
            {
                entitywitch.setCustomNameTag(this.getCustomNameTag());
                entitywitch.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
            }

            this.world.spawnEntity(entitywitch);
            this.setDead();
        }
    }

    public InventoryBasic getVillagerInventory()
    {
        return this.villagerInventory;
    }

    /**
     * Tests if this entity should pickup a weapon or an armor. Entity drops current weapon or armor if the new one is
     * better.
     */
    protected void updateEquipmentIfNeeded(EntityItem itemEntity)
    {
        ItemStack itemstack = itemEntity.getItem();
        Item item = itemstack.getItem();

        if (this.canVillagerPickupItem(item))
        {
            ItemStack itemstack1 = this.villagerInventory.addItem(itemstack);

            if (itemstack1.isEmpty())
            {
                itemEntity.setDead();
            }
            else
            {
                itemstack.setCount(itemstack1.getCount());
            }
        }
    }

    private boolean canVillagerPickupItem(Item itemIn)
    {
        return itemIn == Items.BREAD || itemIn == Items.POTATO || itemIn == Items.CARROT || itemIn == Items.WHEAT || itemIn == Items.WHEAT_SEEDS || itemIn == Items.BEETROOT || itemIn == Items.BEETROOT_SEEDS;
    }

    public boolean hasEnoughFoodToBreed()
    {
        return this.hasEnoughItems(1);
    }

    /**
     * Used by {@link net.minecraft.entity.ai.EntityAIVillagerInteract EntityAIVillagerInteract} to check if the
     * villager can give some items from an inventory to another villager.
     */
    public boolean canAbondonItems()
    {
        return this.hasEnoughItems(2);
    }

    public boolean wantsMoreFood()
    {
        boolean flag = this.getProfession() == 0;

        if (flag)
        {
            return !this.hasEnoughItems(5);
        }
        else
        {
            return !this.hasEnoughItems(1);
        }
    }

    /**
     * Returns true if villager has enough items in inventory
     */
    private boolean hasEnoughItems(int multiplier)
    {
        boolean flag = this.getProfession() == 0;

        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

            if (!itemstack.isEmpty())
            {
                if (itemstack.getItem() == Items.BREAD && itemstack.getCount() >= 3 * multiplier || itemstack.getItem() == Items.POTATO && itemstack.getCount() >= 12 * multiplier || itemstack.getItem() == Items.CARROT && itemstack.getCount() >= 12 * multiplier || itemstack.getItem() == Items.BEETROOT && itemstack.getCount() >= 12 * multiplier)
                {
                    return true;
                }

                if (flag && itemstack.getItem() == Items.WHEAT && itemstack.getCount() >= 9 * multiplier)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if villager has seeds, potatoes or carrots in inventory
     */
    public boolean isFarmItemInInventory()
    {
        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

            if (!itemstack.isEmpty() && (itemstack.getItem() == Items.WHEAT_SEEDS || itemstack.getItem() == Items.POTATO || itemstack.getItem() == Items.CARROT || itemstack.getItem() == Items.BEETROOT_SEEDS))
            {
                return true;
            }
        }

        return false;
    }

    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn)
    {
        if (super.replaceItemInInventory(inventorySlot, itemStackIn))
        {
            return true;
        }
        else
        {
            int i = inventorySlot - 300;

            if (i >= 0 && i < this.villagerInventory.getSizeInventory())
            {
                this.villagerInventory.setInventorySlotContents(i, itemStackIn);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static class EmeraldForItems implements EntityVillager.ITradeList
        {
            public Item buyingItem;
            public EntityVillager.PriceInfo price;

            public EmeraldForItems(Item itemIn, EntityVillager.PriceInfo priceIn)
            {
                this.buyingItem = itemIn;
                this.price = priceIn;
            }

            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
            {
                int i = 1;

                if (this.price != null)
                {
                    i = this.price.getPrice(random);
                }

                recipeList.add(new MerchantRecipe(new ItemStack(this.buyingItem, i, 0), Items.EMERALD));
            }
        }

    public interface ITradeList
    {
        void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random);
    }

    public static class ItemAndEmeraldToItem implements EntityVillager.ITradeList
        {
            /**
             * The itemstack to buy with an emerald. The Item and damage value is used only, any tag data is not
             * retained.
             */
            public ItemStack buyingItemStack;
            /** The price info defining the amount of the buying item required with 1 emerald to match the selling item. */
            public EntityVillager.PriceInfo buyingPriceInfo;
            /** The itemstack to sell. The item and damage value are used only, any tag data is not retained. */
            public ItemStack sellingItemstack;
            public EntityVillager.PriceInfo sellingPriceInfo;

            public ItemAndEmeraldToItem(Item p_i45813_1_, EntityVillager.PriceInfo p_i45813_2_, Item p_i45813_3_, EntityVillager.PriceInfo p_i45813_4_)
            {
                this.buyingItemStack = new ItemStack(p_i45813_1_);
                this.buyingPriceInfo = p_i45813_2_;
                this.sellingItemstack = new ItemStack(p_i45813_3_);
                this.sellingPriceInfo = p_i45813_4_;
            }

            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
            {
                int i = this.buyingPriceInfo.getPrice(random);
                int j = this.sellingPriceInfo.getPrice(random);
                recipeList.add(new MerchantRecipe(new ItemStack(this.buyingItemStack.getItem(), i, this.buyingItemStack.getMetadata()), new ItemStack(Items.EMERALD), new ItemStack(this.sellingItemstack.getItem(), j, this.sellingItemstack.getMetadata())));
            }
        }

    public static class ListEnchantedBookForEmeralds implements EntityVillager.ITradeList
        {
            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
            {
                Enchantment enchantment = Enchantment.REGISTRY.getRandomObject(random);
                int i = MathHelper.getInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
                ItemStack itemstack = ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(enchantment, i));
                int j = 2 + random.nextInt(5 + i * 10) + 3 * i;

                if (enchantment.isTreasureEnchantment())
                {
                    j *= 2;
                }

                if (j > 64)
                {
                    j = 64;
                }

                recipeList.add(new MerchantRecipe(new ItemStack(Items.BOOK), new ItemStack(Items.EMERALD, j), itemstack));
            }
        }

    public static class ListEnchantedItemForEmeralds implements EntityVillager.ITradeList
        {
            /** The enchanted item stack to sell */
            public ItemStack enchantedItemStack;
            /** The price info determining the amount of emeralds to trade in for the enchanted item */
            public EntityVillager.PriceInfo priceInfo;

            public ListEnchantedItemForEmeralds(Item p_i45814_1_, EntityVillager.PriceInfo p_i45814_2_)
            {
                this.enchantedItemStack = new ItemStack(p_i45814_1_);
                this.priceInfo = p_i45814_2_;
            }

            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
            {
                int i = 1;

                if (this.priceInfo != null)
                {
                    i = this.priceInfo.getPrice(random);
                }

                ItemStack itemstack = new ItemStack(Items.EMERALD, i, 0);
                ItemStack itemstack1 = EnchantmentHelper.addRandomEnchantment(random, new ItemStack(this.enchantedItemStack.getItem(), 1, this.enchantedItemStack.getMetadata()), 5 + random.nextInt(15), false);
                recipeList.add(new MerchantRecipe(itemstack, itemstack1));
            }
        }
    //MODDERS DO NOT USE OR EDIT THIS IN ANY WAY IT WILL HAVE NO EFFECT, THIS IS JUST IN HERE TO ALLOW FORGE TO ACCESS IT
    @Deprecated
    public static ITradeList[][][][] GET_TRADES_DONT_USE(){ return DEFAULT_TRADE_LIST_MAP; }

    public static class ListItemForEmeralds implements EntityVillager.ITradeList
        {
            /** The item that is being bought for emeralds */
            public ItemStack itemToBuy;
            /**
             * The price info for the amount of emeralds to sell for, or if negative, the amount of the item to buy for
             * an emerald.
             */
            public EntityVillager.PriceInfo priceInfo;

            public ListItemForEmeralds(Item par1Item, EntityVillager.PriceInfo priceInfo)
            {
                this.itemToBuy = new ItemStack(par1Item);
                this.priceInfo = priceInfo;
            }

            public ListItemForEmeralds(ItemStack stack, EntityVillager.PriceInfo priceInfo)
            {
                this.itemToBuy = stack;
                this.priceInfo = priceInfo;
            }

            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
            {
                int i = 1;

                if (this.priceInfo != null)
                {
                    i = this.priceInfo.getPrice(random);
                }

                ItemStack itemstack;
                ItemStack itemstack1;

                if (i < 0)
                {
                    itemstack = new ItemStack(Items.EMERALD);
                    itemstack1 = new ItemStack(this.itemToBuy.getItem(), -i, this.itemToBuy.getMetadata());
                }
                else
                {
                    itemstack = new ItemStack(Items.EMERALD, i, 0);
                    itemstack1 = new ItemStack(this.itemToBuy.getItem(), 1, this.itemToBuy.getMetadata());
                }

                recipeList.add(new MerchantRecipe(itemstack, itemstack1));
            }
        }

    public static class PriceInfo extends Tuple<Integer, Integer>
        {
            public PriceInfo(int p_i45810_1_, int p_i45810_2_)
            {
                super(Integer.valueOf(p_i45810_1_), Integer.valueOf(p_i45810_2_));

                if (p_i45810_2_ < p_i45810_1_)
                {
                    EntityVillager.LOGGER.warn("PriceRange({}, {}) invalid, {} smaller than {}", Integer.valueOf(p_i45810_1_), Integer.valueOf(p_i45810_2_), Integer.valueOf(p_i45810_2_), Integer.valueOf(p_i45810_1_));
                }
            }

            public int getPrice(Random rand)
            {
                return this.getFirst().intValue() >= this.getSecond().intValue() ? this.getFirst().intValue() : this.getFirst().intValue() + rand.nextInt(this.getSecond().intValue() - this.getFirst().intValue() + 1);
            }
        }

    static class TreasureMapForEmeralds implements EntityVillager.ITradeList
        {
            public EntityVillager.PriceInfo value;
            public String destination;
            public MapDecoration.Type destinationType;

            public TreasureMapForEmeralds(EntityVillager.PriceInfo p_i47340_1_, String p_i47340_2_, MapDecoration.Type p_i47340_3_)
            {
                this.value = p_i47340_1_;
                this.destination = p_i47340_2_;
                this.destinationType = p_i47340_3_;
            }

            public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
            {
                int i = this.value.getPrice(random);
                World world = merchant.getWorld();
                BlockPos blockpos = world.findNearestStructure(this.destination, merchant.getPos(), true);

                if (blockpos != null)
                {
                    ItemStack itemstack = ItemMap.setupNewMap(world, (double)blockpos.getX(), (double)blockpos.getZ(), (byte)2, true, true);
                    ItemMap.renderBiomePreviewMap(world, itemstack);
                    MapData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.setTranslatableName("filled_map." + this.destination.toLowerCase(Locale.ROOT));
                    recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, i), new ItemStack(Items.COMPASS), itemstack));
                }
            }
        }
}