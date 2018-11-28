package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.BlockGlowingPlant;
import net.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CrystalsAndWorldgen extends Component {

    private static Block parsioliteCrystalBlock, parsioliteCrystal,
            ajoiteCrystalBlock, ajoiteCrystal,
            citrineCrystalBlock, citrineCrystal,
            bixbiteCrystalBlock, bixbiteCrystal,
            calciteCrystalBlock, calciteCrystal;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        parsioliteCrystalBlock = new BlockNeutroniaBase(Material.ROCK, "parsionlite_crystal_block");
        parsioliteCrystal = new BlockGlowingPlant("parsionlite_crystal");
        ajoiteCrystalBlock = new BlockNeutroniaBase(Material.ROCK, "ajoite_crystal_block");
        ajoiteCrystal = new BlockGlowingPlant("ajoite_crystal");
        citrineCrystalBlock = new BlockNeutroniaBase(Material.ROCK, "citrine_crystal_block");
        citrineCrystal = new BlockGlowingPlant("citrine_crystal");
        bixbiteCrystalBlock = new BlockNeutroniaBase(Material.ROCK, "bixbite_crystal_block");
        bixbiteCrystal = new BlockGlowingPlant("bixbite_crystal");
        calciteCrystalBlock = new BlockNeutroniaBase(Material.ROCK, "calcite_crystal_block");
        calciteCrystal = new BlockGlowingPlant("calcite_crystal");
    }

    @Override
    public String getComponentInGameConfigName() {
        return "Crystals";
    }

}
