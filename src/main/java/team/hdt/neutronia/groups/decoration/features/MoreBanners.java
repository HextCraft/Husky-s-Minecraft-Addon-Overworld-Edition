package team.hdt.neutronia.groups.decoration.features;

import team.hdt.huskylib.util.ProxyRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import team.hdt.neutronia.base.groups.Component;

import static team.hdt.neutronia.base.lib.LibMisc.MOD_ID;

public class MoreBanners extends Component {

    private boolean dragon, eye, shield, sword;

    public static void addPattern(boolean doit, String name, String id, ItemStack craftingItem) {
        if (!doit)
            return;
        name = MOD_ID + "_" + name;
        id = "n_" + id;
        final Class<?>[] paramTypes = {String.class, String.class, ItemStack.class};
        final Object[] paramValues = {name, id, craftingItem};
        EnumHelper.addEnum(BannerPattern.class, name.toUpperCase(), paramTypes, paramValues);
    }

    @Override
    public void setupConfig() {
        dragon = loadPropBool("Dragon", "", true);
        eye = loadPropBool("Eye", "", true);
        shield = loadPropBool("Shield", "", true);
        sword = loadPropBool("Sword", "", true);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        addPattern(dragon, "dragon_skull", "drs", ProxyRegistry.newStack(Items.SKULL, 1, 5));
        addPattern(eye, "eye", "ey", ProxyRegistry.newStack(Items.ENDER_EYE));
        addPattern(shield, "shield", "sh", ProxyRegistry.newStack(Items.IRON_CHESTPLATE));
        addPattern(sword, "sword", "sw", ProxyRegistry.newStack(Items.IRON_SWORD));
        addPattern(true, "bread", "br", new ItemStack(Items.BREAD));
        addPattern(true, "fish", "fi", new ItemStack(Items.FISH));
        addPattern(true, "fishn", "fis", new ItemStack(Items.FISHING_ROD));
        addPattern(true, "shield_2", "shi", new ItemStack(Items.OAK_DOOR));
        addPattern(true, "pot", "po", new ItemStack(Items.POTIONITEM));
        addPattern(true, "bed", "be", new ItemStack(Items.BED));
        addPattern(true, "book", "bo", new ItemStack(Items.BOOK));
        addPattern(true, "bow", "bow", new ItemStack(Items.BOW));
        addPattern(true, "bucket", "bu", new ItemStack(Items.BUCKET));
        addPattern(true, "write", "wr", new ItemStack(Items.WRITABLE_BOOK));
        addPattern(true, "wheat", "wh", new ItemStack(Items.WHEAT));
        addPattern(true, "paper", "pa", new ItemStack(Items.PAPER));
        addPattern(true, "shear", "sh", new ItemStack(Items.SHEARS));
        addPattern(true, "apple", "ap", new ItemStack(Items.APPLE));
        addPattern(true, "egg", "eg", new ItemStack(Items.EGG));
        addPattern(true, "sword_2", "swo", new ItemStack(Items.GOLDEN_SWORD));
        addPattern(true, "axe", "ax", new ItemStack(Items.GOLDEN_AXE));
        addPattern(true, "hoe", "ho", new ItemStack(Items.GOLDEN_HOE));
        addPattern(true, "pick", "pi", new ItemStack(Items.GOLDEN_PICKAXE));
        addPattern(true, "shovel", "sh", new ItemStack(Items.GOLDEN_SHOVEL));
        addPattern(true, "boots", "boo", new ItemStack(Items.GOLDEN_BOOTS));
        addPattern(true, "legs", "le", new ItemStack(Items.GOLDEN_LEGGINGS));
        addPattern(true, "chest", "ch", new ItemStack(Items.GOLDEN_CHESTPLATE));
        addPattern(true, "helm", "he", new ItemStack(Items.GOLDEN_HELMET));
        addPattern(true, "horse", "ho", new ItemStack(Items.GOLDEN_HORSE_ARMOR));
        addPattern(true, "pumpkin", "pu", new ItemStack(Blocks.PUMPKIN));
        addPattern(true, "tag", "ta", new ItemStack(Items.NAME_TAG));
        addPattern(true, "dragon", "dr", new ItemStack(Items.DRAGON_BREATH));
        addPattern(true, "squid", "sq", new ItemStack(Items.PRISMARINE_SHARD));
        addPattern(true, "balance", "ba", new ItemStack(Items.MILK_BUCKET));
    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

}