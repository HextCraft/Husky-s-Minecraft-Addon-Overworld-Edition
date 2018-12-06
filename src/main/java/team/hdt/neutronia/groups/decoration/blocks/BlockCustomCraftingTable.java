package team.hdt.neutronia.groups.decoration.blocks;

import team.hdt.huskylib.block.BlockMod;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import team.hdt.neutronia.base.blocks.INeutroniaBlock;
import team.hdt.neutronia.groups.decoration.client.gui.ContainerWorkbench;
import team.hdt.neutronia.properties.EnumVanillaWoodTypes;

public class BlockCustomCraftingTable extends BlockMod implements INeutroniaBlock {

    public BlockCustomCraftingTable(EnumVanillaWoodTypes woodTypes) {
        super(String.format("%s_crafting_table", woodTypes.getName()), Material.WOOD);
        setHardness(1.5F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            playerIn.displayGui(new InterfaceCraftingTable(worldIn, pos));
            playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
            return true;
        }
    }

    public class InterfaceCraftingTable implements IInteractionObject {
        private final World world;
        private final BlockPos position;

        public InterfaceCraftingTable(World worldIn, BlockPos pos) {
            this.world = worldIn;
            this.position = pos;
        }

        public String getName() {
            return "custom_crafting_table";
        }

        public boolean hasCustomName() {
            return false;
        }

        public ITextComponent getDisplayName() {
            return new TextComponentTranslation(getTranslationKey() + ".name");
        }

        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return new ContainerWorkbench(playerInventory, this.world, this.position);
        }

        public String getGuiID() {
            return "neutronia:custom_crafting_table";
        }
    }

}