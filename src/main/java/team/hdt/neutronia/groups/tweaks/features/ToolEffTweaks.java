package team.hdt.neutronia.groups.tweaks.features;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import team.hdt.neutronia.base.groups.Component;

public class ToolEffTweaks extends Component {

    public boolean enabled;

    @Override
    public void setupConfig() {
        enabled = loadPropBool("Enabled", "This defines if this component is enabled or not", true);
    }

    @Override
    public boolean hasSubscriptions() {
        return true;
    }

    @SubscribeEvent
    public void registerTweak(BreakSpeed event) {
        // Checks if feature is enabled
        if (!enabled) {
            return;
        }
        // Checks that neither the block nor the held item are null
        event.getState().getBlock();
        if (event.getEntityPlayer().getHeldItemMainhand().isEmpty()) {
            return;
        }

        Block block = event.getState().getBlock();
        ItemStack heldItem = event.getEntityPlayer().getHeldItemMainhand();

        // Checks that the held item is a tool
        if (heldItem.getItem() instanceof ItemTool) {
            ItemTool tool = (ItemTool) heldItem.getItem();

            // Checks for axe-ing capabilities
            if (tool.getToolClasses(heldItem).contains("axe")) {
                if (block == Blocks.MELON_BLOCK) {
                    event.setNewSpeed(event.getOriginalSpeed() * 4);
                }
                if (block.getDefaultState().getMaterial() == Material.LEAVES) {
                    event.setNewSpeed(event.getOriginalSpeed() * 6);
                }
                if (block == Blocks.HAY_BLOCK) {
                    event.setNewSpeed(event.getOriginalSpeed() * 4);
                }
                if (block == Blocks.LADDER) {
                    event.setNewSpeed(event.getOriginalSpeed() * 5);
                }
                if (block == Blocks.CACTUS) {
                    event.setNewSpeed(event.getOriginalSpeed() * 2);
                }
            }
            // Checks for pickaxe-ing capabilities
            if (tool.getToolClasses(heldItem).contains("pickaxe")) {
                if (block.getDefaultState().getMaterial() == Material.GLASS) {
                    event.setNewSpeed(event.getOriginalSpeed() * 5);
                }
                if (block == Blocks.PACKED_ICE) {
                    event.setNewSpeed(event.getOriginalSpeed() * 5);
                }
                if (block instanceof BlockSkull) {
                    event.setNewSpeed(event.getOriginalSpeed() * 4);
                }
                if (block instanceof BlockLever) {
                    event.setNewSpeed(event.getOriginalSpeed() * 4);
                }
                if (block.getDefaultState().getMaterial() == Material.PISTON) {
                    event.setNewSpeed(event.getOriginalSpeed() * 4);
                }
            }
        }

        if (heldItem.getItem() instanceof ItemShears) {
            if (block == Blocks.HAY_BLOCK) {
                event.setNewSpeed(event.getOriginalSpeed() * 4);
            }
        }
    }
}