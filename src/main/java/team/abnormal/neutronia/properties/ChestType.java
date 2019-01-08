package team.abnormal.neutronia.properties;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import team.abnormal.neutronia.base.Reference;

import java.util.HashMap;
import java.util.Map;

public enum ChestType {
        NONE(""),
        SPRUCE("spruce"),
        BIRCH("birch"),
        JUNGLE("jungle"),
        ACACIA("acacia"),
        DARK_OAK("dark_oak"),
        BAMBOO("bamboo"),;

        public static final ChestType[] VALID_TYPES;
        public static final Map<String, ChestType> NAME_TO_TYPE;

        static {
            VALID_TYPES = new ChestType[]{SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK, BAMBOO};
            NAME_TO_TYPE = new HashMap<>();
            for (ChestType type : VALID_TYPES)
                NAME_TO_TYPE.put(type.name, type);
        }

        public final String name;
        public final ResourceLocation nrmTex;
        public final ResourceLocation dblTex;
        public final ModelResourceLocation normalModel;
        public final ModelResourceLocation trapModel;

        ChestType(String name) {
            this.name = name;
            nrmTex = new ResourceLocation(Reference.PREFIX_MOD + "textures/entity/chest/" + name + ".png");
            dblTex = new ResourceLocation(Reference.PREFIX_MOD + "textures/entity/chest/" + name + "_double.png");

            normalModel = new ModelResourceLocation(new ResourceLocation("neutronia", "wooden_chest"), "inventory");
            trapModel = new ModelResourceLocation(new ResourceLocation("neutronia", "trapped_wooden_chest"), "inventory");
        }

        public static ChestType getType(String type) {
            return NAME_TO_TYPE.getOrDefault(type, NONE);
        }
    }