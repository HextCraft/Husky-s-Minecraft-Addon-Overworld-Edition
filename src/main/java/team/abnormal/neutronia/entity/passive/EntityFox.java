package team.abnormal.neutronia.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityFox extends EntityAnimal {
    public EntityFox(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
}
