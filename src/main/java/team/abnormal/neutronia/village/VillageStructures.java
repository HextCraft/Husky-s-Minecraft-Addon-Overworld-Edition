package team.abnormal.neutronia.village;

import net.minecraft.util.ResourceLocation;
import team.abnormal.neutronia.base.utils.registry.Registry;

public interface VillageStructures {

    VillageStructures SMALL_HOUSE = register("small_house");
    VillageStructures BLACKSMITH = register("blacksmith");
    VillageStructures FARM = register("farm");
    VillageStructures LIBRARY = register("library");
    VillageStructures BAKERY = register("bakery");
    VillageStructures TAVERN = register("tavern");
    VillageStructures MEETING_POINT_1 = register("meeting_point_1");
    VillageStructures MEETING_POINT_2 = register("meeting_point_2");
    VillageStructures MEETING_POINT_3 = register("meeting_point_3");
    VillageStructures MEETING_POINT_4 = register("meeting_point_4");

    static VillageStructures register(final String string_1) {
        return Registry.VILLAGE_STRUCTURES.register(new ResourceLocation(string_1), new VillageStructures() {
            public String toString() {
                return string_1;
            }
        });
    }

}
