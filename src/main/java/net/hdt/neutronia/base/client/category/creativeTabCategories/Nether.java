package net.hdt.neutronia.base.client.category.creativeTabCategories;

import net.hdt.neutronia.base.client.category.AbstractCreativeCategory;
import net.hdt.neutronia.groups.building.features.SoulStone;
import net.hdt.neutronia.groups.decoration.features.NetherBlocks;
import net.hdt.neutronia.groups.world.features.overworld.Stalactite;
import net.hdt.neutronia.groups.world.features.overworld.Stalagmite;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Nether extends AbstractCreativeCategory {

    public Nether() {
        super("neutronia.category.nether", new ItemStack(Blocks.NETHERRACK));
    }

    @Override
    public void init() {
        add(NetherBlocks.glowingNetherBlocks);
        add(SoulStone.soulStone);
        add(Block.getBlockFromName("neutronia:normal_soulstone_slab"));
        add(Block.getBlockFromName("neutronia:cracked_soulstone_slab"));
        add(Block.getBlockFromName("neutronia:smooth_soulstone_slab"));
        add(Stalactite.basalt_stalactite);
        add(Stalactite.netherrack_stalactite);
        add(Stalagmite.basalt_stalagmite);
        add(Stalagmite.netherrack_stalagmite);
    }

}
