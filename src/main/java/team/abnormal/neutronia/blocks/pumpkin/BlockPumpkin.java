package team.abnormal.neutronia.blocks.pumpkin;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaBase;

public class BlockPumpkin extends BlockNeutroniaBase {

    public static final PropertyEnum<PumpkinHelper.FaceTypes> TYPE = PropertyEnum.create("type", PumpkinHelper.FaceTypes.class);

    public static final PropertyEnum<EnumFacing> FACING = net.minecraft.block.BlockPumpkin.FACING;

    public BlockPumpkin() {
        super(Material.GOURD, "pumpkin", CreativeTabs.BUILDING_BLOCKS, 1.0f, 1.0f, SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (OreDictionary.itemMatches(new ItemStack(Items.SHEARS), stack, false)) {
            int addition = 1;
            if(playerIn.isSneaking()){
                addition = -1;
            }

            PumpkinHelper.FaceTypes oldType = state.getValue(TYPE);
            PumpkinHelper.FaceTypes newType = PumpkinHelper.FaceTypes.values()[oldType.ordinal() + addition];

            worldIn.setBlockState(pos, state.withProperty(TYPE, newType));
            return false;
        }
        return true;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        StringBuilder builder = new StringBuilder();
        builder.append(state.getValue(FACING).ordinal());
        builder.append(state.getValue(TYPE).ordinal());
        return Integer.parseInt(builder.toString());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        String number = String.valueOf(meta);
        String[] values = {
                number.charAt(0) + "",
                number.substring(1)
        };
        int index$facing = Integer.parseInt(values[0]);
        int index$type = Integer.parseInt(values[1]);
        return getDefaultState().withProperty(FACING, EnumFacing.values()[index$facing])
                .withProperty(TYPE, PumpkinHelper.FaceTypes.values()[index$type]);
    }

    @Override
    public BlockStateContainer getBlockState() {
        return new BlockStateContainer(this, TYPE, FACING);
    }

}
