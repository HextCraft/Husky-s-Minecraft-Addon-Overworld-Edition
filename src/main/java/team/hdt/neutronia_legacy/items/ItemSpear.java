package team.hdt.neutronia_legacy.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.entity.projectile.EntitySpear;
import team.hdt.neutronia_legacy.items.base.tools.BaseSword;

import java.util.Objects;

public class ItemSpear extends BaseSword {

    public ItemSpear(String name, ToolMaterial material) {
        super(name, material);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack stack = playerIn.getHeldItem(hand);

        if (!playerIn.capabilities.isCreativeMode) {
            stack.grow(-1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ,
                SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL,
                0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote) {
            EntitySpear entity = new EntitySpear(worldIn, playerIn);
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entity);
        }

        playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

}
