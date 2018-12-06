package team.hdt.neutronia.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;
import team.hdt.neutronia.properties.EnumVanillaWoodTypes;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCustomBookshelf extends BlockMod implements INeutroniaBlock {

    public BlockCustomBookshelf(EnumVanillaWoodTypes woodTypes) {
        super(String.format("%s_bookshelf", woodTypes.getName()), Material.WOOD);
        setHardness(1.5F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 1;
    }

    @Override
    public int quantityDropped(Random random) {
        return 3;
    }

    @Override
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.BOOK;
    }

}