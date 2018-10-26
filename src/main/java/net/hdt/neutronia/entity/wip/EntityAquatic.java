package net.hdt.neutronia.entity.wip;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.world.IWorldReadable;
import net.minecraft.enchantment.DamageCategory;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.sortme.ILiving;
import net.minecraft.entity.PathfinerMob;

public abstract class EntityAquatic extends PathfinerMob implements ILiving
{
    protected EntityAquatic(final EntityType<?> aEntityType1, final World aWorld2) {
        super(aEntityType1, aWorld2);
    }
    
    @Override
    public boolean cc() {
        return true;
    }
    
    @Override
    public DamageCategory getEntityAttribute() {
        return DamageCategory.AQUATIC;
    }
    
    @Override
    public boolean a(final IWorldReadable aIWorldReadable) {
        return aIWorldReadable.a_(this, this.getBoundingBox()) && aIWorldReadable.c(this, this.getBoundingBox());
    }
    
    @Override
    public int z() {
        return 120;
    }
    
    @Override
    public boolean I() {
        return true;
    }
    
    @Override
    protected int b(final EntityPlayer aEntityPlayer) {
        return 1 + this.world.rand.nextInt(3);
    }
    
    protected void a(final int aInteger) {
        if (this.isValid() && !this.as()) {
            this.setBreath(aInteger - 1);
            if (this.getBreath() == -20) {
                this.setBreath(0);
                this.applyDamage(DamageSource.DROWN, 2.0f);
            }
        }
        else {
            this.setBreath(300);
        }
    }
    
    @Override
    public void updateLogic() {
        final int vInteger1 = this.getBreath();
        super.updateLogic();
        this.a(vInteger1);
    }
    
    @Override
    public boolean by() {
        return false;
    }
    
    @Override
    public boolean canBeLeashedBy(final EntityPlayer aEntityPlayer) {
        return false;
    }
}
