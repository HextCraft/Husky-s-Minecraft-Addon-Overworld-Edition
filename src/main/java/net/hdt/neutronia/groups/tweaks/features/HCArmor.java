package net.hdt.neutronia.groups.tweaks.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.groups.GroupLoader;
import net.hdt.neutronia.groups.tweaks.util.item.StackMap;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by primetoxinz on 5/10/17.
 */
public class HCArmor extends Component {

    private static final StackMap<Integer> weights = new StackMap<>(0);
    private static boolean shieldRebalance;

    public static float getWeight(ItemStack stack) {
        if (!GroupLoader.isFeatureEnabled(HCArmor.class))
            return 0;
        return weights.get(stack);
    }

    private static void initWeights() {

        weights.put(Items.CHAINMAIL_HELMET, OreDictionary.WILDCARD_VALUE, 3);
        weights.put(Items.CHAINMAIL_CHESTPLATE, OreDictionary.WILDCARD_VALUE, 4);
        weights.put(Items.CHAINMAIL_LEGGINGS, OreDictionary.WILDCARD_VALUE, 4);
        weights.put(Items.CHAINMAIL_BOOTS, OreDictionary.WILDCARD_VALUE, 2);

        weights.put(Items.IRON_HELMET, OreDictionary.WILDCARD_VALUE, 5);
        weights.put(Items.IRON_CHESTPLATE, OreDictionary.WILDCARD_VALUE, 8);
        weights.put(Items.IRON_LEGGINGS, OreDictionary.WILDCARD_VALUE, 7);
        weights.put(Items.IRON_BOOTS, OreDictionary.WILDCARD_VALUE, 4);

        weights.put(Items.DIAMOND_HELMET, OreDictionary.WILDCARD_VALUE, 5);
        weights.put(Items.DIAMOND_CHESTPLATE, OreDictionary.WILDCARD_VALUE, 8);
        weights.put(Items.DIAMOND_LEGGINGS, OreDictionary.WILDCARD_VALUE, 7);
        weights.put(Items.DIAMOND_BOOTS, OreDictionary.WILDCARD_VALUE, 4);

        weights.put(Items.GOLDEN_HELMET, OreDictionary.WILDCARD_VALUE, 5);
        weights.put(Items.GOLDEN_CHESTPLATE, OreDictionary.WILDCARD_VALUE, 8);
        weights.put(Items.GOLDEN_LEGGINGS, OreDictionary.WILDCARD_VALUE, 7);
        weights.put(Items.GOLDEN_BOOTS, OreDictionary.WILDCARD_VALUE, 4);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (shieldRebalance) {
            addHardcoreRecipe(new ShapedOreRecipe(null, new ItemStack(Items.SHIELD),
                    "SWS", "WIW", " W ", 'S', Items.LEATHER, 'W', Blocks.PLANKS, 'I', "ingotIron"
            ).setRegistryName("minecraft:shield"));
        }
        initWeights();
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @Override
    public void setupConfig() {
        shieldRebalance = loadPropBool("Shield Rebalance", "Experimental recipes for rebalacing shields", false);
    }

    @Override
    public String getFeatureDescription() {
        return "Gives Armor weight values that effect movement. Changes Entity armor spawning: Zombies only spawn with Iron armor, Skeletons never wear armor.";
    }
}
