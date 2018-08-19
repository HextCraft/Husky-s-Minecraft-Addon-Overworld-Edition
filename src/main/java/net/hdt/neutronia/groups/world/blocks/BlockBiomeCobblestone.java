package net.hdt.neutronia.groups.world.blocks;

import net.hdt.huskylib2.block.BlockMetaVariants;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.groups.world.features.overworld.CaveBiomes;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.function.Supplier;

public class BlockBiomeCobblestone extends BlockMetaVariants implements INeutroniaBlock {

    public BlockBiomeCobblestone() {
        super("biome_cobblestone", Material.ROCK, Variants.class);
        setHardness(1.5F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public boolean shouldDisplayVariant(int variant) {
        return Variants.class.getEnumConstants()[variant].isEnabled();
    }

    public enum Variants implements EnumBase {
        FIRE_STONE(() -> CaveBiomes.firestoneEnabled),
        ICY_STONE(() -> CaveBiomes.icystoneEnabled);

        private final Supplier<Boolean> enabledCond;

        private Variants(Supplier<Boolean> enabledCond) {
            this.enabledCond = enabledCond;
        }

        public boolean isEnabled() {
            return enabledCond.get();
        }
    }


}
