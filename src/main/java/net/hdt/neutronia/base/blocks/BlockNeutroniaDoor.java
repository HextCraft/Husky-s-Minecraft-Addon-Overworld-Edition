package net.hdt.neutronia.base.blocks;

import net.hdt.huskylib2.util.ProxyRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class BlockNeutroniaDoor extends BlockDoor implements INeutroniaBlock {
    private final Supplier<Item> itemSupplier;
    private String bareName;

    public BlockNeutroniaDoor(String name, Supplier<Item> itemSupplier) {
        super(Material.WOOD);
        this.setHardness(3.0F);
        this.setSoundType(SoundType.WOOD);
        this.itemSupplier = itemSupplier;
        this.bareName = name;

        register(name);
    }

    public Block register(String name) {
        setTranslationKey(name);
        this.setRegistryName(this.getPrefix() + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(itemSupplier.get());
        return this;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.itemSupplier.get();
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(this.itemSupplier.get());
    }

    @Override
    public String getBareName() {
        return bareName;
    }

    @Override
    public IProperty getVariantProp() {
        return null;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    @Override
    public Class getVariantEnum() {
        return null;
    }

    @Override
    public String[] getVariants() {
        return new String[0];
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

}