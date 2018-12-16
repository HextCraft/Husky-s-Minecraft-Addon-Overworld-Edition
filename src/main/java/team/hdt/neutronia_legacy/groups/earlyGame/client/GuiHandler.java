package team.hdt.neutronia_legacy.groups.earlyGame.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.IGuiHandler;
import team.hdt.neutronia_legacy.base.util.Reference;

import javax.annotation.Nullable;

import static team.hdt.neutronia_legacy.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_DIRT_CRAFTING_TABLE) {
            return new ContainerCrafting(world, new BlockPos(x, y, z));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_DIRT_CRAFTING_TABLE) {
            return new GuiCraftingDirtWorkbench(player.inventory, world);
        }
        return null;
    }

}
