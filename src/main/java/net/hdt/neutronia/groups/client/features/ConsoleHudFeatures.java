package net.hdt.neutronia.groups.client.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.client.render.RenderPaperDoll;
import net.hdt.neutronia.groups.client.render.RenderSelectedItem;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ConsoleHudFeatures extends Component {

    public static boolean heldItemTooltips;
    public static int heldItemTooltipsRows;
    public static int heldItemTooltipsBottomMargin;
    public static boolean heldItemTooltipsModded;
    public static boolean heldItemTooltipsDots;
    public static boolean paperDoll;
    public static int paperDollPosition;
    public static int paperDollScale;
    public static int paperDollXMargin;
    public static int paperDollYMargin;
    public static boolean paperDollAlways;
    public static boolean paperDollSprinting;
    public static boolean paperDollCrouching;
    public static boolean paperDollFlying;
    public static boolean paperDollElytraFlying;
    public static boolean paperDollBurning;
    public static boolean paperDollMounting;

    @Override
    public void setupConfig() {
        heldItemTooltips = loadPropBool("Advanced Held Item Tooltips", "Enhances vanilla held item tooltips with information about enchantments, potions effects, shulker box contents, and more.", false);
        heldItemTooltipsRows = loadPropInt("Advanced Held Item Tooltips Rows", "Maximum amount of rows to be displayed for held item tooltips.", "", 5, 2, 7);
        heldItemTooltipsBottomMargin = loadPropInt("Advanced Held Item Tooltips Margin", "Margin for last row from screen bottom.", "", 59, 0, 2147483647);
        heldItemTooltipsModded = loadPropBool("Advanced Held Item Tooltips Modded", "Enables tooltip information added by other mods to be displayed as held item tooltips.", false);
        heldItemTooltipsDots = loadPropBool("Advanced Held Item Tooltips Dots", "Show three dots when the complete tooltip information can't be displayed like on Console Edition instead of the custom text.", false);
        paperDoll = loadPropBool("Paper Doll", "Shows a small player model in a configurable corner of the screen while the player is sprinting, sneaking, or flying.", false);
        paperDollPosition = loadPropInt("Paper Doll Position Preset", "Defines a screen corner to display the paper doll in. [0: top left, 1: bottom left, 2: top right, 3: bottom right, default: 0]", "", 0, 0, 3);
        paperDollScale = loadPropInt("Paper Doll Scale", "Scale of the paper doll. This is additionally adjusted by the GUI Scale option in Video Settings.", "", 4, 1, 24);
        paperDollXMargin = loadPropInt("Paper Doll X-Margin", "Margin on x-axis from original doll position.", "", 0, -2147483647, 2147483647);
        paperDollYMargin = loadPropInt("Paper Doll Y-Margin", "Margin on y-axis from original doll position.", "", 0, -2147483647, 2147483647);
        paperDollAlways = loadPropBool("Paper Doll Always", "Always displays the paper doll, no matter what action the player is performing.", false);
        paperDollSprinting = loadPropBool("Paper Doll Sprinting", "Enables the paper doll while the player is sprinting.", false);
        paperDollCrouching = loadPropBool("Paper Doll Crouching", "Enables the paper doll while the player is crouching.", false);
        paperDollFlying = loadPropBool("Paper Doll Flying", "Displays the paper doll when the player is using creative mode flight.", false);
        paperDollElytraFlying = loadPropBool("Paper Doll Elytra Flying", "Shows the paper doll while the player is flying with an elytra.", false);
        paperDollBurning = loadPropBool("Paper Doll Burning", "Disables flame overlay on the hud when on fire and displays the burning paper doll instead.", false);
        paperDollMounting = loadPropBool("Paper Doll Mounting", "Shows the paper doll while the player is riding any entity.", false);
    }

    @Override
    public void initClient(FMLInitializationEvent event) {
        RenderSelectedItem si = new RenderSelectedItem(Minecraft.getMinecraft());
        RenderPaperDoll pd = new RenderPaperDoll(Minecraft.getMinecraft());
        MinecraftForge.EVENT_BUS.register(si);
        MinecraftForge.EVENT_BUS.register(pd);
    }

}