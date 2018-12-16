package team.hdt.neutronia_legacy.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.util.handlers.LootTableHandler;

import javax.annotation.Nullable;

public class EntityBloodPhantom extends EntityPhantomBase {

    public EntityBloodPhantom(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.RED_PHANTOM;
    }

}