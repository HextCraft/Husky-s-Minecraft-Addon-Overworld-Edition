package team.hdt.neutronia_legacy.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import team.hdt.neutronia_legacy.base.util.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void playerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event) {
        final EntityPlayer player = event.player;

        final NBTTagCompound entityData = player.getEntityData();
        final NBTTagCompound persistedData = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistedData);

        final String key = Reference.MOD_ID + ":" + "ReceivedItems";
        final String message;

        if (persistedData.getBoolean(key)) {
            message = I18n.translateToLocal("message.neutronia:login.already_received");
        } else {
            persistedData.setBoolean(key, true);
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.APPLE));
            message = I18n.translateToLocal("message.neutronia:login.free_apple");
        }

        final ITextComponent chatComponent = new TextComponentTranslation(message);
        chatComponent.getStyle().setColor(TextFormatting.LIGHT_PURPLE);
        player.sendMessage(chatComponent);
    }

    @SubscribeEvent
    public static void livingDeath(final LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayer && !event.getEntity().getEntityWorld().isRemote) {
            final EntityPlayer player = (EntityPlayer) event.getEntity();
            final BlockPos pos = player.getPosition();
            player.sendMessage(new TextComponentTranslation("message.neutronia:death.coordinates", pos.getX(), pos.getY(), pos.getZ(), player.dimension, player.getEntityWorld().provider.getDimensionType().getName()));
        }
    }
}