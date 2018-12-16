package team.hdt.neutronia_legacy.base.util.player;

import com.google.common.collect.Sets;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Set of methods dealing with EntityPlayer
 *
 * @author Koward
 */
public final class PlayerHelper {

    private PlayerHelper() {

    }

    private static Set<ItemStack> getHolding(@Nullable EntityPlayer player) {
        if (player == null)
            return Sets.newHashSet();
        return Sets.newHashSet(player.getHeldItemMainhand(), player.getHeldItemOffhand()).stream().filter(s -> !s.isEmpty()).collect(Collectors.toSet());
    }

    public static boolean isHolding(@Nullable EntityPlayer player, Ingredient ingredient) {
        if (player == null)
            return false;

        Set<ItemStack> held = getHolding(player);
        if (held.isEmpty())
            return false;
        for (ItemStack h : held)
            if (ingredient.apply(h))
                return true;
        return false;
    }

    public static boolean isSurvival(EntityPlayer player) {
        return player != null && !player.isCreative() && !player.isSpectator() && !player.isSpectator();
    }

    /**
     * Edit the speed of an entity.
     *
     * @param entity           The entity whose speed will be changed.
     * @param name             Unique name for easier debugging
     * @param modifier         The speed will be multiplied by this number
     * @param penaltySpeedUuid the UUID of the player
     */
    public static void changeSpeed(EntityLivingBase entity,
                                   String name, double modifier, UUID penaltySpeedUuid) {
        //2 operator multiples the current value by 1+x, thus modifier-1 neutralizes the extra 1
        AttributeModifier speedModifier = (new AttributeModifier(penaltySpeedUuid, name, modifier - 1, 2));
        IAttributeInstance iattributeinstance = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

        if (iattributeinstance.getModifier(penaltySpeedUuid) != null) {
            iattributeinstance.removeModifier(speedModifier);
        }
        iattributeinstance.applyModifier(speedModifier);
    }

}