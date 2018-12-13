package team.hdt.neutronia.groups.craftingExtension.blocks;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import team.hdt.huskylib.block.BlockFacing;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;

public class BlockBell extends BlockFacing implements INeutroniaBlock {

    private static final PropertyEnum<EnumBellAttachment> ATTACHMENT = PropertyEnum.create("attachment", EnumBellAttachment.class);

    public BlockBell() {
        super("bell", Material.IRON);
        setSoundType(SoundType.ANVIL);
        setDefaultState(this.getDefaultState().withProperty(ATTACHMENT, EnumBellAttachment.FLOOR).withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ATTACHMENT, FACING);
    }

    public enum EnumBellAttachment implements IStringSerializable {
        FLOOR("floor"),
        CEILING("ceiling"),
        SINGLE_WALL("single_wall"),
        DOUBLE_WALL("double_wall");

        private final String name;

        EnumBellAttachment(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

}