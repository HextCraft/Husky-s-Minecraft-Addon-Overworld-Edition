package net.hdt.neutronia.groups.building.blocks;

import net.hdt.neutronia.base.blocks.BlockMetaVariants;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.building.features.WorldStoneBricks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

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
        GRANITE_BRICKS(WorldStoneBricks.class),
        DIORITE_BRICKS(WorldStoneBricks.class),
        ANDESITE_BRICKS(WorldStoneBricks.class),
        QUARTZ_BRICKS(WorldStoneBricks.class),
        SANDSTONE_BRICKS(WorldStoneBricks.class),
        RED_SANDSTONE_BRICKS(WorldStoneBricks.class);

        public final Class<? extends Component> featureLink;

        Variants(Class<? extends Component> clazz) {
            featureLink = clazz;
        }

        public boolean isEnabled() {
            return true;
        }

    }

}
