/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.abnormal.neutronia.tileentities;

import com.sun.istack.internal.Nullable;
import net.minecraft.class_3829;
import net.minecraft.class_3913;
import net.minecraft.class_3916;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;
import team.abnormal.neutronia.blocks.LecternBlock;

public class LecternBlockEntity extends TileEntity implements class_3829, NameableContainerProvider {
    private final InventoryBasic field_17386 = new InventoryBasic() {
        public int getSizeInventory() {
            return 1;
        }

        public boolean isEmpty() {
            return LecternBlockEntity.this.field_17388.isEmpty();
        }

        public ItemStack getStackInSlot(int int_1) {
            return int_1 == 0 ? LecternBlockEntity.this.field_17388 : ItemStack.EMPTY;
        }

        public ItemStack decrStackSize(int int_1, int int_2) {
            if (int_1 == 0) {
                ItemStack itemStack_1 = LecternBlockEntity.this.field_17388.splitStack(int_2);
                if (LecternBlockEntity.this.field_17388.isEmpty()) {
                    LecternBlockEntity.this.method_17525();
                }

                return itemStack_1;
            } else {
                return ItemStack.EMPTY;
            }
        }

        public ItemStack removeStackFromSlot(int int_1) {
            if (int_1 == 0) {
                ItemStack itemStack_1 = LecternBlockEntity.this.field_17388;
                LecternBlockEntity.this.field_17388 = ItemStack.EMPTY;
                LecternBlockEntity.this.method_17525();
                return itemStack_1;
            } else {
                return ItemStack.EMPTY;
            }
        }

        public void setInventorySlotContents(int int_1, ItemStack itemStack_1) {
        }

        public int getInventoryStackLimit() {
            return 1;
        }

        public void markDirty() {
            LecternBlockEntity.this.markDirty();
        }

        public boolean isUsableByPlayer(EntityPlayer playerEntity_1) {
            if (LecternBlockEntity.this.world.getTileEntity(LecternBlockEntity.this.pos) != LecternBlockEntity.this) {
                return false;
            } else {
                return !(playerEntity_1.getDistanceSq((double) LecternBlockEntity.this.pos.getX() + 0.5D, (double) LecternBlockEntity.this.pos.getY() + 0.5D, (double) LecternBlockEntity.this.pos.getZ() + 0.5D) > 64.0D) && LecternBlockEntity.this.method_17522();
            }
        }

        public boolean isItemValidForSlot(int int_1, ItemStack itemStack_1) {
            return false;
        }

        public void clear() {
        }
    };
    private final class_3913 field_17387 = new class_3913() {
        public int method_17390(int int_1) {
            return int_1 == 0 ? LecternBlockEntity.this.field_17389 : 0;
        }

        public void method_17391(int int_1, int int_2) {
            if (int_1 == 0) {
                LecternBlockEntity.this.method_17511(int_2);
            }

        }

        public int method_17389() {
            return 1;
        }
    };
    private ItemStack field_17388;
    private int field_17389;
    private int field_17390;

    public LecternBlockEntity() {
        this.field_17388 = ItemStack.EMPTY;
    }

    public ItemStack method_17520() {
        return this.field_17388;
    }

    public boolean method_17522() {
        Item item_1 = this.field_17388.getItem();
        return item_1 == Items.WRITABLE_BOOK || item_1 == Items.WRITTEN_BOOK;
    }

    public void method_17513(ItemStack itemStack_1) {
        this.method_17514(itemStack_1, null);
    }

    private void method_17525() {
        this.field_17389 = 0;
        this.field_17390 = 0;
        LecternBlock.method_17473(this.getWorld(), this.getPos(), this.getUpdatePacket(), false);
    }

    public void method_17514(ItemStack itemStack_1, @Nullable EntityPlayer playerEntity_1) {
        this.field_17388 = this.method_17518(itemStack_1, playerEntity_1);
        this.field_17389 = 0;
        this.field_17390 = ItemWrittenBook.getGeneration(this.field_17388);
        this.markDirty();
    }

    private void method_17511(int int_1) {
        int int_2 = MathHelper.clamp(int_1, 0, this.field_17390 - 1);
        if (int_2 != this.field_17389) {
            this.field_17389 = int_2;
            this.markDirty();
            LecternBlock.method_17471(this.getWorld(), this.getPos(), this.getCachedState());
        }

    }

    public int method_17523() {
        return this.field_17389;
    }

    public int method_17524() {
        float float_1 = this.field_17390 > 1 ? (float)this.method_17523() / ((float)this.field_17390 - 1.0F) : 1.0F;
        return MathHelper.floor(float_1 * 14.0F) + (this.method_17522() ? 1 : 0);
    }

    private ItemStack method_17518(ItemStack itemStack_1, @Nullable EntityPlayer playerEntity_1) {
        if (this.world instanceof WorldServer && itemStack_1.getItem() == Items.WRITTEN_BOOK) {
            WrittenBookItem.method_8054(itemStack_1, this.method_17512(playerEntity_1), playerEntity_1);
        }

        return itemStack_1;
    }

    private ServerCommandSource method_17512(@Nullable PlayerEntity playerEntity_1) {
        String string_2;
        Object textComponent_2;
        if (playerEntity_1 == null) {
            string_2 = "Lectern";
            textComponent_2 = new StringTextComponent("Lectern");
        } else {
            string_2 = playerEntity_1.getName().getString();
            textComponent_2 = playerEntity_1.getDisplayName();
        }

        Vec3d vec3d_1 = new Vec3d((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D);
        return new ServerCommandSource(CommandOutput.field_17395, vec3d_1, Vec2f.ZERO, (ServerWorld)this.world, 2, string_2, (TextComponent)textComponent_2, this.world.getServer(), playerEntity_1);
    }

    public void fromTag(CompoundTag compoundTag_1) {
        super.fromTag(compoundTag_1);
        if (compoundTag_1.containsKey("Book", 10)) {
            this.field_17388 = this.method_17518(ItemStack.fromTag(compoundTag_1.getCompound("Book")), (PlayerEntity)null);
        } else {
            this.field_17388 = ItemStack.EMPTY;
        }

        this.field_17390 = WrittenBookItem.method_17443(this.field_17388);
        this.field_17389 = MathHelper.clamp(compoundTag_1.getInt("Page"), 0, this.field_17390 - 1);
    }

    public CompoundTag toTag(CompoundTag compoundTag_1) {
        super.toTag(compoundTag_1);
        if (!this.method_17520().isEmpty()) {
            compoundTag_1.put("Book", this.method_17520().toTag(new CompoundTag()));
            compoundTag_1.putInt("Page", this.field_17389);
        }

        return compoundTag_1;
    }

    public void clearInv() {
        this.method_17513(ItemStack.EMPTY);
    }

    public Container createMenu(int int_1, PlayerInventory playerInventory_1, PlayerEntity playerEntity_1) {
        return new class_3916(int_1, this.field_17386, this.field_17387);
    }

    public TextComponent getDisplayName() {
        return new TranslatableTextComponent("container.lectern", new Object[0]);
    }
}
*/
