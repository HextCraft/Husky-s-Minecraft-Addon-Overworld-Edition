package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.abnormal.abnormalib.interf.IModBlock;
import team.abnormal.abnormalib.util.ProxyRegistry;
import team.abnormal.neutronia.items.ItemNeutroniaDoor;

public abstract class BlockModDoor extends BlockDoor implements IModBlock {

    private final String[] variants;
    private final String bareName;

    public BlockModDoor(Material materialIn, String name, String... variants) {
        super(materialIn);

        if (variants.length == 0) {
            variants = new String[]{name};
        }

        this.bareName = name;
        this.variants = variants;
        if (registerInConstruction()) {
            register(name);
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }

    public Block register(String name) {
        setTranslationKey(name);
        this.setRegistryName(this.getPrefix() + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemNeutroniaDoor(this, name));
        return this;
    }

    public boolean registerInConstruction() {
        return true;
    }

    public String getBareName() {
        return this.bareName;
    }

    public String[] getVariants() {
        return this.variants;
    }

    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    public IProperty getVariantProp() {
        return null;
    }

    public Class getVariantEnum() {
        return null;
    }

}
