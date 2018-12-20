package team.hdt.neutronia_revamped.entity.render.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import team.hdt.neutronia_revamped.entity.EntityPanda;

@SideOnly(Side.CLIENT)
public class PandaEntityModel<T extends EntityPanda> extends ModelQuadruped {

    private float field_3470;
    private float field_3469;
    private float field_3468;

    public PandaEntityModel(int var1, float var2) {
        super(var1, var2);
        this.textureHeight = 64;
        this.textureWidth = 64;
        this.head = new ModelRenderer(this, 0, 6);
        this.head.addBox(-6.5F, -5.0F, -4.0F, 13, 10, 9);
        this.head.setRotationPoint(0.0F, 11.5F, -17.0F);
        this.head.setTextureOffset(45, 16).addBox(-3.5F, 0.0F, -6.0F, 7, 5, 2);
        this.head.setTextureOffset(52, 25).addBox(-8.5F, -8.0F, -1.0F, 5, 4, 1);
        this.head.setTextureOffset(52, 25).addBox(3.5F, -8.0F, -1.0F, 5, 4, 1);
        this.body = new ModelRenderer(this, 0, 25);
        this.body.addBox(-9.5F, -13.0F, -6.5F, 19, 26, 13);
        this.body.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 40, 0);
        this.leg1.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg1.setRotationPoint(-5.5F, 15.0F, 9.0F);
        this.leg2 = new ModelRenderer(this, 40, 0);
        this.leg2.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg2.setRotationPoint(5.5F, 15.0F, 9.0F);
        this.leg3 = new ModelRenderer(this, 40, 0);
        this.leg3.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg3.setRotationPoint(-5.5F, 15.0F, -9.0F);
        this.leg4 = new ModelRenderer(this, 40, 0);
        this.leg4.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg4.setRotationPoint(5.5F, 15.0F, -9.0F);
    }

    @Override
    public void setLivingAnimations(EntityPanda entity, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);
        this.field_3470 = entity.(var4);
        this.field_3469 = var1.method_6555(var4);
        this.field_3468 = var1.isChild() ? 0.0F : var1.method_6560(var4);
    }

    @Override
    public void render(EntityPanda var1, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(var1, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        boolean var8 = var1.method_6521() > 0;
        boolean var9 = var1.method_6545();
        int var10 = var1.method_6532();
        boolean var11 = var1.method_6527();
        boolean var12 = var1.method_6524();
        if (var8) {
            this.head.yaw = 0.35F * MathHelper.sin(0.6F * ageInTicks);
            this.head.roll = 0.35F * MathHelper.sin(0.6F * ageInTicks);
            this.leg3.pitch = -0.75F * MathHelper.sin(0.3F * ageInTicks);
            this.leg4.pitch = 0.75F * MathHelper.sin(0.3F * ageInTicks);
        } else {
            this.head.roll = 0.0F;
        }

        if (var9) {
            if (var10 < 15) {
                this.head.pitch = -0.7853982F * (float)var10 / 14.0F;
            } else if (var10 < 20) {
                float var13 = (float)((var10 - 15) / 5);
                this.head.pitch = -0.7853982F + 0.7853982F * var13;
            }
        }

        if (this.field_3470 > 0.0F) {
            this.body.pitch = this.method_2822(this.body.pitch, 1.7407963F, this.field_3470);
            this.head.pitch = this.method_2822(this.head.pitch, 1.5707964F, this.field_3470);
            this.leg3.roll = -0.27079642F;
            this.leg4.roll = 0.27079642F;
            this.leg1.roll = 0.5707964F;
            this.leg2.roll = -0.5707964F;
            if (var11) {
                this.head.pitch = 1.5707964F + 0.2F * MathHelper.sin(var4 * 0.6F);
                this.leg3.pitch = -0.4F - 0.2F * MathHelper.sin(var4 * 0.6F);
                this.leg4.pitch = -0.4F - 0.2F * MathHelper.sin(var4 * 0.6F);
            }

            if (var12) {
                this.head.pitch = 2.1707964F;
                this.leg3.pitch = -0.9F;
                this.leg4.pitch = -0.9F;
            }
        } else {
            this.leg1.roll = 0.0F;
            this.leg2.roll = 0.0F;
            this.leg3.roll = 0.0F;
            this.leg4.roll = 0.0F;
        }

        if (this.field_3469 > 0.0F) {
            this.leg1.pitch = -0.6F * MathHelper.sin(var4 * 0.15F);
            this.leg2.pitch = 0.6F * MathHelper.sin(var4 * 0.15F);
            this.leg3.pitch = 0.3F * MathHelper.sin(var4 * 0.25F);
            this.leg4.pitch = -0.3F * MathHelper.sin(var4 * 0.25F);
            this.head.pitch = this.method_2822(this.head.pitch, 1.5707964F, this.field_3469);
        }

        if (this.field_3468 > 0.0F) {
            this.head.pitch = this.method_2822(this.head.pitch, 2.0561945F, this.field_3468);
            this.leg1.pitch = -0.5F * MathHelper.sin(var4 * 0.5F);
            this.leg2.pitch = 0.5F * MathHelper.sin(var4 * 0.5F);
            this.leg3.pitch = 0.5F * MathHelper.sin(var4 * 0.5F);
            this.leg4.pitch = -0.5F * MathHelper.sin(var4 * 0.5F);
        }

    }

    protected float method_2822(float var1, float var2, float var3) {
        float var4;
        for(var4 = var2 - var1; var4 < -3.1415927F; var4 += 6.2831855F) {
        }

        while(var4 >= 3.1415927F) {
            var4 -= 6.2831855F;
        }

        return var1 + var3 * var4;
    }

    public void render(T var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        this.render(var1, var2, var3, var4, var5, var6, var7);
        if (this.isChild) {
            float var8 = 3.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, this.field_3540 * var7, this.field_3537 * var7);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float var9 = 0.6F;
            GlStateManager.scalef(0.5555555F, 0.5555555F, 0.5555555F);
            GlStateManager.translatef(0.0F, 23.0F * var7, 0.3F);
            this.head.render(var7);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.33333334F, 0.33333334F, 0.33333334F);
            GlStateManager.translatef(0.0F, 49.0F * var7, 0.0F);
            this.body.render(var7);
            this.leg1.render(var7);
            this.leg2.render(var7);
            this.leg3.render(var7);
            this.leg4.render(var7);
            GlStateManager.popMatrix();
        } else {
            this.head.render(var7);
            this.body.render(var7);
            this.leg1.render(var7);
            this.leg2.render(var7);
            this.leg3.render(var7);
            this.leg4.render(var7);
        }

    }
}
