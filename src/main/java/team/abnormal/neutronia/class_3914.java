//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public interface class_3914 {
    class_3914 field_17304 = new class_3914() {
        public <T> Optional<T> method_17395(BiFunction<World, BlockPos, T> biFunction_1) {
            return Optional.empty();
        }
    };

    static class_3914 method_17392(final World world_1, final BlockPos blockPos_1) {
        return new class_3914() {
            public <T> Optional<T> method_17395(BiFunction<World, BlockPos, T> biFunction_1) {
                return Optional.of(biFunction_1.apply(world_1, blockPos_1));
            }
        };
    }

    <T> Optional<T> method_17395(BiFunction<World, BlockPos, T> var1);

    default <T> T method_17396(BiFunction<World, BlockPos, T> biFunction_1, T object_1) {
        return this.method_17395(biFunction_1).orElse(object_1);
    }

    default void method_17393(BiConsumer<World, BlockPos> biConsumer_1) {
        this.method_17395((world_1, blockPos_1) -> {
            biConsumer_1.accept(world_1, blockPos_1);
            return Optional.empty();
        });
    }
}
