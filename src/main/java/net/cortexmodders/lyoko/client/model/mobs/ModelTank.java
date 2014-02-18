/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.model.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTank extends ModelBase
{
    // fields
    ModelRenderer core;
    ModelRenderer top;
    ModelRenderer bottom;
    ModelRenderer left;
    ModelRenderer right;
    ModelRenderer front;
    ModelRenderer back;

    public ModelTank()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.core = new ModelRenderer(this, 0, 0);
        this.core.addBox(-8F, -8F, -8F, 16, 16, 16);
        this.core.setRotationPoint(0F, 14F, 0F);
        this.core.setTextureSize(128, 64);
        this.core.mirror = true;
        this.setRotation(this.core, 0F, 0F, 0F);
        this.top = new ModelRenderer(this, 64, 0);
        this.top.addBox(-6F, -10F, -6F, 12, 2, 12);
        this.top.setRotationPoint(0F, 14F, 0F);
        this.top.setTextureSize(128, 64);
        this.top.mirror = true;
        this.setRotation(this.top, 0F, 0F, 0F);
        this.bottom = new ModelRenderer(this, 64, 0);
        this.bottom.addBox(-6F, 8F, -6F, 12, 2, 12);
        this.bottom.setRotationPoint(0F, 14F, 0F);
        this.bottom.setTextureSize(128, 64);
        this.bottom.mirror = true;
        this.setRotation(this.bottom, 0F, 0F, 0F);
        this.left = new ModelRenderer(this, 0, 32);
        this.left.addBox(8F, -6F, -6F, 2, 12, 12);
        this.left.setRotationPoint(0F, 14F, 0F);
        this.left.setTextureSize(128, 64);
        this.left.mirror = true;
        this.setRotation(this.left, 0F, 0F, 0F);
        this.right = new ModelRenderer(this, 0, 32);
        this.right.addBox(-10F, -6F, -6F, 2, 12, 12);
        this.right.setRotationPoint(0F, 14F, 0F);
        this.right.setTextureSize(128, 64);
        this.right.mirror = true;
        this.setRotation(this.right, 0F, 0F, 0F);
        this.front = new ModelRenderer(this, 64, 14);
        this.front.addBox(-6F, -6F, -10F, 12, 12, 2);
        this.front.setRotationPoint(0F, 14F, 0F);
        this.front.setTextureSize(128, 64);
        this.front.mirror = true;
        this.setRotation(this.front, 0F, 0F, 0F);
        this.back = new ModelRenderer(this, 64, 14);
        this.back.addBox(-6F, -6F, 8F, 12, 12, 2);
        this.back.setRotationPoint(0F, 14F, 0F);
        this.back.setTextureSize(128, 64);
        this.back.mirror = true;
        this.setRotation(this.back, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.core.render(f5);
        this.top.render(f5);
        this.bottom.render(f5);
        this.left.render(f5);
        this.right.render(f5);
        this.front.render(f5);
        this.back.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }

}
