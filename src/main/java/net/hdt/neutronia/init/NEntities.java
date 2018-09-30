package net.hdt.neutronia.init;

import net.hdt.neutronia.entity.*;
import net.hdt.neutronia.entity.render.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

import static net.hdt.neutronia.base.util.Reference.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class NEntities {

    private static int entityID = 0;

    @SubscribeEvent
    public static void entityRegistering(RegistryEvent.Register<EntityEntry> event) {
        final EntityEntry[] entries = {
                createBuilder("mummy").entity(EntityMummy.class).tracker(80, 3, true).egg(0xC9CE92, 0x444444).build(),
                createBuilder("mummy_villager").entity(EntityMummyVillager.class).tracker(80, 3, true).egg(0xC9CE92, 0x442f00).build(),
                createBuilder("scorpion").entity(EntityScorp.class).tracker(30, 3, true).egg(0x65401, 0x6201209).build(),
//                createBuilder("phantom").entity(EntityPhantom.class).tracker(80, 3, true).egg(0x2d3f56, 0x958c79).build(),
//                createBuilder("red_phantom").entity(EntityBloodPhantom.class).tracker(80, 3, true).egg(0x4A2929, 0x799591).build(),mine
//                createBuilder("ender_phantom").entity(EntityEnderPhantom.class).tracker(80, 3, true).egg(0x352D56, 0x8C9579).build(),
//                createBuilder("shadow_phantom").entity(EntityShadowPhantom.class).tracker(80, 3, true).egg(0x101010, 0x101010).build(),
                createBuilder("inferno").entity(EntityInferno.class).tracker(80, 3, true).egg(0x211114, 0xd17800).build(),
                createBuilder("drowned").entity(EntityDrowned.class).tracker(80, 3, true).egg(0x86e2ca, 0x617d51).build(),
                createBuilder("barnacle").entity(EntityBarnacle.class).tracker(80, 3, true).egg(0x86e2ca, 0x617d51).build(),
                createBuilder("the_devourer").entity(EntityGreatHunger.class).tracker(80, 3, true).egg(0x86e2ca, 0x617d51).build(),
                createBuilder("drowned_scuba_villager").entity(EntityDrownedScubaVillager.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("scuba_villager").entity(EntityScubaVillager.class).tracker(80, 3, true).egg(0x000000, 0xC3B99D).build(),
                createBuilder("aquatic_sanddiver").entity(EntitySandDiverAquatic.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("albadon").entity(EntityAlbadon.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("axolotl").entity(EntityAxolotl.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("bird").entity(EntityBird.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("fox").entity(EntityFox.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("arctic_wolf").entity(EntityArcticWolf.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("mushroom_shroom_glutton").entity(EntityMushroomShroomGlutton.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("well_wisher").entity(EntityWellWisher.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("ol_diggy").entity(EntityOlDiggy.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
                createBuilder("sea_swallowed_captain").entity(EntitySeaSwallowedCaptain.class).tracker(80, 3, true).egg(0x000000, 0x44E4FF).build(),
//                createBuilder("anchored").entity(EntityAnchored.class).tracker(80, 3, true).egg(0x13271d, 0x88baad).build(),
                createBuilder("forsaken_diver").entity(EntityForsakenDiver.class).tracker(80, 3, true).egg(0x13271d, 0x88baad).build(),
                createBuilder("lost_miner").entity(EntityLostMiner.class).tracker(80, 3, true).egg(0x13271d, 0x88baad).build(),
                createBuilder("pharaoh_golem").entity(EntityPharaohGolem.class).tracker(80, 3, true).egg(0x13271d, 0x88baad).build(),
                createBuilder("yeti_golem").entity(EntityYetiGolem.class).tracker(80, 3, true).egg(0x13271d, 0x88baad).build(),
//                createBuilder("firefly").entity(EntityFirefly.class).tracker(64, 20, true).egg(0, 0).build(),
                createBuilder("steampunk_golem").entity(EntitySteampunkGolem.class).tracker(80, 3, true).egg(0x13271d, 0x88baad).build()
        };
        event.getRegistry().registerAll(entries);
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            registerEntityRenders();
        }
        addSpawns();

        /*TODO: Add Great White Shark, Hammerhead Shark, Piranha, Angler Fish, Dragon Shark, Hare, Rabbit, Gecko, Desert Gecko,
                 Clay Golem, Moss Golem, Manta ray, Octopus, Seablob, Necromancer(illager type), Pillagers, Pillager Beast, New Villagers */
    }

    private static void addSpawns() {
        EntityRegistry.addSpawn(EntityMummy.class, 10, 1, 3, EnumCreatureType.MONSTER, Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.MUTATED_DESERT, Biomes.BEACH);
        EntityRegistry.addSpawn(EntityDrowned.class, 14, 2, 4, EnumCreatureType.MONSTER, getBiomes(BiomeDictionary.Type.OCEAN));
        EntityRegistry.addSpawn(EntityAnchored.class, 5, 1, 3, EnumCreatureType.MONSTER, getBiomes(BiomeDictionary.Type.OCEAN));
        EntityRegistry.addSpawn(EntityForsakenDiver.class, 60, 1, 2, EnumCreatureType.MONSTER, getBiomes(BiomeDictionary.Type.OCEAN));
        EntityRegistry.addSpawn(EntityInferno.class, 9, 2, 8, EnumCreatureType.MONSTER, getBiomes(BiomeDictionary.Type.NETHER));
        EntityRegistry.addSpawn(EntityScubaVillager.class, 10, 1, 3, EnumCreatureType.AMBIENT, Biomes.BEACH, Biomes.STONE_BEACH);
    }

    private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
        final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
        final ResourceLocation registryName = new ResourceLocation(MOD_ID, name);
        return builder.id(registryName, entityID++).name(registryName.toString());
    }

    private static Biome[] getBiomes(final BiomeDictionary.Type type) {
        return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
    }

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, RenderMummy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMummyVillager.class, RenderMummyVillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDrowned.class, RenderDrowned::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, RenderPhantom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodPhantom.class, RenderRedPhantom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderPhantom.class, RenderEnderPhantom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShadowPhantom.class, RenderShadowPhantom::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInferno.class, RenderInferno::new);
//        RenderingRegistry.registerEntityRenderingHandler(EntityAnchored.class, RenderAnchored::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLostMiner.class, RenderLostMiner::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityYetiGolem.class, RenderYetiGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySteampunkGolem.class, RenderSteampunkGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPharaohGolem.class, RenderPharaoGolem::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityForsakenDiver.class, RenderForsakenDiver::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityScubaVillager.class, RenderScubaVillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDrownedScubaVillager.class, RenderDrownedScubaVillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySandDiverAquatic.class, RenderSandDiverAquatic::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAlbadon.class, RenderAlbadon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAxolotl.class, RenderAxolotl::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityArcticWolf.class, RenderArcticWolf::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, RenderFox::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBird.class, RenderBird::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMushroomShroomGlutton.class, RenderMushroomShroomGlutton::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySeaSwallowedCaptain.class, RenderSeaSwallowedCaptain::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityOlDiggy.class, RenderOlDiggy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityWellWisher.class, RenderWellWisher::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityScorp.class, RenderScorp::new);
    }

}
