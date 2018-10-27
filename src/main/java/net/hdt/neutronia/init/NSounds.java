package net.hdt.neutronia.init;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

import static net.hdt.neutronia.base.lib.LibMisc.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class NSounds {

    public static final SoundEvent AMBIENT_UNDERWATER_ENTER;
    public static final SoundEvent AMBIENT_UNDERWATER_EXIT;
    public static final SoundEvent AMBIENT_UNDERWATER_LOOP;
    public static final SoundEvent AMBIENT_UNDERWATER_LOOP_ADDITIONS;
    public static final SoundEvent AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE;
    public static final SoundEvent AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE;
    public static final SoundEvent ARMOR_EQUIP_TURTLE;
    public static final SoundEvent AXE_STRIP;
    public static final SoundEvent BLOCK_BEACON_ACTIVATE;
    public static final SoundEvent BLOCK_BEACON_AMBIENT;
    public static final SoundEvent BLOCK_BEACON_DEACTIVATE;
    public static final SoundEvent BLOCK_BEACON_POWER_SELECT;
    public static final SoundEvent field_203253_U;
    public static final SoundEvent field_203251_S;
    public static final SoundEvent field_203252_T;
    public static final SoundEvent field_203282_jc;
    public static final SoundEvent field_203283_jd;
    public static final SoundEvent field_203819_X;
    public static final SoundEvent field_203814_aa;
    public static final SoundEvent field_203815_ax;
    public static final SoundEvent field_203816_ay;
    public static final SoundEvent field_203818_az;
    public static final SoundEvent field_203813_aA;
    public static final SoundEvent field_206933_aM;
    public static final SoundEvent field_206934_aN;
    public static final SoundEvent field_206935_aO;
    public static final SoundEvent field_206936_aP;
    public static final SoundEvent field_206937_aQ;
    public static final SoundEvent field_205203_aT;
    public static final SoundEvent field_205204_aU;
    public static final SoundEvent field_205205_aV;
    public static final SoundEvent field_205206_aW;
    public static final SoundEvent field_205207_aX;
    public static final SoundEvent field_205208_aY;
    public static final SoundEvent field_205209_aZ;
    public static final SoundEvent field_205210_ba;
    public static final SoundEvent field_205212_bc;
    public static final SoundEvent field_205211_bb;
    public static final SoundEvent ENTITY_DROWNED_AMBIENT;
    public static final SoundEvent ENTITY_DROWNED_AMBIENT_WATER;
    public static final SoundEvent ENTITY_DROWNED_DEATH;
    public static final SoundEvent ENTITY_DROWNED_DEATH_WATER;
    public static final SoundEvent ENTITY_DROWNED_HURT;
    public static final SoundEvent ENTITY_DROWNED_HURT_WATER;
    public static final SoundEvent ENTITY_DROWNED_SHOOT;
    public static final SoundEvent ENTITY_DROWNED_STEP;
    public static final SoundEvent ENTITY_DROWNED_SWIM;
    public static final SoundEvent field_203817_bZ;
    public static final SoundEvent field_211414_dn;
    public static final SoundEvent field_211415_do;
    public static final SoundEvent field_211416_dp;
    public static final SoundEvent field_211417_dq;
    public static final SoundEvent field_211418_dr;
    public static final SoundEvent field_211419_ds;
    public static final SoundEvent field_211420_dt;
    public static final SoundEvent field_211421_du;
    public static final SoundEvent field_211422_dv;
    public static final SoundEvent field_211423_dw;
    public static final SoundEvent field_206942_fI;
    public static final SoundEvent field_206943_fS;
    public static final SoundEvent ENTITY_PHANTOM_AMBIENT;
    public static final SoundEvent ENTITY_PHANTOM_BITE;
    public static final SoundEvent ENTITY_PHANTOM_DEATH;
    public static final SoundEvent ENTITY_PHANTOM_FLAP;
    public static final SoundEvent ENTITY_PHANTOM_HURT;
    public static final SoundEvent ENTITY_PHANTOM_SWOOP;
    public static final SoundEvent field_204328_gh;
    public static final SoundEvent field_203824_gm;
    public static final SoundEvent field_203825_gn;
    public static final SoundEvent field_203826_go;
    public static final SoundEvent field_203827_gp;
    public static final SoundEvent field_203828_gq;
    public static final SoundEvent field_203829_gr;
    public static final SoundEvent field_203830_gs;
    public static final SoundEvent field_199059_fV;
    public static final SoundEvent field_203820_gM;
    public static final SoundEvent field_203821_gN;
    public static final SoundEvent field_203822_gO;
    public static final SoundEvent field_203823_gP;
    public static final SoundEvent field_203268_ij;
    public static final SoundEvent field_203269_ik;
    public static final SoundEvent field_203270_il;
    public static final SoundEvent field_203271_im;
    public static final SoundEvent field_203272_in;
    public static final SoundEvent field_203273_io;
    public static final SoundEvent field_203274_ip;
    public static final SoundEvent field_203275_iq;
    public static final SoundEvent field_204411_iV;
    public static final SoundEvent field_204412_iW;
    public static final SoundEvent field_204413_iX;
    public static final SoundEvent field_204414_iY;
    public static final SoundEvent field_203277_iv;
    public static final SoundEvent field_203263_iC;
    public static final SoundEvent field_203264_iD;
    public static final SoundEvent field_203281_iz;
    public static final SoundEvent field_203280_iy;
    public static final SoundEvent field_203279_ix;
    public static final SoundEvent field_203261_iA;
    public static final SoundEvent field_203262_iB;
    public static final SoundEvent field_203278_iw;
    public static final SoundEvent field_203266_iF;
    public static final SoundEvent field_203267_iG;
    public static final SoundEvent ENTITY_TURTLE_SWIM;
    public static final SoundEvent ENTITY_ZOMBIE_CONVERT_TO_DROWNED;
    public static final SoundEvent ENTITY_ZOMBIE_DESTROY_EGG;
    public static final SoundEvent WELL_WISHER_LAUGH;
    public static final SoundEvent WELL_WISHER_I_CAN_HEAR_YOU_I_CAN_SMELL_YOU;
    public static final SoundEvent WELL_WISHER_CRY_LAUGH;
    public static final SoundEvent WELL_WISHER_I_GOT_YOU;
    private static List<SoundEvent> soundEvents = Lists.newArrayList();

    static {
        AMBIENT_UNDERWATER_ENTER = registerSound("ambient.underwater.enter");
        AMBIENT_UNDERWATER_EXIT = registerSound("ambient.underwater.exit");
        AMBIENT_UNDERWATER_LOOP = registerSound("ambient.underwater.loop");
        AMBIENT_UNDERWATER_LOOP_ADDITIONS = registerSound("ambient.underwater.loop.additions");
        AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE = registerSound("ambient.underwater.loop.additions.rare");
        AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE = registerSound("ambient.underwater.loop.additions.ultra_rare");
        ARMOR_EQUIP_TURTLE = registerSound("item.armor.equip_turtle");
        AXE_STRIP = registerSound("item.axe.strip");
        BLOCK_BEACON_ACTIVATE = registerSound("block.beacon.activate");
        BLOCK_BEACON_AMBIENT = registerSound("block.beacon.ambient");
        BLOCK_BEACON_DEACTIVATE = registerSound("block.beacon.deactivate");
        BLOCK_BEACON_POWER_SELECT = registerSound("block.beacon.power_select");
        field_203253_U = registerSound("block.bubble_column.bubble_pop");
        field_203251_S = registerSound("block.bubble_column.upwards_ambient");
        field_203252_T = registerSound("block.bubble_column.upwards_inside");
        field_203282_jc = registerSound("block.bubble_column.whirlpool_ambient");
        field_203283_jd = registerSound("block.bubble_column.whirlpool_inside");
        field_203819_X = registerSound("item.bucket.empty_fish");
        field_203814_aa = registerSound("item.bucket.fill_fish");
        field_203815_ax = registerSound("entity.cod.ambient");
        field_203816_ay = registerSound("entity.cod.death");
        field_203818_az = registerSound("entity.cod.flop");
        field_203813_aA = registerSound("entity.cod.hurt");
        field_206933_aM = registerSound("block.conduit.activate");
        field_206934_aN = registerSound("block.conduit.ambient");
        field_206935_aO = registerSound("block.conduit.ambient.short");
        field_206936_aP = registerSound("block.conduit.attack.target");
        field_206937_aQ = registerSound("block.conduit.deactivate");
        field_205203_aT = registerSound("entity.dolphin.ambient");
        field_205204_aU = registerSound("entity.dolphin.ambient_water");
        field_205205_aV = registerSound("entity.dolphin.attack");
        field_205206_aW = registerSound("entity.dolphin.death");
        field_205207_aX = registerSound("entity.dolphin.eat");
        field_205208_aY = registerSound("entity.dolphin.hurt");
        field_205209_aZ = registerSound("entity.dolphin.jump");
        field_205210_ba = registerSound("entity.dolphin.play");
        field_205212_bc = registerSound("entity.dolphin.splash");
        field_205211_bb = registerSound("entity.dolphin.swim");
        ENTITY_DROWNED_AMBIENT = registerSound("entity.drowned.ambient");
        ENTITY_DROWNED_AMBIENT_WATER = registerSound("entity.drowned.ambient_water");
        ENTITY_DROWNED_DEATH = registerSound("entity.drowned.death");
        ENTITY_DROWNED_DEATH_WATER = registerSound("entity.drowned.death_water");
        ENTITY_DROWNED_HURT = registerSound("entity.drowned.hurt");
        ENTITY_DROWNED_HURT_WATER = registerSound("entity.drowned.hurt_water");
        ENTITY_DROWNED_SHOOT = registerSound("entity.drowned.shoot");
        ENTITY_DROWNED_STEP = registerSound("entity.drowned.step");
        ENTITY_DROWNED_SWIM = registerSound("entity.drowned.swim");
        field_203817_bZ = registerSound("entity.fish.swim");
        field_211414_dn = registerSound("block.wet_grass.break");
        field_211415_do = registerSound("block.wet_grass.fall");
        field_211416_dp = registerSound("block.wet_grass.hit");
        field_211417_dq = registerSound("block.wet_grass.place");
        field_211418_dr = registerSound("block.wet_grass.step");
        field_211419_ds = registerSound("block.coral_block.break");
        field_211420_dt = registerSound("block.coral_block.fall");
        field_211421_du = registerSound("block.coral_block.hit");
        field_211422_dv = registerSound("block.coral_block.place");
        field_211423_dw = registerSound("block.coral_block.step");
        field_206942_fI = registerSound("entity.parrot.imitate.drowned");
        field_206943_fS = registerSound("entity.parrot.imitate.phantom");
        ENTITY_PHANTOM_AMBIENT = registerSound("entity.phantom.ambient");
        ENTITY_PHANTOM_BITE = registerSound("entity.phantom.bite");
        ENTITY_PHANTOM_DEATH = registerSound("entity.phantom.death");
        ENTITY_PHANTOM_FLAP = registerSound("entity.phantom.flap");
        ENTITY_PHANTOM_HURT = registerSound("entity.phantom.hurt");
        ENTITY_PHANTOM_SWOOP = registerSound("entity.phantom.swoop");
        field_204328_gh = registerSound("entity.player.splash.high_speed");
        field_203824_gm = registerSound("entity.puffer_fish.ambient");
        field_203825_gn = registerSound("entity.puffer_fish.blow_out");
        field_203826_go = registerSound("entity.puffer_fish.blow_up");
        field_203827_gp = registerSound("entity.puffer_fish.death");
        field_203828_gq = registerSound("entity.puffer_fish.flop");
        field_203829_gr = registerSound("entity.puffer_fish.hurt");
        field_203830_gs = registerSound("entity.puffer_fish.sting");
        field_199059_fV = registerSound("block.pumpkin.carve");
        field_203820_gM = registerSound("entity.salmon.ambient");
        field_203821_gN = registerSound("entity.salmon.death");
        field_203822_gO = registerSound("entity.salmon.flop");
        field_203823_gP = registerSound("entity.salmon.hurt");
        field_203268_ij = registerSound("item.trident.hit");
        field_203269_ik = registerSound("item.trident.hit_ground");
        field_203270_il = registerSound("item.trident.return");
        field_203271_im = registerSound("item.trident.riptide_1");
        field_203272_in = registerSound("item.trident.riptide_2");
        field_203273_io = registerSound("item.trident.riptide_3");
        field_203274_ip = registerSound("item.trident.throw");
        field_203275_iq = registerSound("item.trident.thunder");
        field_204411_iV = registerSound("entity.tropical_fish.ambient");
        field_204412_iW = registerSound("entity.tropical_fish.death");
        field_204413_iX = registerSound("entity.tropical_fish.flop");
        field_204414_iY = registerSound("entity.tropical_fish.hurt");
        field_203277_iv = registerSound("entity.turtle.ambient_land");
        field_203263_iC = registerSound("entity.turtle.death");
        field_203264_iD = registerSound("entity.turtle.death_baby");
        field_203281_iz = registerSound("entity.turtle.egg_break");
        field_203280_iy = registerSound("entity.turtle.egg_crack");
        field_203279_ix = registerSound("entity.turtle.egg_hatch");
        field_203261_iA = registerSound("entity.turtle.hurt");
        field_203262_iB = registerSound("entity.turtle.hurt_baby");
        field_203278_iw = registerSound("entity.turtle.lay_egg");
        field_203266_iF = registerSound("entity.turtle.shamble");
        field_203267_iG = registerSound("entity.turtle.shamble_baby");
        ENTITY_TURTLE_SWIM = registerSound("entity.turtle.swim");
        ENTITY_ZOMBIE_CONVERT_TO_DROWNED = registerSound("entity.zombie.converted_to_drowned");
        ENTITY_ZOMBIE_DESTROY_EGG = registerSound("entity.zombie.destroy_egg");
        WELL_WISHER_LAUGH = registerSound("entity.well_wisher.laugh");
        WELL_WISHER_I_CAN_HEAR_YOU_I_CAN_SMELL_YOU = registerSound("entity.well_wisher.i_can_hear_you_i_can_smell_you");
        WELL_WISHER_CRY_LAUGH = registerSound("entity.well_wisher.dry_laugh");
        WELL_WISHER_I_GOT_YOU = registerSound("entity.well_wisher.i_got_you");
    }

    private static SoundEvent registerSound(String name) {
        SoundEvent soundEvent = new SoundEvent(new ResourceLocation(MOD_ID, name)).setRegistryName(new ResourceLocation(MOD_ID, name));
        soundEvents.add(soundEvent);
        return soundEvent;
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (SoundEvent soundEvent : soundEvents) event.getRegistry().register(soundEvent);
    }

}