package team.hdt.neutronia_legacy.base;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static team.hdt.neutronia_legacy.base.util.Reference.MOD_ID;

/**
 * Remaps this mod's {@link Block}s and {@link Item}s after registry names have been changed.
 *
 * @author HuskyTheArtist
 */
final class Remapper<T extends IForgeRegistryEntry<T>> {

    private static final Marker MARKER = MarkerManager.getMarker("Remapper").addParents(MarkerManager.getMarker(MOD_ID));
    /**
     * Custom names to remap. Keys are the old names, values are the new names.
     */
    private static final Map<String, String> customNames;

    static {
        customNames = ImmutableMap.<String, String>builder()
                .put("blue_coral", "tube_coral")
                .put("pink_coral", "brain_coral")
                .put("purple_coral", "bubble_coral")
                .put("red_coral", "fire_coral")
                .put("yellow_coral", "horn_coral")
                .put("blue_dead_coral", "dead_tube_coral")
                .put("pink_dead_coral", "dead_brain_coral")
                .put("purple_dead_coral", "dead_bubble_coral")
                .put("red_dead_coral", "dead_fire_coral")
                .put("yellow_dead_coral", "dead_horn_coral")

                .put("blue_brain_coral", "tube_coral")
                .put("pink_brain_coral", "brain_coral")
                .put("purple_brain_coral", "bubble_coral")
                .put("red_brain_coral", "fire_coral")
                .put("yellow_brain_coral", "horn_coral")
                .put("blue_dead_brain_coral", "dead_tube_coral")
                .put("pink_dead_brain_coral", "dead_brain_coral")
                .put("purple_dead_brain_coral", "dead_bubble_coral")
                .put("red_dead_brain_coral", "dead_fire_coral")
                .put("yellow_dead_brain_coral", "dead_horn_coral")

                .put("blue_coral_fan", "tube_coral_fan")
                .put("pink_coral_fan", "brain_coral_fan")
                .put("purple_coral_fan", "bubble_coral_fan")
                .put("red_coral_fan", "fire_coral_fan")
                .put("yellow_coral_fan", "horn_coral_fan")
                .put("blue_dead_coral_fan", "dead_tube_coral_fan")
                .put("pink_dead_coral_fan", "dead_brain_coral_fan")
                .put("purple_dead_coral_fan", "dead_bubble_coral_fan")
                .put("red_dead_coral_fan", "dead_fire_coral_fan")
                .put("yellow_dead_coral_fan", "dead_horn_coral_fan")

                .put("oak_bark", "oak_wood")
                .put("spruce_bark", "spruce_wood")
                .put("birch_bark", "birch_wood")
                .put("jungle_bark", "jungle_wood")
                .put("acacia_bark", "acacia_wood")
                .put("dark_oak_bark", "dark_oak_wood")

                .put("oak_bark_slab", "oak_wood_slab")
                .put("spruce_bark_slab", "spruce_wood_slab")
                .put("birch_bark_slab", "birch_wood_slab")
                .put("jungle_bark_slab", "jungle_wood_slab")
                .put("acacia_bark_slab", "acacia_wood_slab")
                .put("dark_oak_bark_slab", "dark_oak_wood_slab")

                .put("oak_bark_stair", "oak_wood_stair")
                .put("spruce_bark_stair", "spruce_wood_stair")
                .put("birch_bark_stair", "birch_wood_stair")
                .put("jungle_bark_stair", "jungle_wood_stair")
                .put("acacia_bark_stair", "acacia_wood_stair")
                .put("dark_oak_bark_stair", "dark_oak_wood_stair")
                .build();
    }

    private final List<Predicate<Mapping<T>>> remappingFunctions = ImmutableList.of(this::remapCustomName);

    private Remapper() {
    }

    private void remapAll(final List<Mapping<T>> missingMappings) {
        for (final Mapping<T> missingMapping : missingMappings) {
            Neutronia.LOGGER.info(MARKER, String.format("Trying to remap %s", missingMapping.key));
            final boolean remapped = remappingFunctions.stream().anyMatch(mappingPredicate -> mappingPredicate.test(missingMapping));
            if (!remapped) Neutronia.LOGGER.info(MARKER, String.format("Couldn't remap %s", missingMapping.key));
        }
    }

    private boolean tryRemap(final Mapping<T> missingMapping, final ResourceLocation registryName) {
        final IForgeRegistry<T> registry = missingMapping.registry;
        final T value = registry.getValue(registryName);
        if (registry.containsKey(registryName) && value != null) {
            Neutronia.LOGGER.info(MARKER, String.format("Remapped %s %s to %s", registry.getRegistrySuperType().getSimpleName(), missingMapping.key, registryName));
            missingMapping.remap(value);
            return true;
        }
        return false;
    }

    private boolean remapCustomName(final Mapping<T> missingMapping) {
        final String missingPath = missingMapping.key.getPath();
        if (!customNames.containsKey(missingPath)) return false;
        final String newPath = customNames.get(missingPath);
        final ResourceLocation newRegistryName = new ResourceLocation(missingMapping.key.getPath(), newPath);
        return tryRemap(missingMapping, newRegistryName);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID)
    @SuppressWarnings("unused")
    private static class EventHandler {
        private static final Remapper<Block> blockRemapper = new Remapper<>();
        private static final Remapper<Item> itemRemapper = new Remapper<>();

        @SubscribeEvent
        public static void missingBlockMappings(final RegistryEvent.MissingMappings<Block> event) {
            blockRemapper.remapAll(event.getMappings());
        }

        @SubscribeEvent
        public static void missingItemMappings(final RegistryEvent.MissingMappings<Item> event) {
            itemRemapper.remapAll(event.getMappings());
        }
    }
}