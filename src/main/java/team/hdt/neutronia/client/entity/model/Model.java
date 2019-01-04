package team.hdt.neutronia.client.entity.model;

import com.google.common.collect.Lists;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class Model {
    public List<ModelRenderer> cuboidList = Lists.newArrayList();
    public int textureWidth = 64;
    public int textureHeight = 32;

    public Model() {
    }

    public ModelRenderer getRandomCuboid(Random random_1) {
        return this.cuboidList.get(random_1.nextInt(this.cuboidList.size()));
    }
}
