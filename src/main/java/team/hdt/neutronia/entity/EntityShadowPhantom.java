package team.hdt.neutronia.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import team.hdt.neutronia.base.util.handlers.LootTableHandler;

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