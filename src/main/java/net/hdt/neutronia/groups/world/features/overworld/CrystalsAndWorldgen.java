package net.hdt.neutronia.groups.world.features.overworld;

import net.hdt.neutronia.base.blocks.BlockGlowingPlant;
import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CrystalsAndWorldgen extends Component {

    private Block parsioliteCrystalBlock, parsioliteCrystal,
            ajoiteCrystalBlock, ajoiteCrystal,
            citrineCrystalBlock, citrineCrystal,
            bixbiteCrystalBlock, bixbiteCrystal,
            calciteCrystalBlock, calciteCrystal;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        parsioliteCrystalBlock = new BlockGlowingPlant("oarsionlite_crystal");
    }

    @Override
    public String getComponentInGameConfigName() {
        return "Crystals";
    }

}
