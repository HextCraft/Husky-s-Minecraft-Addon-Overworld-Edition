package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AxesBreakLeaves extends Component {

    @SubscribeEvent
    public void calcBreakSpeed(BreakSpeed event) {
        ItemStack stack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
        if (stack.getItem() instanceof ItemAxe && event.getState().getMaterial() == Material.LEAVES)
            event.setNewSpeed((stack.getItem()).getDestroySpeed(stack, Blocks.PLANKS.getDefaultState()));
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

}