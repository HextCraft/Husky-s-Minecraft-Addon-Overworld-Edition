package net.hdt.neutronia.groups.client.features;

import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.groups.client.render.RenderPaperDoll;
import net.hdt.neutronia.groups.client.render.RenderSelectedItem;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
        heldItemTooltips = loadProperty("Advanced Held Item Tooltips", true).setComment("Enhances vanilla held item tooltips with information about enchantments, potions effects, shulker box contents, and more.").get();
        heldItemTooltipsRows = loadProperty("Advanced Held Item Tooltips Rows", 5).setMin(2).setMax(7).setComment("Maximum amount of rows to be displayed for held item tooltips.").get();
        heldItemTooltipsBottomMargin = loadProperty("Advanced Held Item Tooltips Margin", 59).setMin(0).setMax(2147483647).setComment("Margin for last row from screen bottom.").get();
        heldItemTooltipsModded = loadProperty("Advanced Held Item Tooltips Modded", false).setComment("Enables tooltip information added by other mods to be displayed as held item tooltips.").get();
        heldItemTooltipsDots = loadProperty("Advanced Held Item Tooltips Dots", false).setComment("Show three dots when the complete tooltip information can't be displayed like on Console Edition instead of the custom text.").get();
        paperDoll = loadProperty("Paper Doll", true).setComment("Shows a small player model in a configurable corner of the screen while the player is sprinting, sneaking, or flying.").get();
        paperDollPosition = loadProperty("Paper Doll Position Preset", 0).setMin(0).setMax(3).setComment("Defines a screen corner to display the paper doll in. [0: top left, 1: bottom left, 2: top right, 3: bottom right, default: 0]").get();
        paperDollScale = loadProperty("Paper Doll Scale", 4).setMin(1).setMax(24).setComment("Scale of the paper doll. This is additionally adjusted by the GUI Scale option in Video Settings.").get();
        paperDollXMargin = loadProperty("Paper Doll X-Margin", 0).setMin(-2147483647).setMax(2147483647).setComment("Margin on x-axis from original doll position.").get();
        paperDollYMargin = loadProperty("Paper Doll Y-Margin", 0).setMin(-2147483647).setMax(2147483647).setComment("Margin on y-axis from original doll position.").get();
        paperDollAlways = loadProperty("Paper Doll Always", false).setComment("Always displays the paper doll, no matter what action the player is performing.").get();
        paperDollSprinting = loadProperty("Paper Doll Sprinting", true).setComment( "Enables the paper doll while the player is sprinting.").get();
        paperDollCrouching = loadProperty("Paper Doll Crouching", true).setComment("Enables the paper doll while the player is crouching.").get();
        paperDollFlying = loadProperty("Paper Doll Flying", true).setComment("Displays the paper doll when the player is using creative mode flight.").get();
        paperDollElytraFlying = loadProperty("Paper Doll Elytra Flying", true).setComment("Shows the paper doll while the player is flying with an elytra.").get();
        paperDollBurning = loadProperty("Paper Doll Burning", false).setComment("Disables flame overlay on the hud when on fire and displays the burning paper doll instead.").get();
        paperDollMounting = loadProperty("Paper Doll Mounting", false).setComment("Shows the paper doll while the player is riding any entity.").get();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderSelectedItem si = new RenderSelectedItem(Minecraft.getMinecraft());
        RenderPaperDoll pd = new RenderPaperDoll(Minecraft.getMinecraft());
        MinecraftForge.EVENT_BUS.register(si);
        MinecraftForge.EVENT_BUS.register(pd);
    }

    @Override
    public String getDescription() {
        return "This adds some features from the bedrock edition";
    }
}