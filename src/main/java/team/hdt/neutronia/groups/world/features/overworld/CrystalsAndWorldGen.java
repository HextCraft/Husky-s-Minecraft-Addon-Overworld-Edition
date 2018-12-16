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
import team.hdt.neutronia.groups.world.properties.CrystalVariant;
import team.hdt.neutronia.groups.world.world.gen.features.CrystalClusterFeature;

import java.util.Random;

public class CrystalsAndWorldGen extends Component {

    private static Block[] crystal = new Block[5], rock = new Block[5];

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        for(CrystalVariant variant : CrystalVariant.values()) {
            crystal[variant.getId()] = new BlockGlowingPlant(String.format("%s_crystal", variant.getName()));
            rock[variant.getId()] = new BlockCrystal(Material.ROCK, String.format("%s_crystal_block", variant.getName()));
        }
    }

    @Override
    public String getComponentInGameConfigName() {
        return "Crystals";
    }

    @SubscribeEvent
    public void onBiomeDecoratePost(DecorateBiomeEvent.Post event) {
        Random rand = event.getRand();
        for (int n = 0; n < 10; n++) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            BlockPos pos = new BlockPos(event.getChunkPos().getXStart() + x, 0, event.getChunkPos().getZStart() + z);
            pos = pos.add(new Random().nextInt(10), rand.nextInt(event.getWorld().getTopSolidOrLiquidBlock(pos).getY() - 10) + 5, +new Random().nextInt(10));
            if (!event.getWorld().isAirBlock(pos.down()) && event.getWorld().isSideSolid(pos.down(), EnumFacing.UP)) {
                for(CrystalVariant variant : CrystalVariant.values()) {
                    (new CrystalClusterFeature(2, 4, crystal[variant.getId()].getDefaultState(), rock[variant.getId()].getDefaultState())).generate(event.getWorld(), event.getRand(), pos);
                }
            }
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}