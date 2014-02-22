/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScannerMiddle extends ModelBase
{
    // fields
    ModelRenderer sideLcornerB;
    ModelRenderer sideRcornerB;
    ModelRenderer sideLcornerF;
    ModelRenderer sideRcornerF;
    ModelRenderer sideB;
    ModelRenderer sideL;
    ModelRenderer sideR;
    public ModelRenderer doorL;
    public ModelRenderer doorR;

    public ModelScannerMiddle()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;

        this.sideLcornerB = new ModelRenderer(this, 0, 56);
        this.sideLcornerB.addBox(-1F, -8F, -1F, 2, 16, 2);
        this.sideLcornerB.setRotationPoint(-9F, -8F, 9F);
        this.sideLcornerB.setTextureSize(128, 128);
        this.sideLcornerB.mirror = true;
        this.setRotation(this.sideLcornerB, 0F, 0F, 0F);
        this.sideRcornerB = new ModelRenderer(this, 0, 56);
        this.sideRcornerB.addBox(-1F, -8F, -1F, 2, 16, 2);
        this.sideRcornerB.setRotationPoint(9F, -8F, 9F);
        this.sideRcornerB.setTextureSize(128, 128);
        this.sideRcornerB.mirror = true;
        this.setRotation(this.sideRcornerB, 0F, 1.570796F, 0F);
        this.sideLcornerF = new ModelRenderer(this, 0, 56);
        this.sideLcornerF.addBox(-1F, -8F, -1F, 2, 16, 2);
        this.sideLcornerF.setRotationPoint(-9F, -8F, -9F);
        this.sideLcornerF.setTextureSize(128, 128);
        this.sideLcornerF.mirror = true;
        this.setRotation(this.sideLcornerF, 0F, -1.570796F, 0F);
        this.sideRcornerF = new ModelRenderer(this, 0, 56);
        this.sideRcornerF.addBox(-1F, -8F, -1F, 2, 16, 2);
        this.sideRcornerF.setRotationPoint(9F, -8F, -9F);
        this.sideRcornerF.setTextureSize(128, 128);
        this.sideRcornerF.mirror = true;
        this.setRotation(this.sideRcornerF, 0F, 3.141593F, 0F);
        this.sideB = new ModelRenderer(this, 80, 0);
        this.sideB.addBox(-8F, -8F, -1F, 16, 16, 2);
        this.sideB.setRotationPoint(0F, -8F, 11F);
        this.sideB.setTextureSize(128, 128);
        this.sideB.mirror = true;
        this.setRotation(this.sideB, 0F, 3.141593F, 0F);
        this.sideL = new ModelRenderer(this, 80, 0);
        this.sideL.addBox(-8F, -8F, 0F, 16, 16, 2);
        this.sideL.setRotationPoint(-12F, -8F, 0F);
        this.sideL.setTextureSize(128, 128);
        this.sideL.mirror = true;
        this.setRotation(this.sideL, 0F, 1.570796F, 0F);
        this.sideR = new ModelRenderer(this, 80, 0);
        this.sideR.addBox(-8F, -8F, 0F, 16, 16, 2);
        this.sideR.setRotationPoint(12F, -8F, 0F);
        this.sideR.setTextureSize(128, 128);
        this.sideR.mirror = true;
        this.setRotation(this.sideR, 0F, -1.570796F, 0F);
        this.doorL = new ModelRenderer(this, 8, 56);
        this.doorL.addBox(-1F, -8F, -8F, 2, 16, 8);
        this.doorL.setRotationPoint(-9F, -8F, 0F);
        this.doorL.setTextureSize(128, 128);
        this.doorL.mirror = true;
        this.setRotation(this.doorL, 0F, 0F, 0F);
        this.doorR = new ModelRenderer(this, 8, 56);
        this.doorR.addBox(-1F, -8F, 0F, 2, 16, 8);
        this.doorR.setRotationPoint(9F, -8F, 0F);
        this.doorR.setTextureSize(128, 128);
        this.doorR.mirror = true;
        this.setRotation(this.doorR, 0F, -3.141593F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.sideLcornerB.render(f5);
        this.sideRcornerB.render(f5);
        this.sideLcornerF.render(f5);
        this.sideRcornerF.render(f5);
        this.sideB.render(f5);
        this.sideL.render(f5);
        this.sideR.render(f5);
        this.doorL.render(f5);
        this.doorR.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        // Stub
    }

}
