package team.abnormal.neutronia.blocks;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaBase;
import team.abnormal.neutronia.init.NItems;
import team.abnormal.neutronia.init.NSoundEvents;

import javax.annotation.Nullable;
import java.util.Random;

public class ComposterBlock extends BlockNeutroniaBase {
   public static final PropertyInteger LEVEL;
   public static final Object2FloatMap<Item> ITEM_TO_LEVEL_INCREASE_CHANCE;
   public static final AxisAlignedBB RAY_TRACE_SHAPE;

   public static void registerDefaultCompostableItems() {
      ITEM_TO_LEVEL_INCREASE_CHANCE.defaultReturnValue(-1.0F);
      registerCompostableItem(0.1F, new ItemStack(Blocks.LEAVES));
      registerCompostableItem(0.1F, new ItemStack(Blocks.LEAVES2));
      registerCompostableItem(0.1F, new ItemStack(Blocks.SAPLING));
      registerCompostableItem(0.1F, Items.BEETROOT_SEEDS);
      registerCompostableItem(0.1F, new ItemStack(Blocks.GRASS));
      registerCompostableItem(0.1F, Items.MELON_SEEDS);
      registerCompostableItem(0.1F, Items.PUMPKIN_SEEDS);
      registerCompostableItem(0.1F, NItems.SWEET_BERRIES);
      registerCompostableItem(0.1F, Items.WHEAT_SEEDS);
      registerCompostableItem(0.2F, new ItemStack(Blocks.TALLGRASS));
      registerCompostableItem(0.2F, new ItemStack(Blocks.CACTUS));
      registerCompostableItem(0.2F, new ItemStack(Blocks.REEDS));
      registerCompostableItem(0.2F, new ItemStack(Blocks.VINE));
      registerCompostableItem(0.2F, Items.MELON);
      registerCompostableItem(0.5F, new ItemStack(Blocks.WATERLILY));
      registerCompostableItem(0.5F, new ItemStack(Blocks.PUMPKIN));
      registerCompostableItem(0.5F, new ItemStack(Blocks.MELON_BLOCK));
      registerCompostableItem(0.5F, Items.APPLE);
      registerCompostableItem(0.5F, Items.BEETROOT);
      registerCompostableItem(0.5F, Items.CARROT);
      registerCompostableItem(0.5F, new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getMetadata()));
      registerCompostableItem(0.5F, Items.POTATO);
      registerCompostableItem(0.5F, Items.WHEAT);
      registerCompostableItem(0.5F, new ItemStack(Blocks.BROWN_MUSHROOM));
      registerCompostableItem(0.5F, new ItemStack(Blocks.RED_MUSHROOM));
      registerCompostableItem(0.5F, new ItemStack(Blocks.RED_MUSHROOM_BLOCK));
      registerCompostableItem(0.5F, new ItemStack(Blocks.YELLOW_FLOWER));
      registerCompostableItem(0.5F, new ItemStack(Blocks.RED_FLOWER));
      registerCompostableItem(0.5F, new ItemStack(Blocks.DOUBLE_PLANT));
      registerCompostableItem(0.5F, new ItemStack(Blocks.DEADBUSH));
      registerCompostableItem(0.1F, NItems.BLACKBERRIES);
      registerCompostableItem(0.1F, NItems.BLUEBERRIES);
      registerCompostableItem(0.1F, NItems.GOOSEBERRIES);
      registerCompostableItem(0.1F, NItems.POISON_BERRIES);
      registerCompostableItem(0.1F, NItems.WITHER_BERRIES);
      registerCompostableItem(0.1F, NItems.RED_GRAPES);
      registerCompostableItem(0.1F, NItems.GREEN_GRAPES);
      registerCompostableItem(0.1F, NItems.RASPBERRIES);
      registerCompostableItem(0.1F, NItems.SOUR_BERRIES);
      registerCompostableItem(0.1F, NItems.CURRANTS);
      registerCompostableItem(0.8F, Items.POISONOUS_POTATO);
      registerCompostableItem(0.8F, Items.BEEF);
      registerCompostableItem(0.8F, Items.RABBIT);
      registerCompostableItem(0.8F, Items.BREAD);
      registerCompostableItem(0.8F, Items.BAKED_POTATO);
      registerCompostableItem(0.8F, Items.COOKIE);
      registerCompostableItem(1.0F, new ItemStack(Blocks.CAKE));
      registerCompostableItem(1.0F, Items.PUMPKIN_PIE);
      registerCompostableItem(0.5F, Items.ROTTEN_FLESH);
   }

   private static void registerCompostableItem(float float_1, Item itemProvider_1) {
      ITEM_TO_LEVEL_INCREASE_CHANCE.put(itemProvider_1, float_1);
   }

   private static void registerCompostableItem(float float_1, ItemStack itemProvider_1) {
      ITEM_TO_LEVEL_INCREASE_CHANCE.put(itemProvider_1.getItem(), float_1);
   }

   public ComposterBlock() {
      super("composter", Material.WOOD);
      this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0));
   }

   @Nullable
   @Override
   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
      return RAY_TRACE_SHAPE;
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   @Override
   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      int int_1 = state.getValue(LEVEL);
      ItemStack itemStack_1 = playerIn.getHeldItem(hand);
      if (int_1 < 8 && ITEM_TO_LEVEL_INCREASE_CHANCE.containsKey(itemStack_1.getItem())) {
         if (int_1 < 7 && !worldIn.isRemote) {
            boolean boolean_1 = addToComposter(state, worldIn, pos, itemStack_1);
            worldIn.playSound(null, pos, boolean_1 ? NSoundEvents.BLOCK_COMPOSTER_FILL_SUCCESS : NSoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!playerIn.capabilities.isCreativeMode) {
               itemStack_1.setItemDamage(1);
            }
         }

         return true;
      } else if (int_1 == 8) {
         if (!worldIn.isRemote) {
            float float_1 = 0.7F;
            double double_1 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.15000000596046448D;
            double double_2 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
            double double_3 = (double)(worldIn.rand.nextFloat() * 0.7F) + 0.15000000596046448D;
            EntityItem itemEntity_1 = new EntityItem(worldIn, (double)pos.getX() + double_1, (double)pos.getY() + double_2, (double)pos.getZ() + double_3, new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getMetadata()));
            itemEntity_1.setDefaultPickupDelay();
            worldIn.spawnEntity(itemEntity_1);
         }

         emptyComposter(state, worldIn, pos);
         worldIn.playSound(null, pos, NSoundEvents.BLOCK_COMPOSTER_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
         return true;
      } else {
         return false;
      }
   }

   private static void emptyComposter(IBlockState blockState_1, World iWorld_1, BlockPos blockPos_1) {
      iWorld_1.setBlockState(blockPos_1, blockState_1.withProperty(LEVEL, 0), 3);
   }

   private static boolean addToComposter(IBlockState blockState_1, World iWorld_1, BlockPos blockPos_1, ItemStack itemStack_1) {
      int int_1 = (Integer)blockState_1.getValue(LEVEL);
      float float_1 = ITEM_TO_LEVEL_INCREASE_CHANCE.getFloat(itemStack_1.getItem());
      if ((int_1 != 0 || float_1 <= 0.0F) && iWorld_1.rand.nextDouble() >= (double)float_1) {
         return false;
      } else {
         int int_2 = int_1 + 1;
         iWorld_1.setBlockState(blockPos_1, blockState_1.withProperty(LEVEL, int_2), 3);
         if (int_2 == 7) {
            iWorld_1.scheduleUpdate(blockPos_1, blockState_1.getBlock(), 20);
         }

         return true;
      }
   }

   @Override
   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
      if(state.getValue(LEVEL) == 7) {
         worldIn.setBlockState(pos, state.cycleProperty(LEVEL), 3);
         worldIn.playSound(null, pos, NSoundEvents.BLOCK_COMPOSTER_READY, SoundCategory.BLOCKS, 1.0F, 1.0F);
      }

      super.updateTick(worldIn, pos, state, rand);
   }

   @Override
   public boolean hasComparatorInputOverride(IBlockState state) {
      return true;
   }

   @Override
   public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
      return blockState.getValue(LEVEL);
   }

   @Override
   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, LEVEL);
   }

   @Override
   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
      return false;
   }



   public ISidedInventory getInventory(IBlockState blockState_1, World iWorld_1, BlockPos blockPos_1) {
      int int_1 = (Integer)blockState_1.getValue(LEVEL);
      if (int_1 == 8) {
         return new ComposterBlock.FullComposterInventory(blockState_1, iWorld_1, blockPos_1, new ItemStack(Items.DYE, 0, EnumDyeColor.WHITE.getMetadata()));
      } else {
         return (ISidedInventory)(int_1 < 7 ? new ComposterBlock.ComposterInventory(blockState_1, iWorld_1, blockPos_1) : new ComposterBlock.class_3925());
      }
   }

   static {
      LEVEL = PropertyInteger.create("level", 0, 8);
      ITEM_TO_LEVEL_INCREASE_CHANCE = new Object2FloatOpenHashMap();
      RAY_TRACE_SHAPE = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
      /*LEVEL_TO_COLLISION_SHAPE = (VoxelShape[])SystemUtil.consume(new VoxelShape[9], (voxelShapes_1) -> {
         for(int int_1 = 0; int_1 < 8; ++int_1) {
            voxelShapes_1[int_1] = VoxelShapes.combine(RAY_TRACE_SHAPE, Block.createCubeShape(2.0D, (double)Math.max(2, 1 + int_1 * 2), 2.0D, 14.0D, 16.0D, 14.0D), BooleanBiFunction.ONLY_FIRST);
         }

         voxelShapes_1[8] = voxelShapes_1[7];
      });*/
   }

   static class ComposterInventory extends InventoryBasic implements ISidedInventory {
      private final IBlockState state;
      private final World world;
      private final BlockPos pos;
      private boolean dirty;

      public ComposterInventory(IBlockState blockState_1, World iWorld_1, BlockPos blockPos_1) {
         super(new TextComponentTranslation("inventory.composter"), 1);
         this.state = blockState_1;
         this.world = iWorld_1;
         this.pos = blockPos_1;
      }

      @Override
      public int getInventoryStackLimit() {
         return 1;
      }

      @Override
      public int[] getSlotsForFace(EnumFacing side) {
         return side == EnumFacing.UP ? new int[]{0} : new int[]{0};
      }

      @Override
      public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
         return !this.dirty && direction == EnumFacing.UP && ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.containsValue(itemStackIn.getItem());
      }

      @Override
      public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
         return false;
      }

      public void markDirty() {
         ItemStack itemStack_1 = this.getStackInSlot(0);
         if (!itemStack_1.isEmpty()) {
            this.dirty = true;
            ComposterBlock.addToComposter(this.state, this.world, this.pos, itemStack_1);
            this.removeStackFromSlot(0);
         }

      }
   }

   static class FullComposterInventory extends InventoryBasic implements ISidedInventory {
      private final IBlockState state;
      private final World world;
      private final BlockPos pos;
      private boolean dirty;

      public FullComposterInventory(IBlockState blockState_1, World iWorld_1, BlockPos blockPos_1, ItemStack itemStack_1) {
         super(new TextComponentTranslation("inventory.full_composter"), 1);
         this.state = blockState_1;
         this.world = iWorld_1;
         this.pos = blockPos_1;
      }

      public int getInventoryStackLimit() {
         return 1;
      }

      public int[] getSlotsForFace(EnumFacing direction_1) {
         return direction_1 == EnumFacing.DOWN ? new int[]{0} : new int[0];
      }

      @Override
      public boolean canInsertItem(int int_1, ItemStack itemStack_1, @Nullable EnumFacing direction_1) {
         return false;
      }

      @Override
      public boolean canExtractItem(int int_1, ItemStack itemStack_1, EnumFacing direction_1) {
         return !this.dirty && direction_1 == EnumFacing.DOWN && itemStack_1.equals(new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getMetadata()));
      }

      public void markDirty() {
         ComposterBlock.emptyComposter(this.state, this.world, this.pos);
         this.dirty = true;
      }
   }

   static class class_3925 extends InventoryBasic implements ISidedInventory {
      public class_3925() {
         super(new TextComponentTranslation("inventory.idk"), 0);
      }

      @Override
      public int[] getSlotsForFace(EnumFacing side) {
         return new int[0];
      }

      public boolean canInsertItem(int int_1, ItemStack itemStack_1, @Nullable EnumFacing direction_1) {
         return false;
      }

      public boolean canExtractItem(int int_1, ItemStack itemStack_1, EnumFacing direction_1) {
         return false;
      }
   }
}
