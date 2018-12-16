package team.hdt.neutronia_revamped.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import team.hdt.neutronia_legacy.properties.EnumVanillaWoodTypes;
import team.hdt.neutronia_revamped.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_revamped.blocks.BlockNeutroniaPillar;
import team.hdt.neutronia_revamped.properties.EnumGTPVariants;

public class NBlocks {

    public static Block[] bookshelfs = new Block[5], chests = new Block[5], trappedChests = new Block[5], gtp = new Block[13];

    public static void init() {
        for(EnumVanillaWoodTypes woodType : EnumVanillaWoodTypes.values()) {
            bookshelfs[woodType.getMetadata()] = new BlockNeutroniaBase(String.format("%s_bookshelf", woodType.getName()), Material.WOOD);
//            chests[woodType.getMetadata()] = new BlockCustomChest(String.format("%s_chest", woodType.getName()), BlockChest.Type.BASIC);
//            trappedChests[woodType.getMetadata()] = new BlockCustomChest(String.format("trapped_%s_chest", woodType.getName()), BlockChest.Type.TRAP);
        }

        for(EnumGTPVariants color : EnumGTPVariants.values()) {
            gtp[color.getId()] = new BlockNeutroniaPillar(Material.ROCK, String.format("%s_glazed_terracotta_pillar", color.getName()));
        }
    }

}
