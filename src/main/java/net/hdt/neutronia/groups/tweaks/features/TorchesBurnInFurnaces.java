package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TorchesBurnInFurnaces extends Component {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerFuelHandler((ItemStack stack) -> stack.getItem() == Item.getItemFromBlock(Blocks.TORCH) ? 400 : 0);
    }

    @Override
    public String getDescription() {
        return "Makes it so torches burn in furnaces";
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}
