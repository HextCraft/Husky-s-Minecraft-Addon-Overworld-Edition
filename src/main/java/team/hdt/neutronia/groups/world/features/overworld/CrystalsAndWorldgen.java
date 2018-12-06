package team.hdt.neutronia.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.blocks.BlockGlowingPlant;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.world.blocks.BlockCrystal;
import team.hdt.neutronia.groups.world.world.gen.features.CrystalClusterFeature;

import java.util.Random;

public class CrystalsAndWorldgen extends Component {

    private static Block parsioliteCrystalBlock, parsioliteCrystal,
            ajoiteCrystalBlock, ajoiteCrystal,
            citrineCrystalBlock, citrineCrystal,
            bixbiteCrystalBlock, bixbiteCrystal,
            calciteCrystalBlock, calciteCrystal;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        parsioliteCrystalBlock = new BlockCrystal(Material.ROCK, "parsionlite_crystal_block");
        parsioliteCrystal = new BlockGlowingPlant("parsionlite_crystal");
        ajoiteCrystalBlock = new BlockCrystal(Material.ROCK, "ajoite_crystal_block");
        ajoiteCrystal = new BlockGlowingPlant("ajoite_crystal");
        citrineCrystalBlock = new BlockCrystal(Material.ROCK, "citrine_crystal_block");
        citrineCrystal = new BlockGlowingPlant("citrine_crystal");
        bixbiteCrystalBlock = new BlockCrystal(Material.ROCK, "bixbite_crystal_block");
        bixbiteCrystal = new BlockGlowingPlant("bixbite_crystal");
        calciteCrystalBlock = new BlockCrystal(Material.ROCK, "calcite_crystal_block");
        calciteCrystal = new BlockGlowingPlant("calcite_crystal");
    }

    @Override
    public String getComponentInGameConfigName() {
        return "Crystals";
    }

    @SubscribeEvent
    public void onBiomeDecoratePost(DecorateBiomeEvent.Post event) {
        Random rand = event.getRand();

        // Add crystals to all caves
        for (int n = 0; n < 10; n++) {
            int x = rand.nextInt(8) + 12;
            int z = rand.nextInt(8) + 12;
            BlockPos pos = new BlockPos(event.getChunkPos().getXStart() + x, 0, event.getChunkPos().getZStart() + z);
            pos = pos.add(new Random().nextInt(10), rand.nextInt(event.getWorld().getTopSolidOrLiquidBlock(pos).getY() - 10) + 5, +new Random().nextInt(10));
            if (!event.getWorld().isAirBlock(pos.down()) && event.getWorld().isSideSolid(pos.down(), EnumFacing.UP)) {
                (new CrystalClusterFeature(1, 4, parsioliteCrystalBlock.getDefaultState(), parsioliteCrystal.getDefaultState())).generate(event.getWorld(), event.getRand(), pos);
                (new CrystalClusterFeature(1, 4, ajoiteCrystalBlock.getDefaultState(), ajoiteCrystal.getDefaultState())).generate(event.getWorld(), event.getRand(), pos);
                (new CrystalClusterFeature(1, 4, citrineCrystalBlock.getDefaultState(), citrineCrystal.getDefaultState())).generate(event.getWorld(), event.getRand(), pos);
                (new CrystalClusterFeature(1, 4, bixbiteCrystalBlock.getDefaultState(), bixbiteCrystal.getDefaultState())).generate(event.getWorld(), event.getRand(), pos);
                (new CrystalClusterFeature(1, 4, calciteCrystalBlock.getDefaultState(), calciteCrystal.getDefaultState())).generate(event.getWorld(), event.getRand(), pos);
            }
        }

    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}