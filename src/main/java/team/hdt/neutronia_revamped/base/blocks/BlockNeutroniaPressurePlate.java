package team.hdt.neutronia_revamped.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.huskylib.item.ItemModBlock;
import team.hdt.huskylib.util.ProxyRegistry;
import team.hdt.neutronia_revamped.Reference;
import team.hdt.neutronia_revamped.blocks.INeutroniaBlock;

import java.util.List;

public abstract class BlockNeutroniaPressurePlate extends BlockPressurePlate implements INeutroniaBlock {

    private final String[] variants;
    private final String bareName;

    public BlockNeutroniaPressurePlate(String name, Material material, Sensitivity sensitivity) {
        super(material, sensitivity);

        bareName = name;
        variants = new String[]{bareName};

        setTranslationKey(bareName);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

    @Override
    protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List<? extends Entity> list = getValidEntities(worldIn, axisalignedbb);

        if (!list.isEmpty())
            for (Entity entity : list)
                if (!entity.doesEntityNotTriggerPressurePlate())
                    return 15;

        return 0;
    }

    protected abstract List<Entity> getValidEntities(World world, AxisAlignedBB aabb);

    @Override
    public Block setTranslationKey(String name) {
        super.setTranslationKey(name);
        setRegistryName(Reference.PREFIX_MOD + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(Reference.PREFIX_MOD + name)));
        return this;
    }

    @Override
    public String getBareName() {
        return bareName;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    @Override
    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    @Override
    public IProperty getVariantProp() {
        return null;
    }

    @Override
    public Class getVariantEnum() {
        return null;
    }

}
