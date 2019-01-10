//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import net.minecraft.block.Blocks;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class class_3910 extends Container {
    private final class_3914 field_17294;
    private boolean field_17295;
    private ItemStack field_17296;
    private ItemStack field_17297;
    public final Inventory field_17293;

    public class_3910(int int_1, PlayerInventory playerInventory_1) {
        this(int_1, playerInventory_1, class_3914.field_17304);
    }

    public class_3910(int int_1, final PlayerInventory playerInventory_1, final class_3914 class_3914_1) {
        super(int_1);
        this.field_17296 = ItemStack.EMPTY;
        this.field_17297 = ItemStack.EMPTY;
        this.field_17293 = new BasicInventory(3) {
            public void markDirty() {
                class_3910.this.onContentChanged(this);
                super.markDirty();
            }
        };
        this.field_17294 = class_3914_1;
        this.addSlot(new Slot(this.field_17293, 0, 15, 15) {
            public boolean canInsert(ItemStack itemStack_1) {
                return itemStack_1.getItem() == Items.FILLED_MAP;
            }
        });
        this.addSlot(new Slot(this.field_17293, 1, 15, 52) {
            public boolean canInsert(ItemStack itemStack_1) {
                Item item_1 = itemStack_1.getItem();
                return item_1 == Items.PAPER || item_1 == Items.MAP || item_1 == Items.field_8141;
            }
        });
        this.addSlot(new Slot(this.field_17293, 2, 145, 39) {
            public boolean canInsert(ItemStack itemStack_1) {
                return false;
            }

            public ItemStack onTakeItem(PlayerEntity playerEntity_1, ItemStack itemStack_1) {
                ItemStack itemStack_2 = (ItemStack)class_3914_1.method_17395((world_1, blockPos_1) -> {
                    if (!class_3910.this.field_17295 && class_3910.this.field_17293.getInvStack(1).getItem() == Items.field_8141) {
                        ItemStack itemStack_2 = FilledMapItem.method_17442(world_1, class_3910.this.field_17296);
                        if (itemStack_2 != null) {
                            return itemStack_2;
                        }
                    }

                    return itemStack_1;
                }).orElse(itemStack_1);
                this.inventory.takeInvStack(0, 1);
                this.inventory.takeInvStack(1, 1);
                playerInventory_1.setCursorStack(itemStack_2);
                itemStack_2.getItem().onCrafted(itemStack_2, playerEntity_1.world, playerEntity_1);
                class_3914_1.method_17393((world_1, blockPos_1) -> {
                    world_1.playSound((PlayerEntity)null, blockPos_1, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, SoundCategory.BLOCK, 1.0F, 1.0F);
                });
                return super.onTakeItem(playerEntity_1, itemStack_2);
            }
        });

        int int_4;
        for(int_4 = 0; int_4 < 3; ++int_4) {
            for(int int_3 = 0; int_3 < 9; ++int_3) {
                this.addSlot(new Slot(playerInventory_1, int_3 + int_4 * 9 + 9, 8 + int_3 * 18, 84 + int_4 * 18));
            }
        }

        for(int_4 = 0; int_4 < 9; ++int_4) {
            this.addSlot(new Slot(playerInventory_1, int_4, 8 + int_4 * 18, 142));
        }

    }

    public boolean canUse(PlayerEntity playerEntity_1) {
        return (Boolean)this.field_17294.method_17396((world_1, blockPos_1) -> {
            return world_1.getBlockState(blockPos_1).getBlock() != Blocks.CARTOGRAPHY_TABLE ? false : playerEntity_1.squaredDistanceTo((double)blockPos_1.getX() + 0.5D, (double)blockPos_1.getY() + 0.5D, (double)blockPos_1.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    public void onContentChanged(Inventory inventory_1) {
        ItemStack itemStack_1 = this.field_17293.getInvStack(0);
        ItemStack itemStack_2 = this.field_17293.getInvStack(1);
        boolean boolean_1 = !ItemStack.areEqual(itemStack_1, this.field_17296);
        boolean boolean_2 = !ItemStack.areEqual(itemStack_2, this.field_17297);
        this.field_17296 = itemStack_1.copy();
        this.field_17297 = itemStack_2.copy();
        if (boolean_1 || boolean_2) {
            ItemStack itemStack_3 = this.field_17293.getInvStack(2);
            if (!itemStack_3.isEmpty() && (itemStack_1.isEmpty() || itemStack_2.isEmpty())) {
                this.field_17293.removeInvStack(2);
            } else if (!itemStack_1.isEmpty() && !itemStack_2.isEmpty()) {
                this.method_17381(itemStack_1, itemStack_2, itemStack_3);
            }
        }

    }

    private void method_17381(ItemStack itemStack_1, ItemStack itemStack_2, ItemStack itemStack_3) {
        this.field_17294.method_17393((world_1, blockPos_1) -> {
            Item item_1 = itemStack_2.getItem();
            MapState mapState_1 = FilledMapItem.method_17441(itemStack_1, world_1);
            if (mapState_1 != null) {
                ItemStack itemStack_6;
                if (item_1 == Items.PAPER && !mapState_1.field_17403 && mapState_1.scale < 4) {
                    itemStack_6 = itemStack_1.copy();
                    itemStack_6.setAmount(1);
                    itemStack_6.getOrCreateTag().putInt("map_scale_direction", 1);
                } else if (item_1 == Items.field_8141 && !mapState_1.field_17403) {
                    itemStack_6 = itemStack_1.copy();
                    itemStack_6.setAmount(1);
                } else {
                    if (item_1 != Items.MAP) {
                        this.field_17293.removeInvStack(2);
                        this.sendContentUpdates();
                        return;
                    }

                    itemStack_6 = itemStack_1.copy();
                    itemStack_6.setAmount(2);
                }

                if (!ItemStack.areEqual(itemStack_6, itemStack_3)) {
                    this.field_17293.setInvStack(2, itemStack_6);
                    this.sendContentUpdates();
                }

            }
        });
    }

    public class_3917<?> method_17358() {
        return class_3917.CARTOGRAPHY;
    }

    public ItemStack transferSlot(PlayerEntity playerEntity_1, int int_1) {
        ItemStack itemStack_1 = ItemStack.EMPTY;
        Slot slot_1 = (Slot)this.slotList.get(int_1);
        if (slot_1 != null && slot_1.hasStack()) {
            ItemStack itemStack_2 = slot_1.getStack();
            ItemStack itemStack_3 = itemStack_2;
            Item item_1 = itemStack_2.getItem();
            itemStack_1 = itemStack_2.copy();
            if (int_1 == 2) {
                if (this.field_17293.getInvStack(1).getItem() == Items.field_8141) {
                    itemStack_3 = (ItemStack)this.field_17294.method_17395((world_1, blockPos_1) -> {
                        ItemStack itemStack_2x = FilledMapItem.method_17442(world_1, this.field_17296);
                        return itemStack_2x != null ? itemStack_2x : itemStack_2;
                    }).orElse(itemStack_2);
                }

                item_1.onCrafted(itemStack_3, playerEntity_1.world, playerEntity_1);
                if (!this.insertItem(itemStack_3, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot_1.onStackChanged(itemStack_3, itemStack_1);
            } else if (int_1 != 1 && int_1 != 0) {
                if (item_1 == Items.FILLED_MAP) {
                    if (!this.insertItem(itemStack_2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (item_1 != Items.PAPER && item_1 != Items.MAP && item_1 != Items.field_8141) {
                    if (int_1 >= 3 && int_1 < 30) {
                        if (!this.insertItem(itemStack_2, 30, 39, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (int_1 >= 30 && int_1 < 39 && !this.insertItem(itemStack_2, 3, 30, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.insertItem(itemStack_2, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack_2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack_3.isEmpty()) {
                slot_1.setStack(ItemStack.EMPTY);
            }

            slot_1.markDirty();
            if (itemStack_3.getAmount() == itemStack_1.getAmount()) {
                return ItemStack.EMPTY;
            }

            this.field_17295 = true;
            slot_1.onTakeItem(playerEntity_1, itemStack_3);
            this.field_17295 = false;
            this.sendContentUpdates();
        }

        return itemStack_1;
    }

    public void close(PlayerEntity playerEntity_1) {
        super.close(playerEntity_1);
        this.field_17293.removeInvStack(2);
        this.field_17294.method_17393((world_1, blockPos_1) -> {
            this.method_7607(playerEntity_1, playerEntity_1.world, this.field_17293);
        });
    }
}
