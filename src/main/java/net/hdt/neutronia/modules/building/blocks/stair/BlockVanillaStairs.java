/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 *
 * File Created @ [20/03/2016, 19:34:22 (GMT)]
 */
package net.hdt.neutronia.modules.building.blocks.stair;

import net.hdt.neutronia.base.blocks.BlockNeutroniaStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockVanillaStairs extends BlockNeutroniaStairs {

	public BlockVanillaStairs(String name, IBlockState state) {
		super(name, state);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

}
