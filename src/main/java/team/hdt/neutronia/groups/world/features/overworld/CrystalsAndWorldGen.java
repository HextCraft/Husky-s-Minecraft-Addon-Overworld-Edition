package team.hdt.neutronia.groups.world.features.overworld;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.blocks.BlockGlowingPlant;
import team.hdt.neutronia.base.groups.Component;
import team.hdt.neutronia.groups.world.blocks.BlockCrystal;
import team.hdt.neutronia.groups.world.properties.CrystalVariant;
import team.hdt.neutronia.groups.world.world.gen.features.CrystalClusterFeature;

import java.util.*;

public class CrystalsAndWorldGen extends Component {

    private static Block prasionliteCrystalBlock, prasionliteCrystal,
            ajoiteCrystalBlock, ajoiteCrystal,
            citrineCrystalBlock, citrineCrystal,
            bixbiteCrystalBlock, bixbiteCrystal,
            calciteCrystalBlock, calciteCrystal;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        prasionliteCrystalBlock = new BlockCrystal(Material.ROCK, "prasionlite_crystal_block");
        prasionliteCrystal = new BlockGlowingPlant("prasionlite_crystal");
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
                Map<IBlockState, IBlockState> crystalAndRocks = new HashMap<>();
                crystalAndRocks.put(prasionliteCrystalBlock.getDefaultState(), prasionliteCrystal.getDefaultState());
                crystalAndRocks.put(ajoiteCrystalBlock.getDefaultState(), ajoiteCrystal.getDefaultState());
                crystalAndRocks.put(citrineCrystalBlock.getDefaultState(), citrineCrystal.getDefaultState());
                crystalAndRocks.put(bixbiteCrystalBlock.getDefaultState(), bixbiteCrystal.getDefaultState());
                crystalAndRocks.put(calciteCrystalBlock.getDefaultState(), calciteCrystal.getDefaultState());

                for(CrystalVariant variant : CrystalVariant.values()) {
                    (new CrystalClusterFeature(2, 4, crystalAndRocks, variant)).generate(event.getWorld(), event.getRand(), pos);
                }
            }
        }

    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}