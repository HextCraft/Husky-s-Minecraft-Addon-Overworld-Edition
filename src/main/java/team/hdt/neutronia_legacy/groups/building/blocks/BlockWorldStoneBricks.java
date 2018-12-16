package team.hdt.neutronia_legacy.groups.building.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import team.hdt.neutronia_legacy.base.blocks.BlockMetaVariants;
import team.hdt.neutronia_legacy.base.blocks.INeutroniaBlock;
import team.hdt.neutronia_legacy.base.groups.Component;
import team.hdt.neutronia_legacy.groups.building.features.WorldStoneBricks;

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
