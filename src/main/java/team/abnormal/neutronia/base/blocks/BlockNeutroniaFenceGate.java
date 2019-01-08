package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import team.abnormal.abnormalib.item.ItemModBlock;
import team.abnormal.abnormalib.recipe.RecipeHandler;
import team.abnormal.abnormalib.util.ProxyRegistry;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.blocks.INeutroniaBlock;

public class BlockNeutroniaFenceGate extends BlockFenceGate implements INeutroniaBlock {

    private final String[] variants;
    private final String bareName;

    public BlockNeutroniaFenceGate(String name) {
        super(BlockPlanks.EnumType.DARK_OAK);

        setHardness(3.0F);
        setSoundType(SoundType.WOOD);

        variants = new String[]{name};
        bareName = name;

        register(name);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

    public static void initFenceGate(Block base, ItemStack baseTwo, int meta, Block block) {
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(block, 6),
                "OXO", "OXO",
                'O', baseTwo, 'X', ProxyRegistry.newStack(base, 1, meta));
    }

    public Block register(String name) {
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
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    @Override
    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[]{POWERED};
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
