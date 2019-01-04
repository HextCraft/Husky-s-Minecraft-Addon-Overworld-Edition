package team.hdt.neutronia.client.entity.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface class_3882 {
    ModelRenderer method_2838();

    default void method_17148(float float_1) {
        this.method_2838().render(float_1);
    }
}
