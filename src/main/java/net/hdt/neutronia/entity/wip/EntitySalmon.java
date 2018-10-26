package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.reference.Sounds;
import net.minecraft.sound.Sound;
import net.minecraft.util.IItemProvider;
import net.minecraft.reference.Items;
import net.minecraft.item.ItemStack;
import javax.annotation.Nullable;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntitySalmon extends akg
{
    public EntitySalmon(final World aWorld) {
        super(EntityType.SALMON, aWorld);
        this.setSize(0.7f, 0.4f);
    }
    
    @Override
    public int dB() {
        return 5;
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_SALMON;
    }
    
    @Override
    protected ItemStack l() {
        return new ItemStack(Items.kD);
    }
    
    @Override
    protected Sound getSoundAmbient() {
        return Sounds.ENTITY_SALMON_AMBIENT;
    }
    
    @Override
    protected Sound getSoundDeath() {
        return Sounds.ENTITY_SALMON_DEATH;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.ENTITY_SALMON_HURT;
    }
    
    @Override
    protected Sound dA() {
        return Sounds.ENTITY_SALMON_FLOP;
    }
}
