package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.hdt.neutronia.groups.building.features.MoreStoneBlocks;
import net.hdt.neutronia.groups.building.features.WorldStoneBricks;
import net.hdt.neutronia.groups.decoration.features.CenteredGlazedTerracotta;
import net.hdt.neutronia.groups.decoration.features.CutGlazedTerracotta;
import net.hdt.neutronia.groups.decoration.features.GlazedTerracottaPillar;
import net.hdt.neutronia.groups.decoration.features.GlazedTerracottaStripes;
import net.hdt.neutronia.groups.world.features.overworld.Stalactite;
import net.hdt.neutronia.groups.world.features.overworld.Stalagmite;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Overworld extends AbstractCreativeCategory {

    public Overworld() {
        super("neutronia.category.overworld", new ItemStack(Blocks.GRASS));
    }

    @Override
    public void init() {
        add(GlazedTerracottaPillar.glazedTerracottaPillar);
        add(GlazedTerracottaStripes.glazedTerracottaStriped);
        add(CutGlazedTerracotta.cutGlazedTerracotta);
        add(CenteredGlazedTerracotta.centeredGlazedTerracotta);
        add(WorldStoneBricks.world_stone_bricks);
        add(MoreStoneBlocks.newStoneVariants);
        add(Stalactite.andesite_stalactite);
        add(Stalactite.clay_stalactite);
        add(Stalactite.crystal_stalactite);
        add(Stalactite.diorite_stalactite);
        add(Stalactite.dirt_stalactite);
        add(Stalactite.granite_stalactite);
        add(Stalactite.ice_stalactite);
        add(Stalactite.limestone_stalactite);
        add(Stalactite.marble_stalactite);
        add(Stalactite.packed_ice_stalactite);
        add(Stalactite.stone_stalactite);
        add(Stalactite.sandstone_stalactite);
        add(Stalagmite.andesite_stalagmite);
        add(Stalagmite.clay_stalagmite);
        add(Stalagmite.crystal_stalagmite);
        add(Stalagmite.diorite_stalagmite);
        add(Stalagmite.dirt_stalagmite);
        add(Stalagmite.granite_stalagmite);
        add(Stalagmite.ice_stalagmite);
        add(Stalagmite.limestone_stalagmite);
        add(Stalagmite.marble_stalagmite);
        add(Stalagmite.packed_ice_stalagmite);
        add(Stalagmite.stone_stalagmite);
        add(Stalagmite.sandstone_stalagmite);
        add(Block.getBlockFromName("neutronia:stone_stairs"));
        add(Block.getBlockFromName("neutronia:stone_granite_slab"));
        add(Block.getBlockFromName("neutronia:stone_granite_stairs"));
        add(Block.getBlockFromName("neutronia:stone_diorite_slab"));
        add(Block.getBlockFromName("neutronia:stone_diorite_stairs"));
        add(Block.getBlockFromName("neutronia:stone_andesite_slab"));
        add(Block.getBlockFromName("neutronia:stone_andesite_stair"));
        add(Block.getBlockFromName("neutronia:end_bricks_slab"));
        add(Block.getBlockFromName("neutronia:end_bricks_stair"));
        add(Block.getBlockFromName("neutronia:prismarine_slab"));
        add(Block.getBlockFromName("neutronia:prismarine_stairs"));
        add(Block.getBlockFromName("neutronia:prismarine_bricks_slab"));
        add(Block.getBlockFromName("neutronia:prismarine_bricks_stairs"));
        add(Block.getBlockFromName("neutronia:prismarine_dark_slab"));
        add(Block.getBlockFromName("neutronia:prismarine_dark_stairs"));
        add(Block.getBlockFromName("neutronia:red_nether_brick_slab"));
        add(Block.getBlockFromName("neutronia:red_nether_brick_stairs"));
    }

}
