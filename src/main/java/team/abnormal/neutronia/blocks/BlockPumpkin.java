package team.abnormal.neutronia.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaBase;

public class BlockPumpkin extends BlockNeutroniaBase {

    public static final PropertyEnum<FaceTypes> TYPE = PropertyEnum.create("type", FaceTypes.class);

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

            FaceTypes oldType = state.getValue(TYPE);
            FaceTypes newType = FaceTypes.values()[oldType.ordinal() + addition];

            worldIn.setBlockState(pos, state.withProperty(TYPE, newType));
            return false;
        }
        return true;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        StringBuilder builder = new StringBuilder();
        builder.append(state.getValue(FACING).ordinal());
        builder.append("00");
        builder.append(state.getValue(TYPE).ordinal());
        return Integer.parseInt(builder.toString());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        String number = String.valueOf(meta);
        String[] values = number.split("00");
        if(values.length != 2)
        return getDefaultState();

        int index$facing = Integer.parseInt(values[0]);
        int index$type = Integer.parseInt(values[1]);
        return getDefaultState().withProperty(FACING, EnumFacing.values()[index$facing])
                .withProperty(TYPE, FaceTypes.values()[index$type]);
    }

    @Override
    public BlockStateContainer getBlockState() {
        return new BlockStateContainer(this, TYPE, FACING);
    }

    public enum FaceTypes implements IStringSerializable {
        CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
        OR, SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK;

        @Override
        public String getName() {
            return this.toString();
        }

    }
}
