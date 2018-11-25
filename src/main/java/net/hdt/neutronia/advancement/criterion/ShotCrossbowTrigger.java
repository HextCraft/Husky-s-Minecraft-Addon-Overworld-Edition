/*
package net.hdt.neutronia.advancement.criterion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hdt.neutronia.base.lib.LibMisc;
import net.minecraft.advancement.ServerAdvancementManager;
import net.minecraft.advancement.criterion.CriterionTrigger.ConditionsContainer;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerServerEntity;
import net.minecraft.item.ContainerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.json.ItemPredicate.Builder;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShotCrossbowTrigger implements ICriterionTrigger<ShotCrossbowTrigger.Conditions> {
    private static final ResourceLocation ID = new ResourceLocation(LibMisc.MOD_ID,"shot_crossbow");
    private final Map<AdvancementManager, ShotCrossbowTrigger.class_2124> field_9744 = Maps.newHashMap();

    public ShotCrossbowTrigger() {
    }

    public ResourceLocation getId() {
        return ID;
    }

    public void addListener(PlayerAdvancements var1, ICriterionTrigger.Listener<ShotCrossbowTrigger.Conditions> var2) {
        ShotCrossbowTrigger.class_2124 var3 = this.field_9744.get(var1);
        if (var3 == null) {
            var3 = new ShotCrossbowTrigger.class_2124(var1);
            this.field_9744.put(var1, var3);
        }

        var3.method_9116(var2);
    }

    public void removeListener(PlayerAdvancements var1, ICriterionTrigger.Listener<ShotCrossbowTrigger.Conditions> var2) {
        ShotCrossbowTrigger.class_2124 var3 = this.field_9744.get(var1);
        if (var3 != null) {
            var3.method_9119(var2);
            if (var3.method_9117()) {
                this.field_9744.remove(var1);
            }
        }

    }

    public void removeAllListeners(PlayerAdvancements var1) {
        this.field_9744.remove(var1);
    }

    public ShotCrossbowTrigger.Conditions deserializeConditions(JsonObject var1, JsonDeserializationContext var2) {
        ItemPredicate var3 = ItemPredicate.deserialize(var1.get("item"));
        return new ShotCrossbowTrigger.Conditions(var3);
    }

    public void method_9115(EntityPlayerMP var1, ItemStack var2) {
        ShotCrossbowTrigger.class_2124 var3 = this.field_9744.get(var1.getAdvancements());
        if (var3 != null) {
            var3.method_9118(var2);
        }

    }

    static class class_2124 {
        private final PlayerAdvancements field_9746;
        private final Set<ICriterionTrigger<ShotCrossbowTrigger.Conditions>> field_9745 = Sets.newHashSet();

        public class_2124(PlayerAdvancements var1) {
            this.field_9746 = var1;
        }

        public boolean method_9117() {
            return this.field_9745.isEmpty();
        }

        public void method_9116(ICriterionTrigger<ShotCrossbowTrigger.Conditions> var1) {
            this.field_9745.add(var1);
        }

        public void method_9119(ICriterionTrigger<ShotCrossbowTrigger.Conditions> var1) {
            this.field_9745.remove(var1);
        }

        public void method_9118(ItemStack var1) {
            List<ICriterionTrigger.Listener<ShotCrossbowTrigger.Conditions>> var2 = null;
            Iterator var3 = this.field_9745.iterator();

            ICriterionTrigger.Listener var4;
            while(var3.hasNext()) {
                var4 = (ICriterionTrigger.Listener)var3.next();
                if (((ShotCrossbowTrigger.Conditions)var4.getCriterionInstance()).matches(var1)) {
                    if (var2 == null) {
                        var2 = Lists.newArrayList();
                    }

                    var2.add(var4);
                }
            }

            if (var2 != null) {
                var3 = var2.iterator();

                while(var3.hasNext()) {
                    var4 = (ICriterionTrigger.Listener)var3.next();
                    var4.equals(this.field_9746);
                }
            }

        }
    }

    public static class Conditions extends AbstractCriterionInstance {
        private final ItemPredicate item;

        public Conditions(ItemPredicate var1) {
            super(ShotCrossbowTrigger.ID);
            this.item = var1;
        }

        public static ShotCrossbowTrigger.Conditions create(ContainerItem var0) {
            return new ShotCrossbowTrigger.Conditions(Builder.create().item(var0).build());
        }

        public boolean matches(ItemStack var1) {
            return this.item.matches(var1);
        }

        public JsonElement method_807() {
            JsonObject var1 = new JsonObject();
            var1.add("item", this.item.method_8971());
            return var1;
        }
    }
}
*/
