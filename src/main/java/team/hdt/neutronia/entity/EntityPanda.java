/*
package net.hdt.neutronia.entity;

import LibMisc;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.Comparator;

public class EntityPanda extends EntityWaterMob {

    private static final DataParameter<Integer> bD;
    private static final DataParameter<Integer> bE;
    private static final DataParameter<Integer> bG;
    private static final DataParameter<Byte> bH;
    private static final DataParameter<Byte> bI;
    private static final DataParameter<Byte> bJ;

    public EntityPanda(World worldIn) {
        super(worldIn);
        setSize(0.5F, 0.5F);
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setString("MainGene");
        compound.setString("HiddenGene");
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        compound.getString("MainGene");
        compound.getString("HiddenGene");
    }

    static {
        bD = EntityDataManager.createKey(EntityPanda.class, DataSerializers.);
        bE = DataTracker.<Integer>registerData(EntityPanda.class, TrackedDataHandlerRegistry.INTEGER);
        bG = DataTracker.<Integer>registerData(EntityPanda.class, TrackedDataHandlerRegistry.INTEGER);
        bH = DataTracker.<Byte>registerData(EntityPanda.class, TrackedDataHandlerRegistry.BYTE);
        bI = DataTracker.<Byte>registerData(EntityPanda.class, TrackedDataHandlerRegistry.BYTE);
        bJ = DataTracker.<Byte>registerData(EntityPanda.class, TrackedDataHandlerRegistry.BYTE);
    }

    public enum EnumPandaType {

        NORMAL(0, "normal", false, "textures/entity/panda/panda.png"),
        LAZY(1, "lazy", false, "textures/entity/panda/lazy_panda.png"),
        WORRIED(2, "worried", false, "textures/entity/panda/worried_panda.png"),
        PLAYFUL(3, "playful", false, "textures/entity/panda/playful_panda.png"),
        BROWN(4, "brown", true, "textures/entity/panda/brown_panda.png"),
        WEAK(5, "weak", true, "textures/entity/panda/weak_panda.png"),
        AGGRESSIVE(6, "aggressive", false, "textures/entity/panda/aggressive_panda.png");

        private static final EnumPandaType[] h;
        private final int i;
        private final String j;
        private final boolean k;
        private final ResourceLocation l;

        private EnumPandaType(final int aInteger1, final String aString2, final boolean aBoolean3, final String aString4) {
            this.i = aInteger1;
            this.j = aString2;
            this.k = aBoolean3;
            this.l = new ResourceLocation(LibMisc.MOD_ID, aString4);
        }

        public int a() {
            return this.i;
        }

        public String b() {
            return this.j;
        }

        public boolean c() {
            return this.k;
        }

        private static EnumPandaType b(final EntityPanda aEntityPandac1, final EntityPanda aEntityPandac2) {
            if (!aEntityPandac1.v()) {
                return aEntityPandac1;
            }
            if (aEntityPandac1 == aEntityPandac2) {
                return aEntityPandac1;
            }
            return c.a;
        }

        @SideOnly(Side.CLIENT)
        public ResourceLocation d() {
            return this.l;
        }

        public static EnumPandaType a(int aInteger) {
            if (aInteger < 0 || aInteger >= c.h.length) {
                aInteger = 0;
            }
            return c.h[aInteger];
        }

        public static EnumPandaType a(final String aString) {
            for (final EnumPandaType vEntityPandac5 : values()) {
                if (vEntityPandac5.j.equals(aString)) {
                    return vEntityPandac5;
                }
            }
            return EnumPandaType.a;
        }

        static {
            h = Arrays.stream(values()).sorted(Comparator.comparingInt(EnumPandaType::a)).<EnumPandaType>toArray(EnumPandaType[]::new);
        }
    }

}*/
