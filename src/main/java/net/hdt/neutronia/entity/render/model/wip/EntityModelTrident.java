package net.hdt.neutronia.entity.render.model;

import net.minecraft.util.Identifier;
import net.fabricmc.api.Side;
import net.fabricmc.api.Sided;

@Sided(Side.CLIENT)
public class EntityModelTrident extends EntityModel
{
    public static final Identifier a;
    private final ModelRenderer b;
    
    public EntityModelTrident() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        (this.b = new ModelRenderer(this, 0, 0)).addBox(-0.5f, -4.0f, -0.5f, 1, 31, 1, 0.0f);
        final ModelRenderer vModelRenderer1 = new ModelRenderer(this, 4, 0);
        vModelRenderer1.a(-1.5f, 0.0f, -0.5f, 3, 2, 1);
        this.b.addChild(vModelRenderer1);
        final ModelRenderer vModelRenderer2 = new ModelRenderer(this, 4, 3);
        vModelRenderer2.a(-2.5f, -3.0f, -0.5f, 1, 4, 1);
        this.b.addChild(vModelRenderer2);
        final ModelRenderer vModelRenderer3 = new ModelRenderer(this, 4, 3);
        vModelRenderer3.mirror = true;
        vModelRenderer3.a(1.5f, -3.0f, -0.5f, 1, 4, 1);
        this.b.addChild(vModelRenderer3);
    }
    
    public void a() {
        this.b.render(0.0625f);
    }
    
    static {
        a = new Identifier("textures/entity/trident.png");
    }
}
