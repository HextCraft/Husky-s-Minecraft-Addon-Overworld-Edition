package team.abnormal.neutronia.blocks.pumpkin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import team.abnormal.neutronia.init.NBlocks;

public class PumpkinHelper {

    public static Block[] pumpkins = new Block[FaceTypes.values().length];

    public static Block[] jack_o_lantern = new Block[FaceTypes.values().length];

    public static IBlockState getPumpkinNext(ResourceLocation registryName) {
        if(registryName.getPath().contains("carved_pumpkin")) {
            String value = registryName.getPath().replace("carved_pumpkin", "");
            if ("".equals(value)) {
                return pumpkins[0].getDefaultState();
            } else {
                int ordinal = Integer.parseInt(value);
                ordinal++;
                if (ordinal == FaceTypes.values().length) {
                    return NBlocks.CARVED_PUMPKIN.getDefaultState();
                } else {
                    return pumpkins[ordinal].getDefaultState();
                }
            }
        }else {
            String value = registryName.getPath().replace("jack_o_lantern", "");
            if ("".equals(value)){
                return jack_o_lantern[0].getDefaultState();
            } else {
                int ordinal = Integer.parseInt(value);
                ordinal++;
                if (ordinal == FaceTypes.values().length){
                    return NBlocks.JACK_O_LANTERN.getDefaultState();
                } else {
                    return jack_o_lantern[ordinal].getDefaultState();
                }
            }
        }
    }

    public static IBlockState getPumpkinReturn(ResourceLocation registryName) {
        if(registryName.getPath().contains("carved_pumpkin")) {
            String value = registryName.getPath().replace("carved_pumpkin", "");

            if ("".equals(value)) {
                return pumpkins[24].getDefaultState();
            } else {
                int ordinal = Integer.parseInt(value);
                ordinal--;
                if (ordinal == -1) {
                    return NBlocks.CARVED_PUMPKIN.getDefaultState();
                } else {
                    return pumpkins[ordinal].getDefaultState();
                }
            }
        }else {
            String value = registryName.getPath().replace("jack_o_lantern", "");

            if ("".equals(value)){
                return jack_o_lantern[24].getDefaultState();
            } else {
                int ordinal = Integer.parseInt(value);
                ordinal--;
                if (ordinal == -1){
                    return NBlocks.JACK_O_LANTERN.getDefaultState();
                } else {
                    return jack_o_lantern[ordinal].getDefaultState();
                }
            }
        }
    }

    public enum FaceTypes implements IStringSerializable {
        CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
        OR, SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK;

        @Override
        public String getName() {
            return this.toString().toLowerCase();
        }

    }



}
