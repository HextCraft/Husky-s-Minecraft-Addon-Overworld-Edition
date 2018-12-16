package team.hdt.neutronia_legacy.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.util.handlers.LootTableHandler;

import javax.annotation.Nullable;

public class EntityShadowPhantom extends EntityPhantomBase {

    public EntityShadowPhantom(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.PHANTOM;
    }

}