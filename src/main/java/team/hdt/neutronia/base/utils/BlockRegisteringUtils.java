package team.hdt.neutronia.base.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import team.hdt.huskylib.block.BlockModStairs;
import team.hdt.neutronia.base.blocks.*;

public class BlockRegisteringUtils {

    public static void addSlabAndStair(String name, Block block, int meta, boolean doit) {
        addSlabAndStair(name, block, meta, true, true, doit);
    }

    public static void addSlabAndStair(String name, Block block, int meta, boolean slab, boolean stairs, boolean doit) {
        if (!doit)
            return;

        IBlockState state = block.getStateFromMeta(meta);
        String stairsName = name + "_stairs";

        if (stairs)
            BlockModStairs.initStairs(block, meta, new BlockNeutroniaStairs(stairsName, state));
        if (slab) {
            MRSlab singleSlab = new MRSlab.Half(name + "_slab", block.getMaterial(block.getDefaultState()), block.getDefaultState());
//            MRSlab doubleSlab = new MRSlab.Double("double_" + name + "_slab", block.getDefaultState(), block.getMaterial(block.getDefaultState()));
            MRSlab doubleSlab = new MRSlab.Double(name + "_slab_double", block.getDefaultState(), block.getMaterial(block.getDefaultState()));
            MRSlab.registerSlab(block, meta, singleSlab, doubleSlab);
        }
    }

    public static void addFenceAndGate(String name, Block block, ItemStack materialTwo, int meta, boolean doIt) {
        addFenceAndGate(name, block, materialTwo, meta, true, true, doIt);
    }

    public static void addFenceAndGate(String name, Block block, ItemStack materialTwo, int meta, boolean fence, boolean fenceGate, boolean doIt) {
        if (!doIt)
            return;

        String wallName;
        IBlockState state = block.getStateFromMeta(meta);

        if (fence) {
            wallName = name + "_fence";
            BlockNeutroniaFence.initFence(block, materialTwo, meta, new BlockNeutroniaFence(wallName, state));
        }

        if (fenceGate) {
            wallName = name + "_fence_gate";
            BlockNeutroniaFenceGate.initFenceGate(block, materialTwo, meta, new BlockNeutroniaFenceGate(wallName));
        }
    }

    public static void addFenceAndGateAlt(String name, Block block, ItemStack materialTwo, int meta, boolean doIt, CreativeTabs creativeTabs) {
        addFenceAndGateAlt(name, block, materialTwo, meta, true, true, doIt, creativeTabs);
    }

    public static void addFenceAndGateAlt(String name, Block block, ItemStack materialTwo, int meta, boolean fence, boolean fenceGate, boolean doIt, CreativeTabs creativeTabs) {
        if (!doIt)
            return;

        String wallName;
        IBlockState state = block.getStateFromMeta(meta);

        if (fence) {
            wallName = name + "_fence";
            BlockNeutroniaFence neutroniaWall = new BlockNeutroniaFence(wallName, state);
            neutroniaWall.setCreativeTab(creativeTabs);
            BlockNeutroniaFence.initFence(block, materialTwo, meta, neutroniaWall);
        }

        if (fenceGate) {
            wallName = name + "_fence_gate";
            BlockNeutroniaFenceGate neutroniaFenceGate = new BlockNeutroniaFenceGate(wallName);
            neutroniaFenceGate.setCreativeTab(creativeTabs);
            BlockNeutroniaFenceGate.initFenceGate(block, materialTwo, meta, neutroniaFenceGate);
        }
    }

    public static void add(String name, Block block, int meta, boolean doit) {
        add(name, block, meta, doit, BlockNeutroniaWall::new);
    }

    public static void add(String name, Block block, int meta, boolean doit, WallSupplier supplier) {
        if (!doit)
            return;

        IBlockState state = block.getStateFromMeta(meta);
        String wallName = name + "_wall";
        BlockNeutroniaWall.initWall(block, meta, supplier.supply(wallName, state));
    }

    public static void add(String name, Block block, int meta, boolean doit, CreativeTabs creativeTabs) {
        add(name, block, meta, doit, BlockNeutroniaWall::new, creativeTabs);
    }

    public static void add(String name, Block block, int meta, boolean doit, WallSupplier supplier, CreativeTabs creativeTabs) {
        if (!doit)
            return;

        IBlockState state = block.getStateFromMeta(meta);
        String wallName = name + "_wall";
        BlockNeutroniaWall neutroniaWall = supplier.supply(wallName, state);
        neutroniaWall.setCreativeTab(creativeTabs);
        BlockNeutroniaWall.initWall(block, meta, neutroniaWall);
    }

    public interface WallSupplier {
        BlockNeutroniaWall supply(String wallName, IBlockState state);
    }

}
