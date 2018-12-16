package team.hdt.neutronia_legacy.init;

import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.huskylib.recipe.RecipeHandler;
import team.hdt.huskylib.util.ProxyRegistry;
import team.hdt.neutronia_legacy.items.ItemNeutroniaBase;
import team.hdt.neutronia_legacy.items.ItemSpear;
import team.hdt.neutronia_legacy.items.ItemTrident;

public class NItems {

    public static final Item woodSpear;
    public static final Item stoneSpear;
    public static final Item ironSpear;
    public static final Item goldSpear;
    public static final Item diamondSpear;

    public static final Item trident;
    public static final Item anchor;
    public static final Item chisel;
    public static final Item logStripper;

    public static Item[] barkItem = new Item[6];

    static {
        chisel = new ItemNeutroniaBase("chisel");
        logStripper = new ItemNeutroniaBase("log_stripper");
        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(chisel, 1), "I", "S", 'I', Items.IRON_INGOT, 'S', Items.STICK);
        RecipeHandler.addShapedRecipe(ProxyRegistry.newStack(logStripper, 1), "II ", " I ", " S ", 'I', Items.IRON_INGOT, 'S', Items.STICK);

        woodSpear = new ItemSpear("wood_spear", Item.ToolMaterial.WOOD).setCreativeTab(null);
        stoneSpear = new ItemSpear("stone_spear", Item.ToolMaterial.STONE).setCreativeTab(null);
        ironSpear = new ItemSpear("iron_spear", Item.ToolMaterial.IRON).setCreativeTab(null);
        goldSpear = new ItemSpear("gold_spear", Item.ToolMaterial.GOLD).setCreativeTab(null);
        diamondSpear = new ItemSpear("diamond_spear", Item.ToolMaterial.DIAMOND).setCreativeTab(null);
        trident = new ItemTrident().setCreativeTab(null);
        anchor = new ItemSpear("anchor", Item.ToolMaterial.IRON).setCreativeTab(null);

        for (BlockPlanks.EnumType woodTypes : BlockPlanks.EnumType.values()) {
            barkItem[woodTypes.getMetadata()] = new ItemNeutroniaBase(String.format("%s_bark_item", woodTypes.getName()));
        }

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

    }

}
