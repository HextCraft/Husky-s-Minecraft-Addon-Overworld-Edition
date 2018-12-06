package team.hdt.neutronia.groups.decoration.features;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import team.hdt.neutronia.base.blocks.BlockNeutroniaBase;
import team.hdt.neutronia.base.groups.Component;

public class SmoothBlocks extends Component {

    public static BlockNeutroniaBase smoothEndBrick;
    public static BlockNeutroniaBase smoothPrismarineBrick;
    public static BlockNeutroniaBase smoothElderPrismarineBrick;
    public static BlockNeutroniaBase smoothBrick;
    public static BlockNeutroniaBase smoothPurpurBlock;
    public static BlockNeutroniaBase smoothNetherBrick;
    public static BlockNeutroniaBase smoothRedNetherBrick;
    public static BlockNeutroniaBase smoothStone;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        smoothEndBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_end_brick", 0.8F, 10.0F, 0.0F, SoundType.STONE);
        smoothPrismarineBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_prismarine_brick", 1.5F, 10.0F, 0.0F, SoundType.STONE);
        smoothElderPrismarineBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_elder_prismarine_brick", 1.5F, 10.0F, 0.0F, SoundType.STONE);
        smoothBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_brick", 2.0F, 10.0F, 0.0F, SoundType.STONE);
        smoothPurpurBlock = new BlockNeutroniaBase(Material.ROCK, "smooth_purpur_block", 1.5F, 10.0F, 0.0F, SoundType.STONE);
        smoothNetherBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_nether_brick", 2.0F, 10.0F, 0.0F, SoundType.STONE);
        smoothRedNetherBrick = new BlockNeutroniaBase(Material.ROCK, "smooth_red_nether_brick", 1.5F, 10.0F, 0.0F, SoundType.STONE);
        smoothStone = new BlockNeutroniaBase(Material.ROCK, "smooth_stone", 1.5F, 10.0F, 0.0F, SoundType.STONE);
    }

}