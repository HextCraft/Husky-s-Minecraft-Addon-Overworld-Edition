package team.abnormal.neutronia.blocks.pumpkin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import team.abnormal.neutronia.init.NBlocks;

public class PumpkinHelper {

    public static Block[] pumpkins = new Block[FaceTypes.values().length];

    public static IBlockState getNext(ResourceLocation registryName) {
        String value = registryName.getPath().replace("pumpkin", "");
        if ("".equals(value)){
            return pumpkins[0].getDefaultState();
        } else {
            int ordinal = Integer.parseInt(value);
            ordinal++;
            if (ordinal == FaceTypes.values().length){
                return NBlocks.PUMPKIN.getDefaultState();
            } else {
                return pumpkins[ordinal].getDefaultState();
            }
        }
    }

    public static void init(){
        for(int i = 0; i < pumpkins.length; i++){
            pumpkins[i] = new BlockPumpkin(FaceTypes.values()[i]);
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
