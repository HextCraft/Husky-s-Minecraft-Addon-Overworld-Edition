package net.hdt.neutronia.groups.building.blocks;

import net.hdt.huskylib2.block.BlockMetaVariants;
import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.groups.building.features.WorldStoneBricks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.function.Supplier;

public class BlockWorldStoneBricks extends BlockMetaVariants implements INeutroniaBlock {

    public BlockWorldStoneBricks() {
        super("world_stone_bricks", Material.ROCK, Variants.class);
        setHardness(1.5F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        setCreativeTab(Neutronia.NEUTRONIA_MAIN);
    }

    @Override
    public boolean shouldDisplayVariant(int variant) {
        return Variants.class.getEnumConstants()[variant].isEnabled();
    }

    public enum Variants implements EnumBase {
        GRANITE_BRICKS(WorldStoneBricks.class),
        DIORITE_BRICKS(WorldStoneBricks.class),
        ANDESITE_BRICKS(WorldStoneBricks.class),
        QUARTZ_BRICKS(WorldStoneBricks.class),
        SANDSTONE_BRICKS(WorldStoneBricks.class),
        RED_SANDSTONE_BRICKS(WorldStoneBricks.class);

        public final Class<? extends Component> featureLink;
        private final Supplier<Boolean> enabledCond;

        Variants(Class<? extends Component> clazz) {
            this(clazz, () -> true);
        }

        Variants(Class<? extends Component> clazz, Supplier<Boolean> enabledCond) {
            featureLink = clazz;
            this.enabledCond = enabledCond;
        }

        public boolean isEnabled() {
            return GroupLoader.isFeatureEnabled(featureLink) && enabledCond.get();
        }

    }

}
