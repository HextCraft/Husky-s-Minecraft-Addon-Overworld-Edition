package team.hdt.neutronia.groups.decoration.items;

import team.hdt.huskylib.interf.IExtraVariantHolder;
import team.hdt.huskylib.item.ItemModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia.base.lib.LibMisc;
import team.hdt.neutronia.groups.decoration.blocks.BlockCustomChest;
import team.hdt.neutronia.groups.decoration.features.VariedChests;
import team.hdt.neutronia.groups.decoration.tile_entity.TileCustomChest;

import java.util.ArrayList;
import java.util.List;

public class ItemChestBlock extends ItemModBlock implements IExtraVariantHolder {

    public ItemChestBlock(Block block, ResourceLocation res) {
        super(block, res);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return stack -> {
            VariedChests.ChestType type = VariedChests.custom_chest.getCustomType(stack);
            return getBlock() == VariedChests.custom_chest_trap ? type.trapModel : type.normalModel;
        };
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        VariedChests.ChestType type = VariedChests.custom_chest.getCustomType(stack);
        String name = type.name + (getBlock() == VariedChests.custom_chest_trap ? "_trapped" : "");
        return "tile." + LibMisc.PREFIX_MOD + name + "_chest";
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        int typeCnt = 0;

        BlockPos posN = pos.north();
        BlockPos posS = pos.south();
        BlockPos posW = pos.west();
        BlockPos posE = pos.east();

        BlockCustomChest cChest = (BlockCustomChest) getBlock();
        VariedChests.ChestType myType = cChest.getCustomType(stack);

        if (world.getBlockState(posN).getBlock() == block && cChest.getCustomType(world, posN) == myType)
            typeCnt += cChest.isDoubleChest(world, posN, myType) ? 2 : 1;
        if (world.getBlockState(posS).getBlock() == block && cChest.getCustomType(world, posS) == myType)
            typeCnt += cChest.isDoubleChest(world, posS, myType) ? 2 : 1;
        if (world.getBlockState(posW).getBlock() == block && cChest.getCustomType(world, posW) == myType)
            typeCnt += cChest.isDoubleChest(world, posW, myType) ? 2 : 1;
        if (world.getBlockState(posE).getBlock() == block && cChest.getCustomType(world, posE) == myType)
            typeCnt += cChest.isDoubleChest(world, posE, myType) ? 2 : 1;

        if (typeCnt <= 1 && super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState)) {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileCustomChest) {
                ((TileCustomChest) te).chestType = myType;
                return true;
            }
        }

        return false;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        BlockCustomChest chest = (BlockCustomChest) Block.getBlockFromItem(this);
        if (isInCreativeTab(tab))
            for (VariedChests.ChestType type : VariedChests.ChestType.VALID_TYPES)
                subItems.add(chest.setCustomType(new ItemStack(this, 1), type));
    }

    @Override
    public String[] getExtraVariants() {
        List<String> variants = new ArrayList<>();
        for (VariedChests.ChestType type : VariedChests.ChestType.VALID_TYPES) {
            variants.add(type.name + "_chest");
            variants.add(type.name + "_trapped_chest");
        }

        return variants.toArray(new String[0]);
    }

}