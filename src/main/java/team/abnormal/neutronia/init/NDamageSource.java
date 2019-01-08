package team.abnormal.neutronia.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import javax.annotation.Nullable;

public class NDamageSource {

    public static final DamageSource BERRY_BUSH = new DamageSource("berryBush");

    public static DamageSource trident(Entity var0, @Nullable Entity var1) {
        return (new EntityDamageSourceIndirect("trident", var0, var1)).setProjectile();
    }

}