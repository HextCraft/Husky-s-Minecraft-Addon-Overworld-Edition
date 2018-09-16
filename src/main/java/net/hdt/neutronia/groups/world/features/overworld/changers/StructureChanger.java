package net.hdt.neutronia.groups.world.features.overworld.changers;

import com.google.common.collect.Sets;
import net.hdt.huskylib2.event.StructureSetBlockEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;

import java.util.Set;
import java.util.function.BiPredicate;

public class StructureChanger {


    public static IBlockState getConversion(Set<StructureChanger> changers, StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState state) {
        for (StructureChanger changer : changers) {
            if (changer.canConvert(world, pos)) {
                return changer.getConversion(structure, world, pos, relativePos, state);
            }
        }
        return null;
    }

    private BiPredicate<World, BlockPos> predicate;

    public static StructureChanger create(Set<StructureChanger> set, BiPredicate<World, BlockPos> predicate) {
        StructureChanger c = new StructureChanger(predicate);
        set.add(c);
        return c;
    }

    public Set<IChanger> changers = Sets.newHashSet();

    private StructureChanger(BiPredicate<World, BlockPos> predicate) {
        this.predicate = predicate;
    }

    public static void convert(Set<StructureChanger> set, StructureSetBlockEvent event) {
        IBlockState state = getConversion(set, event.getComponent(), event.getWorld(), event.getPos(), event.getRelativePos(), event.getState());
        if (state != null) {
            event.setState(state);
        }
    }

    public StructureChanger addChanger(IChanger changer) {
        this.changers.add(changer);
        return this;
    }

    private boolean canConvert(World world, BlockPos pos) {
        return predicate.test(world, pos);
    }

    public IBlockState getConversion(StructureComponent structure, World world, BlockPos pos, BlockPos relativePos, IBlockState state) {
        for (IChanger changer : changers) {
            if (changer.canChange(structure, world, pos, relativePos, state)) {
                IBlockState newState = changer.change(structure, world, pos, relativePos, state);
                if (newState != null) {
                    return newState;
                }
            }
        }
        return null;
    }


}
