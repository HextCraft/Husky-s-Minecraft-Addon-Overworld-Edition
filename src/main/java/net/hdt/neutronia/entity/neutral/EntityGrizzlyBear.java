package net.hdt.neutronia.entity.neutral;

import net.hdt.neutronia.base.util.handlers.LootTableHandler;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGrizzlyBear extends EntityPolarBear {
    public EntityGrizzlyBear(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootTableHandler.GRIZZLY_BEAR;
    }
}