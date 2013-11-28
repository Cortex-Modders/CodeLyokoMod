/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.model.tileentity;

import matt.lyoko.entities.tileentity.TileEntityScanner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelScanner extends ModelBase
{
    // fields
    ModelRenderer base;
    ModelRenderer baseL;
    ModelRenderer baseB;
    ModelRenderer baseF;
    ModelRenderer baseR;
    ModelRenderer sideLcornerB;
    ModelRenderer sideRcornerB;
    ModelRenderer sideLcornerF;
    ModelRenderer sideRcornerF;
    ModelRenderer sideB;
    ModelRenderer sideL;
    ModelRenderer sideR;
    public ModelRenderer doorL;
    public ModelRenderer doorR;
    ModelRenderer top;
    ModelRenderer topL;
    ModelRenderer topF;
    ModelRenderer topB;
    ModelRenderer topR;

    public ModelScanner()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;

        this.base = new ModelRenderer(this, 0, 28);
        this.base.addBox(-10F, -8F, -10F, 20, 8, 20);
        this.base.setRotationPoint(0F, 0F, 0F);
        this.base.setTextureSize(128, 128);
        this.base.mirror = true;
        this.setRotation(this.base, 0F, 0F, 0F);
        this.baseL = new ModelRenderer(this, 22, 46);
        this.baseL.addBox(-8F, -4F, 0F, 16, 8, 2);
        this.baseL.setRotationPoint(-12F, -4F, 0F);
        this.baseL.setTextureSize(128, 128);
        this.baseL.mirror = true;
        this.setRotation(this.baseL, 0F, 1.570796F, 0F);
        this.baseB = new ModelRenderer(this, 22, 46);
        this.baseB.addBox(-8F, -4F, 0F, 16, 8, 2);
        this.baseB.setRotationPoint(0F, -4F, 10F);
        this.baseB.setTextureSize(128, 128);
        this.baseB.mirror = true;
        this.setRotation(this.baseB, 0F, 0F, 0F);
        this.baseF = new ModelRenderer(this, 22, 46);
        this.baseF.addBox(-8F, -4F, -2F, 16, 8, 2);
        this.baseF.setRotationPoint(0F, -4F, -10F);
        this.baseF.setTextureSize(128, 128);
        this.baseF.mirror = true;
        this.setRotation(this.baseF, 0F, 0F, 0F);
        this.baseR = new ModelRenderer(this, 22, 46);
        this.baseR.addBox(-8F, -4F, 0F, 16, 8, 2);
        this.baseR.setRotationPoint(10F, -4F, 0F);
        this.baseR.setTextureSize(128, 128);
        this.baseR.mirror = true;
        this.setRotation(this.baseR, 0F, 1.570796F, 0F);
        this.sideLcornerB = new ModelRenderer(this, 0, 56);
        this.sideLcornerB.addBox(-1F, -32F, -1F, 2, 64, 2);
        this.sideLcornerB.setRotationPoint(-9F, -40F, 9F);
        this.sideLcornerB.setTextureSize(128, 128);
        this.sideLcornerB.mirror = true;
        this.setRotation(this.sideLcornerB, 0F, 0F, 0F);
        this.sideRcornerB = new ModelRenderer(this, 0, 56);
        this.sideRcornerB.addBox(-1F, -32F, -1F, 2, 64, 2);
        this.sideRcornerB.setRotationPoint(9F, -40F, 9F);
        this.sideRcornerB.setTextureSize(128, 128);
        this.sideRcornerB.mirror = true;
        this.setRotation(this.sideRcornerB, 0F, (float) Math.toRadians(90), 0F);
        this.sideLcornerF = new ModelRenderer(this, 0, 56);
        this.sideLcornerF.addBox(-1F, -32F, -1F, 2, 64, 2);
        this.sideLcornerF.setRotationPoint(-9F, -40F, -9F);
        this.sideLcornerF.setTextureSize(128, 128);
        this.sideLcornerF.mirror = true;
        this.setRotation(this.sideLcornerF, 0F, (float) Math.toRadians(-90), 0F);
        this.sideRcornerF = new ModelRenderer(this, 0, 56);
        this.sideRcornerF.addBox(-1F, -32F, -1F, 2, 64, 2);
        this.sideRcornerF.setRotationPoint(9F, -40F, -9F);
        this.sideRcornerF.setTextureSize(128, 128);
        this.sideRcornerF.mirror = true;
        this.setRotation(this.sideRcornerF, 0F, (float) Math.toRadians(90), 0F);
        this.sideB = new ModelRenderer(this, 80, 0);
        this.sideB.addBox(-8F, -32F, -1F, 16, 64, 2);
        this.sideB.setRotationPoint(0F, -40F, 11F);
        this.sideB.setTextureSize(128, 128);
        this.sideB.mirror = true;
        this.setRotation(this.sideB, 0F, (float) Math.toRadians(180), 0F);
        this.sideL = new ModelRenderer(this, 80, 0);
        this.sideL.addBox(-8F, -32F, 0F, 16, 64, 2);
        this.sideL.setRotationPoint(-12F, -40F, 0F);
        this.sideL.setTextureSize(128, 128);
        this.sideL.mirror = true;
        this.setRotation(this.sideL, 0F, 1.570796F, 0F);
        this.sideR = new ModelRenderer(this, 80, 0);
        this.sideR.addBox(-8F, -32F, 0F, 16, 64, 2);
        this.sideR.setRotationPoint(12F, -40F, 0F);
        this.sideR.setTextureSize(128, 128);
        this.sideR.mirror = true;
        this.setRotation(this.sideR, 0F, -1.570796F, 0F);
        this.doorL = new ModelRenderer(this, 8, 56);
        this.doorL.addBox(-1F, -32F, -8F, 2, 64, 8);
        this.doorL.setRotationPoint(-9F, -40F, 0F);
        this.doorL.setTextureSize(128, 128);
        this.doorL.mirror = true;
        this.setRotation(this.doorL, 0F, 0F, 0F);
        this.doorR = new ModelRenderer(this, 8, 56);
        this.doorR.addBox(-1F, -32F, -8F, 2, 64, 8);
        this.doorR.setRotationPoint(9F, -40F, 0F);
        this.doorR.setTextureSize(128, 128);
        this.doorR.mirror = true;
        this.setRotation(this.doorR, (float) Math.toRadians(180), (float) Math.toRadians(-180), 0F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.addBox(-10F, -8F, -10F, 20, 8, 20);
        this.top.setRotationPoint(0F, -72F, 0F);
        this.top.setTextureSize(128, 128);
        this.top.mirror = true;
        this.setRotation(this.top, 0F, 0F, 0F);
        this.topL = new ModelRenderer(this, 22, 18);
        this.topL.addBox(-8F, -4F, 0F, 16, 8, 2);
        this.topL.setRotationPoint(-12F, -76F, 0F);
        this.topL.setTextureSize(128, 128);
        this.topL.mirror = true;
        this.setRotation(this.topL, 0F, 1.570796F, 0F);
        this.topF = new ModelRenderer(this, 22, 18);
        this.topF.addBox(-8F, -4F, -2F, 16, 8, 2);
        this.topF.setRotationPoint(0F, -76F, -10F);
        this.topF.setTextureSize(128, 128);
        this.topF.mirror = true;
        this.setRotation(this.topF, 0F, 0F, 0F);
        this.topB = new ModelRenderer(this, 22, 18);
        this.topB.addBox(-8F, -4F, 0F, 16, 8, 2);
        this.topB.setRotationPoint(0F, -76F, 10F);
        this.topB.setTextureSize(128, 128);
        this.topB.mirror = true;
        this.setRotation(this.topB, 0F, 0F, 0F);
        this.topR = new ModelRenderer(this, 22, 18);
        this.topR.addBox(-8F, -4F, 0F, 16, 8, 2);
        this.topR.setRotationPoint(10F, -76F, 0F);
        this.topR.setTextureSize(128, 128);
        this.topR.mirror = true;
        this.setRotation(this.topR, 0F, 1.570796F, 0F);
    }

    public void render(TileEntityScanner entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.base.render(f5);
        this.baseL.render(f5);
        this.baseB.render(f5);
        this.baseF.render(f5);
        this.baseR.render(f5);
        this.sideLcornerB.render(f5);
        this.sideRcornerB.render(f5);
        this.sideLcornerF.render(f5);
        this.sideRcornerF.render(f5);
        this.sideB.render(f5);
        this.sideL.render(f5);
        this.sideR.render(f5);
        this.doorL.render(f5);
        this.doorR.render(f5);
        this.top.render(f5);
        this.topL.render(f5);
        this.topF.render(f5);
        this.topB.render(f5);
        this.topR.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, TileEntityScanner entity)
    {
        // Stub
    }
}