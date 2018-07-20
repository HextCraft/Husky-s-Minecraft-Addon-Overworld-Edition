package net.hdt.neutronia.modules.building.blocks;

import net.hdt.huskylib2.blocks.BlockMetaVariants;
import net.hdt.neutronia.base.blocks.INeutroniaBlock;
import net.hdt.neutronia.base.module.ModuleLoader;
import net.hdt.neutronia.modules.building.features.SoulSandstone;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

public class BlockNewSandstone extends BlockMetaVariants implements INeutroniaBlock {

	public BlockNewSandstone() {
		super("sandstone_new", MOD_ID, Material.ROCK, Variants.class);
		setHardness(0.8F);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

	@Override
	public boolean shouldDisplayVariant(int variant) {
		return ModuleLoader.isFeatureEnabled(SoulSandstone.class) || variant < 4;
	}
	
	public enum Variants implements EnumBase {
		SANDSTONE_SMOOTH(false, true),
		SANDSTONE_BRICKS(true, true),
		RED_SANDSTONE_SMOOTH(false, true),
		RED_SANDSTONE_BRICKS(true, true),
		SOUL_SANDSTONE_SMOOTH(false, true),
		SOUL_SANDSTONE_BRICKS(true, true);

		Variants(boolean stairs, boolean slabs) {
			this.stairs = stairs;
			this.slabs = slabs;
		}
		public final boolean stairs, slabs;
	}

}
