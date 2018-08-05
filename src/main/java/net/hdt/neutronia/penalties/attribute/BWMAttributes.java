package betterwithmods.common.penalties.attribute;

import betterwithmods.BWMod;
import betterwithmods.module.ConfigHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Range;

public class BWMAttributes {

    public static Attribute<Boolean> JUMP, SWIM, HEAL, SPRINT, ATTACK, PAIN, GRUE;
    public static Attribute<Float> SPEED, SPOOKED;

    public static void registerAttributes() {
        JUMP = new BooleanAttribute(new ResourceLocation(BWMod.MODID, "jump"), true).setDescription("Can the player jump with this penalty active?");
        SWIM = new BooleanAttribute(new ResourceLocation(BWMod.MODID, "swim"), true).setDescription("Can the player swim with this penalty active?");
        HEAL = new BooleanAttribute(new ResourceLocation(BWMod.MODID, "heal"), true).setDescription("Can the player heal with this penalty active?");
        SPRINT = new BooleanAttribute(new ResourceLocation(BWMod.MODID, "sprint"), true).setDescription("Can the player sprint with this penalty active?");
        ATTACK = new BooleanAttribute(new ResourceLocation(BWMod.MODID, "attack"), true).setDescription("Can the player attack with this penalty active?");
        PAIN = new BooleanAttribute(new ResourceLocation(BWMod.MODID, "pain"), false).setDescription("Is the player in pain? (Plays the OOF noise periodically)");
        GRUE = new BooleanAttribute(new ResourceLocation(BWMod.MODID, "grue"), false).setDescription("Can the player be eaten by the Grue when this is active?");

        SPEED = new FloatAttribute(new ResourceLocation(BWMod.MODID, "speed"), 1f).setDescription("Speed modifier when this penalty is active. (Multiplies the player's existing speed)");
        SPOOKED = new FloatAttribute(new ResourceLocation(BWMod.MODID, "spooked"), 0f).setDescription("Does the player start to go insane when this is active?");
    }

    public static boolean isCustom(String category) {
        return ConfigHelper.loadPropBool("Customized", category, "Set this to true to allow more granular configs to generate and make the penalties work as you please. Requires the game to be started to generate more configs.", false);
    }

    public static AttributeInstance<Boolean> getBooleanAttribute(IAttribute<Boolean> parent, String category, String penalty, String desc, Boolean defaultValue) {

        boolean value = isCustom(category) ? ConfigHelper.loadPropBool(parent.getRegistryName().getResourcePath(), String.join(".", category, penalty), desc, defaultValue) : defaultValue;
        return new AttributeInstance<>(parent, value);
    }

    public static AttributeInstance<Float> getFloatAttribute(IAttribute<Float> parent, String category, String penalty, String desc, Float defaultValue) {
        float value = isCustom(category) ? (float) ConfigHelper.loadPropDouble(parent.getRegistryName().getResourcePath(), String.join(".", category, penalty), desc, defaultValue) : defaultValue;
        return new AttributeInstance<>(parent, value);
    }


    public static <T extends Number & Comparable> Range<T> getRange(String category, String penalty, String desc, Range<T> defaultValue) {
        if (isCustom(category)) {
            Number max = defaultValue.getMaximum(), min = defaultValue.getMinimum();
            if (max instanceof Float) {
                Float upper = (float) ConfigHelper.loadPropDouble("Upper Range", String.join(".", category, penalty), desc, max.doubleValue());
                Float lower = (float) ConfigHelper.loadPropDouble("Lower Range", String.join(".", category, penalty), desc, min.doubleValue());
                return (Range<T>) Range.between(upper, lower);
            } else if (max instanceof Integer) {
                Integer upper = ConfigHelper.loadPropInt("Upper Range", String.join(".", category, penalty), desc, max.intValue());
                Integer lower = ConfigHelper.loadPropInt("Lower Range", String.join(".", category, penalty), desc, min.intValue());
                return (Range<T>) Range.between(upper, lower);
            }
        } else {
            return defaultValue;
        }
        return null;
    }

}