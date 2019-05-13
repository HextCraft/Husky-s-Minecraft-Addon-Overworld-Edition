package team.abnormal.neutronia.base.utils.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ResourceLocation;
import team.abnormal.neutronia.base.Reference;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaPillar;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaSlab;
import team.abnormal.neutronia.base.blocks.BlockNeutroniaStairs;
import team.abnormal.neutronia.blocks.BlockCustomChest;
import team.abnormal.neutronia.blocks.BlockStrippedLog;
import team.hollow.abnormalib.blocks.BaseModBlock;
import team.hollow.abnormalib.blocks.CampfireBaseBlock;
import team.hollow.abnormalib.blocks.CustomLadderBlock;
import team.hollow.abnormalib.blocks.*;
import team.hollow.abnormalib.utils.registry.RegistryUtils;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.blocks.entity.ChestBaseBlockEntity;
import team.hollow.neutronia.client.entity.render.model.BaseChestModel;
import team.hollow.neutronia.client.entity.render.model.chests.ModelLargeSpruceChest;
import team.hollow.neutronia.client.entity.render.model.chests.ModelSpruceChest;
import team.hollow.neutronia.client.renderer.ChestBaseBlockEntityRenderer;

import java.util.HashMap;
import java.util.Map;

public class WoodRegistry {

    public ResourceLocation name;
    private Block log;
    private Block wood;
    private Block strippedLog;
    private Block strippedWood;
    private Block stairs;
    private Block slab;
    private Block planks;
    private Block patternedPlanks;
    private Block carvedPlanks;
    private Block leaves;
    private Block chest;
    private Block sapling;
    private Block fence;
    private Block fenceGate;
    private Block lectern;
    private Block paperLantern;
    private Block logCampfire;
    private Block strippedLogCampfire;
    private Block barrel;
    private Block bookshelf;
    private Block door;
    private Block trapdoor;
    private Block button;
    private Block pressurePlate;
    private Block ladder;
    private Block corner;
    private Block post;
    private Block siding;
    private SaplingGenerator saplingGenerator;

    private WoodRegistry(ResourceLocation name, SaplingGenerator saplingGenerator) {
        this.name = name;
        this.saplingGenerator = saplingGenerator;
    }

    private WoodRegistry(ResourceLocation name) {
        this.name = name;
        this.saplingGenerator = null;
    }

    public static WoodRegistry getInstance(ResourceLocation name, SaplingGenerator saplingGenerator) {
        return new WoodRegistry(name, saplingGenerator);
    }

    public Block getLog() {
        return log;
    }

    public Block getWood() {
        return wood;
    }

    public Block getStrippedLog() {
        return strippedLog;
    }

    public Block getStrippedWood() {
        return strippedWood;
    }

    public Block getStairs() {
        return stairs;
    }

    public Block getSlab() {
        return slab;
    }

    public Block getPlanks() {
        return planks;
    }

    public Block getPatternedPlanks() {
        return patternedPlanks;
    }

    public Block getCarvedPlanks() {
        return carvedPlanks;
    }

    public Block getLeaves() {
        return leaves;
    }

    public Block getChest() {
        return chest;
    }

    public Block getSapling() {
        return sapling;
    }

    public Block getFence() {
        return fence;
    }

    public Block getFenceGate() {
        return fenceGate;
    }

    public Block getLectern() {
        return lectern;
    }

    public Block getPaperLantern() {
        return paperLantern;
    }

    public Block getLogCampfire() {
        return logCampfire;
    }

    public Block getStrippedLogCampfire() {
        return strippedLogCampfire;
    }

    public Block getBarrel() {
        return barrel;
    }

    public Block getBookshelf() {
        return bookshelf;
    }

    public Block getDoor() {
        return door;
    }

    public Block getTrapdoor() {
        return trapdoor;
    }

    public Block getButton() {
        return button;
    }

    public Block getPressurePlate() {
        return pressurePlate;
    }

    public Block getLadder() {
        return ladder;
    }

    public Block getCorner() {
        return corner;
    }

    public Block getPost() {
        return post;
    }

    public Block getSiding() {
        return siding;
    }

    public static class Builder {

        public ResourceLocation name;
        private WoodRegistry woodRegistry;
        private CreativeTabs itemGroup;

        public Builder(ResourceLocation name) {
            this.name = name;
            woodRegistry = new WoodRegistry(name);
            this.itemGroup = new CreativeTabs(String.format("%s_blocks", name.getPath())) {
                @Override
                public ItemStack createIcon() {
                    return new ItemStack(woodRegistry.planks);
                }
            };
        }

        public Builder(ResourceLocation name, Block planks) {
            this.name = name;
            woodRegistry = new WoodRegistry(name);
            woodRegistry.planks = planks;
            this.itemGroup = new CreativeTabs(String.format("%s_blocks", name.getPath())) {
                @Override
                public ItemStack createIcon() {
                    return new ItemStack(planks);
                }
            };
        }

        public Builder(ResourceLocation name, Block planks, CreativeTabs itemGroup) {
            this.name = name;
            woodRegistry = new WoodRegistry(name);
            woodRegistry.planks = planks;
            this.itemGroup = itemGroup;
        }

        public Builder(ResourceLocation name, SaplingGenerator saplingGenerator) {
            this.name = name;
            woodRegistry = new WoodRegistry(name, saplingGenerator);
            this.itemGroup = CreativeTabs.BUILDING_BLOCKS;
        }

        public Builder log() {
            woodRegistry.log = RegistryUtils.register(new BlockNeutroniaPillar(woodRegistry.planks.getMaterial(woodRegistry.planks.getDefaultState())),
                    new ResourceLocation(name.getNamespace(), name.getPath() + "_log"), itemGroup);
            return this;
        }

        public Builder wood() {
            woodRegistry.wood = RegistryUtils.register(new Block(woodRegistry.planks.getMaterial(woodRegistry.planks.getDefaultState())),
                    new ResourceLocation(name.getNamespace(), name.getPath() + "_wood"), itemGroup);
            return this;
        }

        public Builder strippedLog() {
            woodRegistry.strippedLog = RegistryUtils.register(new BlockStrippedLog(woodRegistry.planks.getDefaultState()), new ResourceLocation(name.getNamespace(),
                    "stripped_" + name.getPath() + "_log"), itemGroup);
            return this;
        }

        public Builder strippedWood() {
            woodRegistry.strippedWood = RegistryUtils.register(new Block(woodRegistry.planks.getMaterial(woodRegistry.planks.getDefaultState())),
                    new ResourceLocation(name.getNamespace(), "stripped_" + name.getPath() + "_wood"), itemGroup);
            return this;
        }

        public Builder stairs() {
            woodRegistry.stairs = RegistryUtils.register(new BlockNeutroniaStairs(woodRegistry.planks.getDefaultState()), new ResourceLocation(name.getNamespace(),
                            name.getPath() + "_stairs"), itemGroup);
            return this;
        }

        public Builder slab() {
            woodRegistry.slab = RegistryUtils.register(new BlockNeutroniaSlab.Half(), new ResourceLocation(name.getNamespace(), name.getPath() + "_slab"),
                    itemGroup);
            woodRegistry.slab = RegistryUtils.registerNoBI(new BlockNeutroniaSlab.Double(), new ResourceLocation(name.getNamespace(), name.getPath() + "_slab"));
            return this;
        }

        public Builder planks() {
            woodRegistry.planks = RegistryUtils.register(new Block(woodRegistry.planks.getMaterial(woodRegistry.planks.getDefaultState())),
                    new ResourceLocation(name.getNamespace(), name.getPath() + "_planks"), itemGroup);
            return this;
        }

        public Builder patternedPlanks() {
            woodRegistry.patternedPlanks = RegistryUtils.register(new Block(woodRegistry.planks.getMaterial(woodRegistry.planks.getDefaultState())),
                    new ResourceLocation(name.getNamespace(), "patterned_" + name.getPath() + "_planks"), itemGroup);
            return this;
        }

        public Builder carvedPlanks() {
            woodRegistry.carvedPlanks = RegistryUtils.register(new Block(woodRegistry.planks.getMaterial(woodRegistry.planks.getDefaultState())),
                    new ResourceLocation(name.getNamespace(), "carved_" + name.getPath() + "_planks"), itemGroup);
            return this;
        }

        public Builder leaves() {
            woodRegistry.leaves = RegistryUtils.register(new LeavesBaseBlock(), new ResourceLocation(name.getNamespace(), name.getPath() + "_leaves"), itemGroup);
            return this;
        }

        public Builder coloredLeaves() {
            woodRegistry.leaves = RegistryUtils.register(new LeavesBaseBlock(), new ResourceLocation(name.getNamespace(), name.getPath() + "_leaves"), itemGroup);
//            NeutroniaLeavesColors.COLORED_LEAVES_LIST.add(woodRegistry.leaves);
            return this;
        }

        public Builder chest() {
            BlockCustomChest chestBaseBlock = new BlockCustomChest();
            woodRegistry.chest = RegistryUtils.register(chestBaseBlock, new ResourceLocation(name.getNamespace(), name.getPath() + "_chest"), itemGroup);
            chestBaseBlock.setChestTexture(new ResourceLocation(name.getNamespace(), "textures/entity/chest/" + name.getPath() + ".png"));
            chestBaseBlock.setDoubleChestTexture(new ResourceLocation(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_double.png"));
            chestBaseBlock.setTrappedChestTexture(new ResourceLocation(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_trapped.png"));
            chestBaseBlock.setTrappedDoubleChestTexture(new ResourceLocation(name.getNamespace(), "textures/entity/chest/" + name.getPath() + "_trapped_double.png"));
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                BlockEntityRendererRegistry.INSTANCE.register(ChestBaseBlockEntity.class, new ChestBaseBlockEntityRenderer());
            }
            return this;
        }

        public Builder sapling() {
            woodRegistry.sapling = RegistryUtils.register(new BlockSapling(woodRegistry.saplingGenerator), new ResourceLocation(name.getNamespace(), name.getPath() + "_sapling"), itemGroup);
            return this;
        }

        public Builder waterloggedSapling() {
            woodRegistry.sapling = RegistryUtils.register(new NeutroniaWaterloggedSaplingBlock(woodRegistry.saplingGenerator), new ResourceLocation(name.getNamespace(), name.getPath() +
                    "_underwater_sapling"), itemGroup);
            return this;
        }

        public Builder fence() {
            woodRegistry.fence = RegistryUtils.register(new FenceBaseBlock(woodRegistry.planks.getDefaultState()), new ResourceLocation(name.getNamespace(), name.getPath() + "_fence"), itemGroup);
            return this;
        }

        public Builder fenceGate() {
            woodRegistry.fenceGate = RegistryUtils.register(new FenceGateBaseBlock(), new ResourceLocation(name.getNamespace(), name.getPath() + "_fence_gate"), itemGroup);
            return this;
        }

        public Builder lectern() {
            woodRegistry.lectern = RegistryUtils.register(new LecternBaseBlock(), new ResourceLocation(name.getNamespace(), name.getPath() + "_lectern"), itemGroup);
            return this;
        }

        public Builder paperLantern() {
            woodRegistry.paperLantern = RegistryUtils.register(new BaseModBlock(Block.Settings.copy(woodRegistry.planks)), new ResourceLocation(name.getNamespace(), name.getPath() + "_paper_lantern"),
                    itemGroup);
            return this;
        }

        public Builder logCampfire() {
            woodRegistry.logCampfire = RegistryUtils.register(new CampfireBaseBlock(), new ResourceLocation(name.getNamespace(), name.getPath() + "_campfire"), itemGroup);
            return this;
        }

        public Builder strippedLogCampfire() {
            woodRegistry.strippedLogCampfire = RegistryUtils.register(new CampfireBaseBlock(), new ResourceLocation(name.getNamespace(), "stripped_" + name.getPath() +
                    "_campfire"), itemGroup);
            return this;
        }

        public Builder barrel() {
            woodRegistry.barrel = RegistryUtils.register(new BarrelBlock(Block.Settings.copy(woodRegistry.planks)), new ResourceLocation(name.getNamespace(), name.getPath() + "_barrel"), itemGroup);
            return this;
        }

        public Builder bookshelf() {
            woodRegistry.bookshelf = RegistryUtils.register(new BaseModBlock(Block.Settings.copy(woodRegistry.planks)), new ResourceLocation(name.getNamespace(), name.getPath() + "_bookshelf"), itemGroup);
            return this;
        }

        public Builder door() {
            woodRegistry.door = RegistryUtils.register(new DoorBaseBlock(Material.WOOD), new ResourceLocation(name.getNamespace(), name.getPath() + "_door"), itemGroup);
            return this;
        }

        public Builder trapdoor() {
            woodRegistry.trapdoor = RegistryUtils.register(new TrapdoorBaseBlock(Material.WOOD), new ResourceLocation(name.getNamespace(), name.getPath() + "_trapdoor"),
                    itemGroup);
            return this;
        }

        public Builder button() {
            woodRegistry.button = RegistryUtils.register(new ButtonBaseBlock(true), new ResourceLocation(name.getNamespace(), name.getPath() + "_button"), itemGroup);
            return this;
        }

        public Builder pressurePlate(PressurePlateBlock.ActivationRule type) {
            woodRegistry.pressurePlate = RegistryUtils.register(new PressurePlateBaseBlock(Material.WOOD, type), new ResourceLocation(name.getNamespace(),
                    name.getPath() + "_pressure_plate"), itemGroup);
            return this;
        }

        public Builder ladder() {
            woodRegistry.ladder = RegistryUtils.register(new CustomLadderBlock(), new ResourceLocation(name.getNamespace(),
                    name.getPath() + "_ladder"), itemGroup);
            return this;
        }

        public Builder corner() {
            woodRegistry.corner = RegistryUtils.register(new NeutroniaCornerBlock(woodRegistry.planks.getDefaultState(), Block.Settings.copy(woodRegistry.planks)),
                    new ResourceLocation(name.getNamespace(), name.getPath() + "_corner"), itemGroup);
            return this;
        }

        public Builder post() {
            woodRegistry.post =  RegistryUtils.register(new NeutroniaPostBlock(Block.Settings.copy(woodRegistry.planks)), new ResourceLocation(name.getNamespace(), name.getPath() + "_post"), itemGroup);
            return this;
        }

        public Builder siding() {
            woodRegistry.siding = RegistryUtils.register(new NeutroniaSidingBlock(Block.Settings.copy(woodRegistry.planks)), new ResourceLocation(name.getNamespace(), name.getPath() + "_siding"), itemGroup);
            return this;
        }

        public WoodRegistry build() {
            return woodRegistry;
        }

    }

}