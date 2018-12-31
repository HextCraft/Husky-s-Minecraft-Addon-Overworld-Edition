package team.hdt.neutronia.world.template;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.gen.structure.template.PlacementSettings;

import java.util.Random;
import java.util.function.BiConsumer;

public class RotatedSettingConfigurator implements BiConsumer<PlacementSettings, Random> {
    public static final RotatedSettingConfigurator INSTANCE = new RotatedSettingConfigurator();

    private RotatedSettingConfigurator() {
    }

    @Override
    public void accept(PlacementSettings settings, Random random) {
        Rotation[] rotations = Rotation.values();
        Mirror[] mirrors = Mirror.values();

        settings.setRotation(rotations[random.nextInt(rotations.length)]);
        settings.setMirror(mirrors[random.nextInt(mirrors.length)]);
    }
}