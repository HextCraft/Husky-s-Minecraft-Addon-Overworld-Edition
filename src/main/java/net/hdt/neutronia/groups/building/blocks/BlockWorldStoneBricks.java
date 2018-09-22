package net.hdt.neutronia.groups.building.blocks;

import net.hdt.huskylib2.block.BlockMetaVariants;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.groups.building.features.WorldStoneBricks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.function.Supplier;

public class BlockWorldStoneBricks extends BlockMetaVariants implements INeutroniaBlock {

    public BlockWorldStoneBricks() {
        super("world_stone_bricks", Material.ROCK, Variants.class);
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
        STONE_GRANITE_BRICKS(WorldStoneBricks.class),
        STONE_DIORITE_BRICKS(WorldStoneBricks.class),
        STONE_ANDESITE_BRICKS(WorldStoneBricks.class);

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
            return GroupLoader.isComponentEnabled(featureLink) && enabledCond.get();
        }

    }

}
