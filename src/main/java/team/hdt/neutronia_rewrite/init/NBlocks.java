package team.hdt.neutronia_rewrite.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import team.hdt.neutronia.properties.EnumVanillaWoodTypes;
import team.hdt.neutronia_rewrite.blocks.BlockNeutroniaBase;
import team.hdt.neutronia_rewrite.blocks.BlockNeutroniaPillar;

public class NBlocks {

    public static Block[] bookshelfs = new Block[5], chests = new Block[5], trappedChests = new Block[5], gtp = new Block[16];

    public static void init() {
        for(EnumVanillaWoodTypes woodType : EnumVanillaWoodTypes.values()) {
            bookshelfs[woodType.getMetadata()] = new BlockNeutroniaBase(String.format("%s_bookshelf", woodType.getName()), Material.WOOD);
//            chests[woodType.getMetadata()] = new BlockCustomChest(String.format("%s_chest", woodType.getName()), BlockChest.Type.BASIC);
//            trappedChests[woodType.getMetadata()] = new BlockCustomChest(String.format("trapped_%s_chest", woodType.getName()), BlockChest.Type.TRAP);
        }
        for(EnumDyeColor color : EnumDyeColor.values()) {
            gtp[color.getMetadata()] = new BlockNeutroniaPillar(Material.ROCK, String.format("%s_glazed_terracotta_pillar", color.getName()));
        }
    }

}
