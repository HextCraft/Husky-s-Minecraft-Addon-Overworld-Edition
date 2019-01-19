package team.abnormal.neutronia.blocks.pumpkin;

import net.minecraft.util.IStringSerializable;

public class PumpkinHelper {

    public enum FaceTypes implements IStringSerializable {
        CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
        OR, SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK;

        @Override
        public String getName() {
            return this.toString().toLowerCase();
        }

    }

}
