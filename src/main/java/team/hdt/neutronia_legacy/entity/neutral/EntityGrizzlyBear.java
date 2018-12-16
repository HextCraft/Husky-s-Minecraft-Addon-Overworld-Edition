package team.hdt.neutronia_legacy.entity.neutral;

import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.base.util.handlers.LootTableHandler;

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