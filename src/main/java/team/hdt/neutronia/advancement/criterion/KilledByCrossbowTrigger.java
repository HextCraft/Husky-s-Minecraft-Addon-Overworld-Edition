/*
package team.hdt.neutronia.advancement.criterion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancement.ServerAdvancementManager;
import net.minecraft.advancement.criterion.CriterionTrigger.ConditionsContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerServerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.NumberRange.Integer;
import net.minecraft.util.json.EntityJsonPredicate;
import net.minecraft.util.json.EntityJsonPredicate.class_2049;

public class KilledByCrossbowTrigger implements CriterionTrigger<KilledByCrossbowTrigger.Conditions> {
    private static final Identifier ID = new Identifier("killed_by_crossbow");
    private final Map<ServerAdvancementManager, KilledByCrossbowTrigger.class_2077> field_9656 = Maps.newHashMap();

    public KilledByCrossbowTrigger() {
    }

    public Identifier getId() {
        return ID;
    }

    public void addCondition(ServerAdvancementManager var1, ConditionsContainer<KilledByCrossbowTrigger.Conditions> var2) {
        KilledByCrossbowTrigger.class_2077 var3 = (KilledByCrossbowTrigger.class_2077)this.field_9656.get(var1);
        if (var3 == null) {
            var3 = new KilledByCrossbowTrigger.class_2077(var1);
            this.field_9656.put(var1, var3);
        }

        var3.method_8982(var2);
    }

    public void removeCondition(ServerAdvancementManager var1, ConditionsContainer<KilledByCrossbowTrigger.Conditions> var2) {
        KilledByCrossbowTrigger.class_2077 var3 = (KilledByCrossbowTrigger.class_2077)this.field_9656.get(var1);
        if (var3 != null) {
            var3.method_8985(var2);
            if (var3.method_8984()) {
                this.field_9656.remove(var1);
            }
        }

    }

    public void removePlayer(ServerAdvancementManager var1) {
        this.field_9656.remove(var1);
    }

    public KilledByCrossbowTrigger.Conditions deserializeConditions(JsonObject var1, JsonDeserializationContext var2) {
        EntityJsonPredicate[] var3 = EntityJsonPredicate.method_8910(var1.get("victims"));
        Integer var4 = Integer.fromJson(var1.get("unique_entity_types"));
        return new KilledByCrossbowTrigger.Conditions(var3, var4);
    }

    public void method_8980(PlayerServerEntity var1, Collection<Entity> var2, int var3) {
        KilledByCrossbowTrigger.class_2077 var4 = (KilledByCrossbowTrigger.class_2077)this.field_9656.get(var1.method_14236());
        if (var4 != null) {
            var4.method_8983(var1, var2, var3);
        }

    }

    static class class_2077 {
        private final ServerAdvancementManager field_9658;
        private final Set<ConditionsContainer<KilledByCrossbowTrigger.Conditions>> field_9657 = Sets.newHashSet();

        public class_2077(ServerAdvancementManager var1) {
            this.field_9658 = var1;
        }

        public boolean method_8984() {
            return this.field_9657.isEmpty();
        }

        public void method_8982(ConditionsContainer<KilledByCrossbowTrigger.Conditions> var1) {
            this.field_9657.add(var1);
        }

        public void method_8985(ConditionsContainer<KilledByCrossbowTrigger.Conditions> var1) {
            this.field_9657.remove(var1);
        }

        public void method_8983(PlayerServerEntity var1, Collection<Entity> var2, int var3) {
            List<ConditionsContainer<KilledByCrossbowTrigger.Conditions>> var4 = null;
            Iterator var5 = this.field_9657.iterator();

            ConditionsContainer var6;
            while(var5.hasNext()) {
                var6 = (ConditionsContainer)var5.next();
                if (((KilledByCrossbowTrigger.Conditions)var6.getConditions()).matches(var1, var2, var3)) {
                    if (var4 == null) {
                        var4 = Lists.newArrayList();
                    }

                    var4.add(var6);
                }
            }

            if (var4 != null) {
                var5 = var4.iterator();

                while(var5.hasNext()) {
                    var6 = (ConditionsContainer)var5.next();
                    var6.apply(this.field_9658);
                }
            }

        }
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final EntityJsonPredicate[] victims;
        private final Integer uniqueEntityTypes;

        public Conditions(EntityJsonPredicate[] var1, Integer var2) {
            super(KilledByCrossbowTrigger.ID);
            this.victims = var1;
            this.uniqueEntityTypes = var2;
        }

        public static KilledByCrossbowTrigger.Conditions method_8986(class_2049... var0) {
            EntityJsonPredicate[] var1 = new EntityJsonPredicate[var0.length];

            for(int var2 = 0; var2 < var0.length; ++var2) {
                class_2049 var3 = var0[var2];
                var1[var2] = var3.method_8920();
            }

            return new KilledByCrossbowTrigger.Conditions(var1, Integer.ANY);
        }

        public static KilledByCrossbowTrigger.Conditions method_8987(Integer var0) {
            EntityJsonPredicate[] var1 = new EntityJsonPredicate[0];
            return new KilledByCrossbowTrigger.Conditions(var1, var0);
        }

        public boolean matches(PlayerServerEntity var1, Collection<Entity> var2, int var3) {
            if (this.victims.length > 0) {
                List<Entity> var4 = new ArrayList(var2);
                EntityJsonPredicate[] var5 = this.victims;
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    EntityJsonPredicate var8 = var5[var7];
                    boolean var9 = false;
                    Iterator var10 = var4.iterator();

                    while(var10.hasNext()) {
                        Entity var11 = (Entity)var10.next();
                        if (var8.method_8914(var1, var11)) {
                            var10.remove();
                            var9 = true;
                            break;
                        }
                    }

                    if (!var9) {
                        return false;
                    }
                }
            }

            if (this.uniqueEntityTypes == Integer.ANY) {
                return true;
            } else {
                Set<EntityType> var12 = new HashSet();
                Iterator var13 = var2.iterator();

                while(var13.hasNext()) {
                    Entity var14 = (Entity)var13.next();
                    var12.add(var14.getFactory());
                }

                return this.uniqueEntityTypes.matches(var12.size()) && this.uniqueEntityTypes.matches(var3);
            }
        }

        public JsonElement method_807() {
            JsonObject var1 = new JsonObject();
            var1.add("victims", EntityJsonPredicate.method_8911(this.victims));
            var1.add("unique_entity_types", this.uniqueEntityTypes.serialize());
            return var1;
        }
    }
}
*/
