/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCable extends ModelBase
{
    // fields
    ModelRenderer cableCore;
    ModelRenderer cableTop;
    ModelRenderer cableBottom;
    ModelRenderer cableRight;
    ModelRenderer cableLeft;
    ModelRenderer cableBack;
    ModelRenderer cableFront;

    public ModelCable()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.cableCore = new ModelRenderer(this, 0, 0);
        this.cableCore.addBox(-3F, 0F, -3F, 6, 6, 6);
        this.cableCore.setRotationPoint(0F, 13F, 0F);
        this.cableCore.setTextureSize(64, 32);
        this.cableCore.mirror = true;
        this.setRotation(this.cableCore, 0F, 0F, 0F);
        this.cableTop = new ModelRenderer(this, 24, 0);
        this.cableTop.addBox(-3F, 0F, -3F, 6, 5, 6);
        this.cableTop.setRotationPoint(0F, 8F, 0F);
        this.cableTop.setTextureSize(64, 32);
        this.cableTop.mirror = true;
        this.setRotation(this.cableTop, 0F, 0F, 0F);
        this.cableBottom = new ModelRenderer(this, 24, 0);
        this.cableBottom.addBox(-3F, 0F, -3F, 6, 5, 6);
        this.cableBottom.setRotationPoint(0F, 19F, 0F);
        this.cableBottom.setTextureSize(64, 32);
        this.cableBottom.mirror = true;
        this.setRotation(this.cableBottom, 0F, 0F, 0F);
        this.cableRight = new ModelRenderer(this, 0, 12);
        this.cableRight.addBox(-3F, 0F, -3F, 5, 6, 6);
        this.cableRight.setRotationPoint(-5F, 13F, 0F);
        this.cableRight.setTextureSize(64, 32);
        this.cableRight.mirror = true;
        this.setRotation(this.cableRight, 0F, 0F, 0F);
        this.cableLeft = new ModelRenderer(this, 0, 12);
        this.cableLeft.addBox(-2F, 0F, -3F, 5, 6, 6);
        this.cableLeft.setRotationPoint(5F, 13F, 0F);
        this.cableLeft.setTextureSize(64, 32);
        this.cableLeft.mirror = true;
        this.setRotation(this.cableLeft, 0F, 0F, 0F);
        this.cableBack = new ModelRenderer(this, 24, 11);
        this.cableBack.addBox(-3F, 0F, -2F, 6, 6, 5);
        this.cableBack.setRotationPoint(0F, 13F, 5F);
        this.cableBack.setTextureSize(64, 32);
        this.cableBack.mirror = true;
        this.setRotation(this.cableBack, 0F, 0F, 0F);
        this.cableFront = new ModelRenderer(this, 24, 11);
        this.cableFront.addBox(-3F, 0F, -3F, 6, 6, 5);
        this.cableFront.setRotationPoint(0F, 13F, -5F);
        this.cableFront.setTextureSize(64, 32);
        this.cableFront.mirror = true;
        this.setRotation(this.cableFront, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.cableCore.render(f5);
        this.cableTop.render(f5);
        this.cableBottom.render(f5);
        this.cableRight.render(f5);
        this.cableLeft.render(f5);
        this.cableBack.render(f5);
        this.cableFront.render(f5);
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

    public void renderModel(float f, boolean top, boolean bottom, boolean left, boolean right, boolean front, boolean back)
    {
        this.cableCore.render(f);
        if (top)
            this.cableTop.render(f);
        if (bottom)
            this.cableBottom.render(f);
        if (right)
            this.cableRight.render(f);
        if (left)
            this.cableLeft.render(f);
        if (back)
            this.cableBack.render(f);
        if (front)
            this.cableFront.render(f);
    }
}