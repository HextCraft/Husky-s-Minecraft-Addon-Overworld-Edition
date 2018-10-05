package net.hdt.neutronia.groups.world.world.gen.features;

import net.hdt.neutronia.groups.world.util.GeneratorUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenCoralTree extends WorldGenerator {
	//TODO make trees not gen on top of each other
	public final int maxHeight;
	private static final IBlockState WATER = Blocks.WATER.getDefaultState();
	
	public WorldGenCoralTree(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	public boolean generate(World worldIn, Random rand, BlockPos start) {
		//randomly pick a coral color
		IBlockState block = GeneratorUtils.CORAL_BLOCK_TYPES[rand.nextInt(GeneratorUtils.CORAL_BLOCK_TYPES.length)];
				
		int totalHeight = 4 + rand.nextInt(maxHeight-3);
		int trunkHeight = 2 + rand.nextInt(totalHeight/3);
		
		//draw "trunk" of coral tree
		for(int y=0; y<trunkHeight; y++){
			placeBlockInWater(worldIn, start.add(0, y, 0), block);
		}
		
		//reset position to top of trunk
		start = start.add(0, trunkHeight-1, 0);
		
		//try and generate branches in 4 cardinal directions
		//all braches are same width but can have varying heights to make it look semi-symetrical 
		int branchWidth = 1 + rand.nextInt(5);
		
		//North
		if(rand.nextBoolean()) {
			int branchHeight = 1 + rand.nextInt(totalHeight - trunkHeight);
			drawBresehnam(worldIn, start, start.add(0, branchHeight, -branchWidth), block);
		}
		
		//East
		if(rand.nextBoolean()) {
			int branchHeight = 1 + rand.nextInt(totalHeight - trunkHeight);
			drawBresehnam(worldIn, start, start.add(branchWidth, branchHeight, 0), block);
		}
		
		//South
		if(rand.nextBoolean()) {
			int branchHeight = 1 + rand.nextInt(totalHeight - trunkHeight);
			drawBresehnam(worldIn, start, start.add(0, branchHeight, branchWidth), block);
		}
		
		//West
		if(rand.nextBoolean()) {
			int branchHeight = 1 + rand.nextInt(totalHeight - trunkHeight);
			drawBresehnam(worldIn, start, start.add(-branchWidth, branchHeight, 0), block);
		}
		
		return true;
	}
	
	//only place blocks in water and below the sea level surface
	private void placeBlockInWater(World world, BlockPos pos, IBlockState state) {
		if(pos.getY() >= world.getSeaLevel()-1)
			return;
		
		IBlockState toReplace = world.getBlockState(pos);
		if(toReplace == WATER)
			this.setBlockAndNotifyAdequately(world, pos, state);
	}
	
	//Draws a line from {x1, y1, z1} to {x2, y2, z2}
	private void drawBresehnam(World world, BlockPos from, BlockPos to, IBlockState state) {
		for (BlockPos pixel : GeneratorUtils.getBresehnamArrays(from, to)) {
			placeBlockInWater(world, pixel, state);
		}
	}

}