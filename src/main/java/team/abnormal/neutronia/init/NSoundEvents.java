package team.abnormal.neutronia.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import team.abnormal.neutronia.base.Reference;

public class NSoundEvents {
    public static final SoundEvent ENTITY_PANDA_DEATH = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "entity.panda.death"));
    public static final SoundEvent ENTITY_PANDA_HURT = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "entity.panda.hurt"));
    public static final SoundEvent ENTITY_PANDA_STEP = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "entity.panda.step"));
    public static final SoundEvent ITEM_AXE_STRIP = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "item.axe.strip"));
    public static final SoundEvent ITEM_CROSSBOW_LOADING_START = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "item.crossbow.loading_start"));
    public static final SoundEvent ITEM_CROSSBOW_CHARGE = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "item.crossbow.charge"));
    public static final SoundEvent ITEM_CROSSBOW_FIRE = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "item.crossbow.fire"));

    public static final SoundEvent UI_CARTOGRAPHY_TABLE_TAKE_RESULT = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "ui.cartography_table.take_result"));
    public static final SoundEvent BLOCK_PUMPKIN_CARVE = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "block.pumpkin.carve"));
    public static final SoundEvent BLOCK_COMPOSTER_EMPTY = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "block.composter.empty"));
    public static final SoundEvent BLOCK_COMPOSTER_READY = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "block.composter.ready"));
    public static final SoundEvent BLOCK_COMPOSTER_FILL_SUCCESS = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "block.composter.fill_success"));
    public static final SoundEvent BLOCK_COMPOSTER_FILL = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "block.composter.fill"));
}
