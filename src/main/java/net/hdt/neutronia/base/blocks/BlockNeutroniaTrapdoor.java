package net.hdt.neutronia.base.blocks;

import net.hdt.huskylib2.item.ItemModBlock;
import net.hdt.huskylib2.util.ProxyRegistry;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNeutroniaTrapdoor extends BlockTrapDoor implements INeutroniaBlock {

    private final String[] variants;
    private final String bareName;

    public BlockNeutroniaTrapdoor(String name) {
        super(Material.WOOD);

        setHardness(3.0F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(Neutronia.CREATIVE_TAB);

        variants = new String[]{name};
        bareName = name;

        setTranslationKey(name);
        useNeighborBrightness = true;
    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        if (state.getValue(OPEN)) {
            IBlockState down = world.getBlockState(pos.down());
            if (down.getBlock() == Blocks.LADDER)
                return down.getValue(FACING) == state.getValue(FACING);
        }

        return false;
    }

    @Override
    public Block setTranslationKey(String name) {
        super.setTranslationKey(name);
        setRegistryName(LibMisc.PREFIX_MOD + name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemModBlock(this, new ResourceLocation(LibMisc.PREFIX_MOD + name)));
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
