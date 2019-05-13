package team.abnormal.neutronia.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.base.utils.registry.RegistryUtils;

public class BlockNeutroniaDoor extends BlockModDoor {

    public BlockNeutroniaDoor(String name) {
        super(Material.WOOD);
        setCreativeTab(CreativeTabs.REDSTONE);
        RegistryUtils.registerDoor(this, new ResourceLocation(Reference.MOD_ID, name), CreativeTabs.REDSTONE);
    }

    public BlockNeutroniaDoor(Material material, String name) {
        super(material);
        setCreativeTab(CreativeTabs.REDSTONE);
        RegistryUtils.registerDoor(this, new ResourceLocation(Reference.MOD_ID, name), CreativeTabs.REDSTONE);
    }

}