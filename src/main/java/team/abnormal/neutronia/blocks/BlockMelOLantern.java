package team.abnormal.neutronia.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaBase;

public class BlockMelOLantern extends BlockNeutroniaBase {

    public BlockMelOLantern() {
        super("mel_o_lantern", Material.GOURD);
        setSoundType(SoundType.WOOD);
        setHardness(1.0F);
    }

}