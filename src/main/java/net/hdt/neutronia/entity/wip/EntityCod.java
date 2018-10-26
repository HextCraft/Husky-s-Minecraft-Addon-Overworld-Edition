package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.reference.Sounds;
import net.minecraft.sound.Sound;
import javax.annotation.Nullable;
import net.minecraft.reference.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.util.IItemProvider;
import net.minecraft.reference.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityCod extends akg
{
    public EntityCod(final World aWorld) {
        super(EntityType.COD, aWorld);
        this.setSize(0.5f, 0.3f);
    }
    
    @Override
    protected ItemStack l() {
        return new ItemStack(Items.kE);
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_COD;
    }
    
    @Override
    protected Sound getSoundAmbient() {
        return Sounds.ENTITY_COD_AMBIENT;
    }
    
    @Override
    protected Sound getSoundDeath() {
        return Sounds.ENTITY_COD_DEATH;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.ENTITY_COD_HURT;
    }
    
    @Override
    protected Sound dA() {
        return Sounds.ENTITY_COD_FLOP;
    }
}
