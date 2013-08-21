/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScannerMiddle extends ModelBase
{
    //fields
    ModelRenderer sideLcornerB;
    ModelRenderer sideRcornerB;
    ModelRenderer sideLcornerF;
    ModelRenderer sideRcornerF;
    ModelRenderer sideB;
    ModelRenderer sideL;
    ModelRenderer sideR;
    public ModelRenderer doorL;
    public ModelRenderer doorR;

    public ModelScannerMiddle() {
        textureWidth = 128;
        textureHeight = 128;

        sideLcornerB = new ModelRenderer(this, 0, 56);
        sideLcornerB.addBox(-1F, -8F, -1F, 2, 16, 2);
        sideLcornerB.setRotationPoint(-9F, -8F, 9F);
        sideLcornerB.setTextureSize(128, 128);
        sideLcornerB.mirror = true;
        setRotation(sideLcornerB, 0F, 0F, 0F);
        sideRcornerB = new ModelRenderer(this, 0, 56);
        sideRcornerB.addBox(-1F, -8F, -1F, 2, 16, 2);
        sideRcornerB.setRotationPoint(9F, -8F, 9F);
        sideRcornerB.setTextureSize(128, 128);
        sideRcornerB.mirror = true;
        setRotation(sideRcornerB, 0F, 1.570796F, 0F);
        sideLcornerF = new ModelRenderer(this, 0, 56);
        sideLcornerF.addBox(-1F, -8F, -1F, 2, 16, 2);
        sideLcornerF.setRotationPoint(-9F, -8F, -9F);
        sideLcornerF.setTextureSize(128, 128);
        sideLcornerF.mirror = true;
        setRotation(sideLcornerF, 0F, -1.570796F, 0F);
        sideRcornerF = new ModelRenderer(this, 0, 56);
        sideRcornerF.addBox(-1F, -8F, -1F, 2, 16, 2);
        sideRcornerF.setRotationPoint(9F, -8F, -9F);
        sideRcornerF.setTextureSize(128, 128);
        sideRcornerF.mirror = true;
        setRotation(sideRcornerF, 0F, 3.141593F, 0F);
        sideB = new ModelRenderer(this, 80, 0);
        sideB.addBox(-8F, -8F, -1F, 16, 16, 2);
        sideB.setRotationPoint(0F, -8F, 11F);
        sideB.setTextureSize(128, 128);
        sideB.mirror = true;
        setRotation(sideB, 0F, 3.141593F, 0F);
        sideL = new ModelRenderer(this, 80, 0);
        sideL.addBox(-8F, -8F, 0F, 16, 16, 2);
        sideL.setRotationPoint(-12F, -8F, 0F);
        sideL.setTextureSize(128, 128);
        sideL.mirror = true;
        setRotation(sideL, 0F, 1.570796F, 0F);
        sideR = new ModelRenderer(this, 80, 0);
        sideR.addBox(-8F, -8F, 0F, 16, 16, 2);
        sideR.setRotationPoint(12F, -8F, 0F);
        sideR.setTextureSize(128, 128);
        sideR.mirror = true;
        setRotation(sideR, 0F, -1.570796F, 0F);
        doorL = new ModelRenderer(this, 8, 56);
        doorL.addBox(-1F, -8F, -8F, 2, 16, 8);
        doorL.setRotationPoint(-9F, -8F, 0F);
        doorL.setTextureSize(128, 128);
        doorL.mirror = true;
        setRotation(doorL, 0F, 0F, 0F);
        doorR = new ModelRenderer(this, 8, 56);
        doorR.addBox(-1F, -8F, 0F, 2, 16, 8);
        doorR.setRotationPoint(9F, -8F, 0F);
        doorR.setTextureSize(128, 128);
        doorR.mirror = true;
        setRotation(doorR, 0F, -3.141593F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        sideLcornerB.render(f5);
        sideRcornerB.render(f5);
        sideLcornerF.render(f5);
        sideRcornerF.render(f5);
        sideB.render(f5);
        sideL.render(f5);
        sideR.render(f5);
        doorL.render(f5);
        doorR.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        // Stub
    }

}
