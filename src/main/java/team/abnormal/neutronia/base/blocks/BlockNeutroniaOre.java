package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.abnormal.abnormalib.block.BlockMod;
import team.abnormal.neutronia.blocks.INeutroniaBlock;

import java.util.Random;

public class BlockNeutroniaOre extends BlockMod implements INeutroniaBlock {

    private Item drop, ingot;

    public BlockNeutroniaOre(String name) {
        super(name, Material.ROCK);
        this.drop = new ItemBlock(this);
        registerRecipes();
        setHardness(3.0F);
        setResistance(5.0F);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    private void registerRecipes() {
        if (ingot != null) {
            GameRegistry.addSmelting(this, new ItemStack(ingot), 3F);
        }
    }

    public Item getDrop() {
        return drop;
    }

    public Block setIngot(Item ingot) {
        this.ingot = ingot;
        return this;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return new ItemStack(drop).getItem();
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0) {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        } else {
            return this.quantityDropped(random);
        }
    }

}