package net.hdt.neutronia.entity.wip;

import java.util.Locale;
import net.minecraft.entity.Entity;
import net.minecraft.entity.tracker.DataTracker;
import net.minecraft.entity.tracker.TrackedDataHandlerRegistry;
import net.minecraft.entity.IEntityData;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.reference.Sounds;
import net.minecraft.sound.Sound;
import javax.annotation.Nullable;
import net.minecraft.reference.LootTables;
import net.minecraft.util.IItemProvider;
import net.minecraft.reference.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTCompound;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.item.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.entity.tracker.TrackedData;

public class EntityTropicalFish extends akg
{
    private static final TrackedData<Integer> b;
    private static final Identifier[] c;
    private static final Identifier[] bC;
    private static final Identifier[] bD;
    public static final int[] a;
    private boolean bE;
    
    private static int a(final a aEntityTropicalFisha1, final DyeColor aDyeColor2, final DyeColor aDyeColor3) {
        return (aEntityTropicalFisha1.a() & 0xFF) | (aEntityTropicalFisha1.b() & 0xFF) << 8 | (aDyeColor2.a() & 0xFF) << 16 | (aDyeColor3.a() & 0xFF) << 24;
    }
    
    public EntityTropicalFish(final World aWorld) {
        super(EntityType.TROPICAL_FISH, aWorld);
        this.bE = true;
        this.setSize(0.5f, 0.4f);
    }
    
    @Sided(Side.CLIENT)
    public static String b(final int aInteger) {
        return "entity.minecraft.tropical_fish.predefined." + aInteger;
    }
    
    @Sided(Side.CLIENT)
    public static DyeColor d(final int aInteger) {
        return DyeColor.a(t(aInteger));
    }
    
    @Sided(Side.CLIENT)
    public static DyeColor p(final int aInteger) {
        return DyeColor.a(u(aInteger));
    }
    
    @Sided(Side.CLIENT)
    public static String q(final int aInteger) {
        final int vInteger2 = s(aInteger);
        final int vInteger3 = v(aInteger);
        return "entity.minecraft.tropical_fish.type." + EntityTropicalFish.a.a(vInteger2, vInteger3);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.<Integer>startTracking(EntityTropicalFish.b, 0);
    }
    
    @Override
    public void serializeCustomData(final NBTCompound aNBTCompound) {
        super.serializeCustomData(aNBTCompound);
        aNBTCompound.setInt("Variant", this.dI());
    }
    
    @Override
    public void deserializeCustomData(final NBTCompound aNBTCompound) {
        super.deserializeCustomData(aNBTCompound);
        this.r(aNBTCompound.getInt("Variant"));
    }
    
    public void r(final int aInteger) {
        this.dataTracker.<Integer>set(EntityTropicalFish.b, aInteger);
    }
    
    @Override
    public boolean c(final int aInteger) {
        return !this.bE;
    }
    
    public int dI() {
        return this.dataTracker.<Integer>get(EntityTropicalFish.b);
    }
    
    @Override
    protected void f(final ItemStack aItemStack) {
        super.f(aItemStack);
        final NBTCompound vNBTCompound2 = aItemStack.getTagSafely();
        vNBTCompound2.setInt("BucketVariantTag", this.dI());
    }
    
    @Override
    protected ItemStack l() {
        return new ItemStack(Items.kF);
    }
    
    @Nullable
    @Override
    protected Identifier getLootTableId() {
        return LootTables.ENTITY_TROPICAL_FISH;
    }
    
    @Override
    protected Sound getSoundAmbient() {
        return Sounds.ENTITY_TROPICAL_FISH_AMBIENT;
    }
    
    @Override
    protected Sound getSoundDeath() {
        return Sounds.ENTITY_TROPICAL_FISH_DEATH;
    }
    
    @Override
    protected Sound getSoundHurt(final DamageSource aDamageSource) {
        return Sounds.ENTITY_TROPICAL_FISH_HURT;
    }
    
    @Override
    protected Sound dA() {
        return Sounds.ENTITY_TROPICAL_FISH_FLOP;
    }
    
    @Sided(Side.CLIENT)
    private static int t(final int aInteger) {
        return (aInteger & 0xFF0000) >> 16;
    }
    
    @Sided(Side.CLIENT)
    public float[] dJ() {
        return DyeColor.a(t(this.dI())).d();
    }
    
    @Sided(Side.CLIENT)
    private static int u(final int aInteger) {
        return (aInteger & 0xFF000000) >> 24;
    }
    
    @Sided(Side.CLIENT)
    public float[] dK() {
        return DyeColor.a(u(this.dI())).d();
    }
    
    @Sided(Side.CLIENT)
    public static int s(final int aInteger) {
        return Math.min(aInteger & 0xFF, 1);
    }
    
    @Sided(Side.CLIENT)
    public int dL() {
        return s(this.dI());
    }
    
    @Sided(Side.CLIENT)
    private static int v(final int aInteger) {
        return Math.min((aInteger & 0xFF00) >> 8, 5);
    }
    
    @Sided(Side.CLIENT)
    public Identifier dM() {
        if (s(this.dI()) == 0) {
            return EntityTropicalFish.bC[v(this.dI())];
        }
        return EntityTropicalFish.bD[v(this.dI())];
    }
    
    @Sided(Side.CLIENT)
    public Identifier dN() {
        return EntityTropicalFish.c[s(this.dI())];
    }
    
    @Nullable
    @Override
    public IEntityData a(final IWorld aIWorld1, final LocalDifficulty aLocalDifficulty2, @Nullable IEntityData aIEntityData3, @Nullable final NBTCompound aNBTCompound4) {
        aIEntityData3 = super.a(aIWorld1, aLocalDifficulty2, aIEntityData3, aNBTCompound4);
        if (aNBTCompound4 != null && aNBTCompound4.hasKey("BucketVariantTag", 3)) {
            this.r(aNBTCompound4.getInt("BucketVariantTag"));
            return aIEntityData3;
        }
        int vInteger5;
        int vInteger6;
        int vInteger7;
        int vInteger8;
        if (aIEntityData3 instanceof b) {
            final b vEntityTropicalFishb9 = (b)aIEntityData3;
            vInteger5 = vEntityTropicalFishb9.b;
            vInteger6 = vEntityTropicalFishb9.c;
            vInteger7 = vEntityTropicalFishb9.d;
            vInteger8 = vEntityTropicalFishb9.e;
        }
        else if (this.rand.nextFloat() < 0.9) {
            final int vInteger9 = EntityTropicalFish.a[this.rand.nextInt(EntityTropicalFish.a.length)];
            vInteger5 = (vInteger9 & 0xFF);
            vInteger6 = (vInteger9 & 0xFF00) >> 8;
            vInteger7 = (vInteger9 & 0xFF0000) >> 16;
            vInteger8 = (vInteger9 & 0xFF000000) >> 24;
            aIEntityData3 = new b(this, vInteger5, vInteger6, vInteger7, vInteger8);
        }
        else {
            this.bE = false;
            vInteger5 = this.rand.nextInt(2);
            vInteger6 = this.rand.nextInt(6);
            vInteger7 = this.rand.nextInt(15);
            vInteger8 = this.rand.nextInt(15);
        }
        this.r(vInteger5 | vInteger6 << 8 | vInteger7 << 16 | vInteger8 << 24);
        return aIEntityData3;
    }
    
    static {
        b = DataTracker.<Integer>registerData(EntityTropicalFish.class, TrackedDataHandlerRegistry.INTEGER);
        c = new Identifier[] { new Identifier("textures/entity/fish/tropical_a.png"), new Identifier("textures/entity/fish/tropical_b.png") };
        bC = new Identifier[] { new Identifier("textures/entity/fish/tropical_a_pattern_1.png"), new Identifier("textures/entity/fish/tropical_a_pattern_2.png"), new Identifier("textures/entity/fish/tropical_a_pattern_3.png"), new Identifier("textures/entity/fish/tropical_a_pattern_4.png"), new Identifier("textures/entity/fish/tropical_a_pattern_5.png"), new Identifier("textures/entity/fish/tropical_a_pattern_6.png") };
        bD = new Identifier[] { new Identifier("textures/entity/fish/tropical_b_pattern_1.png"), new Identifier("textures/entity/fish/tropical_b_pattern_2.png"), new Identifier("textures/entity/fish/tropical_b_pattern_3.png"), new Identifier("textures/entity/fish/tropical_b_pattern_4.png"), new Identifier("textures/entity/fish/tropical_b_pattern_5.png"), new Identifier("textures/entity/fish/tropical_b_pattern_6.png") };
        a = new int[] { a(EntityTropicalFish.a.h, DyeColor.ORANGE, DyeColor.GRAY), a(EntityTropicalFish.a.g, DyeColor.GRAY, DyeColor.GRAY), a(EntityTropicalFish.a.g, DyeColor.GRAY, DyeColor.BLUE), a(EntityTropicalFish.a.l, DyeColor.WHITE, DyeColor.GRAY), a(EntityTropicalFish.a.b, DyeColor.BLUE, DyeColor.GRAY), a(EntityTropicalFish.a.a, DyeColor.ORANGE, DyeColor.WHITE), a(EntityTropicalFish.a.f, DyeColor.PINK, DyeColor.LIGHT_BLUE), a(EntityTropicalFish.a.j, DyeColor.PURPLE, DyeColor.YELLOW), a(EntityTropicalFish.a.l, DyeColor.WHITE, DyeColor.RED), a(EntityTropicalFish.a.f, DyeColor.WHITE, DyeColor.YELLOW), a(EntityTropicalFish.a.i, DyeColor.WHITE, DyeColor.GRAY), a(EntityTropicalFish.a.l, DyeColor.WHITE, DyeColor.ORANGE), a(EntityTropicalFish.a.d, DyeColor.CYAN, DyeColor.PINK), a(EntityTropicalFish.a.e, DyeColor.LIME, DyeColor.LIGHT_BLUE), a(EntityTropicalFish.a.k, DyeColor.RED, DyeColor.WHITE), a(EntityTropicalFish.a.c, DyeColor.GRAY, DyeColor.RED), a(EntityTropicalFish.a.j, DyeColor.RED, DyeColor.WHITE), a(EntityTropicalFish.a.g, DyeColor.WHITE, DyeColor.YELLOW), a(EntityTropicalFish.a.a, DyeColor.RED, DyeColor.WHITE), a(EntityTropicalFish.a.b, DyeColor.GRAY, DyeColor.WHITE), a(EntityTropicalFish.a.d, DyeColor.CYAN, DyeColor.YELLOW), a(EntityTropicalFish.a.g, DyeColor.YELLOW, DyeColor.YELLOW) };
    }
    
    enum a
    {
        a(0, 0), 
        b(0, 1), 
        c(0, 2), 
        d(0, 3), 
        e(0, 4), 
        f(0, 5), 
        g(1, 0), 
        h(1, 1), 
        i(1, 2), 
        j(1, 3), 
        k(1, 4), 
        l(1, 5);
        
        private final int m;
        private final int n;
        private static final a[] o;
        
        private a(final int aInteger1, final int aInteger2) {
            this.m = aInteger1;
            this.n = aInteger2;
        }
        
        public int a() {
            return this.m;
        }
        
        public int b() {
            return this.n;
        }
        
        @Sided(Side.CLIENT)
        public static String a(final int aInteger1, final int aInteger2) {
            return a.o[aInteger2 + 6 * aInteger1].c();
        }
        
        @Sided(Side.CLIENT)
        public String c() {
            return this.name().toLowerCase(Locale.ROOT);
        }
        
        static {
            o = values();
        }
    }
    
    static class b extends akg.a
    {
        private final int b;
        private final int c;
        private final int d;
        private final int e;
        
        private b(final EntityTropicalFish aEntityTropicalFish1, final int aInteger2, final int aInteger3, final int aInteger4, final int aInteger5) {
            super(aEntityTropicalFish1);
            this.b = aInteger2;
            this.c = aInteger3;
            this.d = aInteger4;
            this.e = aInteger5;
        }
    }
}
