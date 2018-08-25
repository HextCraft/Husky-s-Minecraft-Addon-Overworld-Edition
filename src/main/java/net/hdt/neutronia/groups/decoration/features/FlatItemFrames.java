package net.hdt.neutronia.groups.decoration.features;

import net.hdt.neutronia.base.Neutronia;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.decoration.client.render.RenderFlatItemFrame;
import net.hdt.neutronia.groups.decoration.entity.EntityFlatItemFrame;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FlatItemFrames extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        String flatItemFrameName = "neutronia:flat_item_frame";
        EntityRegistry.registerModEntity(new ResourceLocation(flatItemFrameName), EntityFlatItemFrame.class, flatItemFrameName, LibEntityIDs.FLAT_ITEM_FRAME, Neutronia.instance, 256, 64, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityFlatItemFrame.class, RenderFlatItemFrame.FACTORY);
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.getFace() == null)
            return;

        ItemStack itemstack = event.getItemStack();
        EnumFacing facing = event.getFace();
        BlockPos blockpos = event.getPos().offset(facing);
        World world = event.getWorld();
        EntityPlayer player = event.getEntityPlayer();

        if (!player.canPlayerEdit(blockpos, facing, itemstack) || facing.getAxis() != EnumFacing.Axis.Y || itemstack.getItem() != Items.ITEM_FRAME)
            return;

        EntityHanging entityhanging = new EntityFlatItemFrame(world, blockpos, facing);

        if (entityhanging.onValidSurface()) {
            if (!event.getWorld().isRemote) {
                entityhanging.playPlaceSound();
                world.spawnEntity(entityhanging);
                event.setCanceled(true);

                if (!player.capabilities.isCreativeMode)
                    itemstack.shrink(1);
            } else player.swingArm(event.getHand());
        }
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @Override
    public String[] getIncompatibleMods() {
        return new String[]{"strait"};
    }
}