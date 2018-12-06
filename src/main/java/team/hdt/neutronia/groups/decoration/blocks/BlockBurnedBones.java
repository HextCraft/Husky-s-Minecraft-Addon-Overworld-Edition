package team.hdt.neutronia.groups.decoration.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import team.hdt.neutronia.base.blocks.BlockNeutroniaPillar;

public class BlockBurnedBones extends BlockNeutroniaPillar {

    public BlockBurnedBones() {
        super(Material.ROCK, "burned_bones");
        this.setHardness(2.0F);
        this.setSoundType(SoundType.STONE);
    }

}
