package net.hdt.neutronia.groups.world.features.overworld;

import com.google.common.collect.Sets;
import net.hdt.huskylib2.event.StructureSetBlockEvent;
import net.hdt.neutronia.base.groups.Component;
import net.hdt.neutronia.base.registry.block.recipe.BlockIngredient;
import net.hdt.neutronia.base.registry.block.recipe.BlockIngredientSpecial;
import net.hdt.neutronia.base.registry.block.recipe.BlockMaterialIngredient;
import net.hdt.neutronia.base.registry.block.recipe.StateIngredient;
import net.hdt.neutronia.groups.world.features.overworld.changers.BiomeIngredientChanger;
import net.hdt.neutronia.groups.world.features.overworld.changers.IngredientChanger;
import net.hdt.neutronia.groups.world.features.overworld.changers.StructureChanger;
import net.hdt.neutronia.groups.world.features.overworld.changers.TableChanger;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

/**
 * Created by primetoxinz on 5/21/17.
 */
public class HCVillages extends Component {

    public static boolean disableAllComplexBlocks;
    public static boolean disableVillagerSpawning;
    public static boolean disableIronGolems;

    private int normalRadius, semiabandonedRadius;

    @Override
    public String getFeatureDescription() {
        return "Makes it so villages with in the reaches of the spawn zone are abandoned and gradually gain more resources the further out. What this means to be gained by the player.";
    }

    private Set<StructureChanger> VILLAGE = Sets.newHashSet();
    private StructureChanger ABANDONED, SEMIABANDONED, NORMAL;

    @Override
    public void setupConfig() {
        semiabandonedRadius = loadPropInt("Semi-Abandoned Village Radius", "Block radius from 0,0 at which villages are now semi-abandoned, all villages inside this radius are abandoned", 2000);
        normalRadius = loadPropInt("Normal Village Radius", "Block radius from 0,0 at which villages are now normal, all villages in between this and semi-abandoned are semi-abandoned", 3000);
        disableAllComplexBlocks = loadPropBool("Disable All Complex Blocks", "Removes any and all useful blocks from villages, including ladders, stairs, tables and more", false);
        disableVillagerSpawning = loadPropBool("Replace Villager Spawning with Nitwits", "Replaces all villager spawns with Nitwits, which have no trades", true);
        disableIronGolems = loadPropBool("Disable Village Iron Golem Spawns", "WARNING: Stops all non-player created Iron Golem Spawns", true);

        ABANDONED = StructureChanger.create(VILLAGE, (w, p) -> p.distanceSq(w.getSpawnPoint()) < semiabandonedRadius * semiabandonedRadius);
        SEMIABANDONED = StructureChanger.create(VILLAGE, (w, p) -> p.distanceSq(w.getSpawnPoint()) < normalRadius * normalRadius);
        NORMAL = StructureChanger.create(VILLAGE, (w, p) -> p.distanceSq(w.getSpawnPoint()) >= normalRadius * normalRadius);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        TableChanger tableChanger = new TableChanger();

        ABANDONED
                .addChanger(tableChanger)
                .addChanger(new BiomeIngredientChanger(new BlockIngredient(new ItemStack(Blocks.WOOL, 1, EnumDyeColor.BLACK.getMetadata())), Blocks.PLANKS.getDefaultState()))
                .addChanger(new IngredientChanger(new StateIngredient(Blocks.CRAFTING_TABLE), Blocks.AIR.getDefaultState()))
                .addChanger(new IngredientChanger(new StateIngredient(Blocks.BOOKSHELF), Blocks.AIR.getDefaultState()))
                .addChanger(new IngredientChanger(new StateIngredient(Blocks.TORCH), Blocks.AIR.getDefaultState()))
                .addChanger(new IngredientChanger(new BlockMaterialIngredient(Material.GLASS), Blocks.AIR.getDefaultState()))
                .addChanger(new IngredientChanger(new BlockMaterialIngredient(Material.PLANTS), Blocks.AIR.getDefaultState()))
                .addChanger(new IngredientChanger(new BlockMaterialIngredient(Material.WATER), Blocks.DIRT.getDefaultState()))
                .addChanger(new IngredientChanger(new BlockIngredientSpecial((world, pos) -> world.getBlockState(pos).getBlock() instanceof BlockDoor), Blocks.AIR.getDefaultState()));

        SEMIABANDONED = NORMAL = ABANDONED;
//                .addChanger(tableChanger)
//                .addChanger(new IngredientChanger(new BlockMaterialIngredient(Material.WATER), Blocks.AIR.getDefaultState()))
//                .addChanger(new IngredientChanger(new BlockMaterialIngredient(Material.GLASS), Blocks.AIR.getDefaultState()));

        NORMAL.addChanger(tableChanger);

    }

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    @SubscribeEvent
    public void biomeSpecificVillage(BiomeEvent.GetVillageBlockID event) {

    }

    @SubscribeEvent
    public void onStructureSetBlock(StructureSetBlockEvent event) {
        if (event.getComponent() instanceof StructureVillagePieces.Village) {
//            System.out.printf("/tp %s ~ %s\n", event.getPos().getX(), event.getPos().getZ());
            StructureChanger.convert(VILLAGE, event);
        }
    }

    //hack to stop iron golem spawning in villages, also will stop any other spawning
    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent event) {
        if (!disableIronGolems)
            return;
        if (event.getEntity() instanceof EntityIronGolem) {
            EntityIronGolem golem = (EntityIronGolem) event.getEntity();
            if (!golem.isPlayerCreated()) {
                event.setCanceled(true);
            }
        }
    }

    @Override
    public boolean hasTerrainSubscriptions() {
        return true;
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }
}

