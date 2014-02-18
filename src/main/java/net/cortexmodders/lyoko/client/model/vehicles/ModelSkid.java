/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.model.vehicles;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSkid extends ModelVehicle
{
    // fields
    ModelRenderer tail2;
    ModelRenderer tail1;
    ModelRenderer skidMoore1pt1;
    ModelRenderer skidMoore1pt2;
    ModelRenderer skidMoore2pt1;
    ModelRenderer skidMoore2pt2;
    ModelRenderer skidHead1;
    ModelRenderer skidHead2;
    ModelRenderer skidHead3;
    ModelRenderer armLeftTop;
    ModelRenderer armLeftMid;
    ModelRenderer armLeftBot;
    ModelRenderer armRightTop;
    ModelRenderer armRightMid;
    ModelRenderer armRightBot;

    public ModelSkid()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;

        this.tail2 = new ModelRenderer(this, 0, 0);
        this.tail2.addBox(-4F, 0F, -4F, 8, 13, 8);
        this.tail2.setRotationPoint(0F, 11F, 0F);
        this.tail2.setTextureSize(64, 32);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0F, 0F, 0F);
        this.tail1 = new ModelRenderer(this, 0, 0);
        this.tail1.addBox(-2F, 0F, -2F, 4, 75, 4);
        this.tail1.setRotationPoint(0F, -64F, 0F);
        this.tail1.setTextureSize(64, 32);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0F, 0F, 0F);
        this.skidMoore1pt1 = new ModelRenderer(this, 0, 0);
        this.skidMoore1pt1.addBox(-3F, 0F, -2F, 6, 8, 4);
        this.skidMoore1pt1.setRotationPoint(0F, -47F, 0F);
        this.skidMoore1pt1.setTextureSize(64, 32);
        this.skidMoore1pt1.mirror = true;
        this.setRotation(this.skidMoore1pt1, 0F, 0F, 0F);
        this.skidMoore1pt2 = new ModelRenderer(this, 0, 0);
        this.skidMoore1pt2.addBox(-3.5F, 0F, -2F, 7, 6, 4);
        this.skidMoore1pt2.setRotationPoint(0F, -46F, 0F);
        this.skidMoore1pt2.setTextureSize(64, 32);
        this.skidMoore1pt2.mirror = true;
        this.setRotation(this.skidMoore1pt2, 0F, 0F, 0F);
        this.skidMoore2pt1 = new ModelRenderer(this, 0, 0);
        this.skidMoore2pt1.addBox(-2F, 0F, -3F, 4, 8, 6);
        this.skidMoore2pt1.setRotationPoint(0F, -16F, 0F);
        this.skidMoore2pt1.setTextureSize(64, 32);
        this.skidMoore2pt1.mirror = true;
        this.setRotation(this.skidMoore2pt1, 0F, 0F, 0F);
        this.skidMoore2pt2 = new ModelRenderer(this, 0, 0);
        this.skidMoore2pt2.addBox(-2F, 0F, -3.5F, 4, 6, 7);
        this.skidMoore2pt2.setRotationPoint(0F, -15F, 0F);
        this.skidMoore2pt2.setTextureSize(64, 32);
        this.skidMoore2pt2.mirror = true;
        this.setRotation(this.skidMoore2pt2, 0F, 0F, 0F);
        this.skidHead1 = new ModelRenderer(this, 0, 0);
        this.skidHead1.addBox(-6.5F, 0F, -6.5F, 13, 5, 13);
        this.skidHead1.setRotationPoint(0F, -87F, 0F);
        this.skidHead1.setTextureSize(64, 32);
        this.skidHead1.mirror = true;
        this.setRotation(this.skidHead1, 0F, 0F, 0F);
        this.skidHead2 = new ModelRenderer(this, 0, 59);
        this.skidHead2.addBox(-10F, 0F, -10F, 20, 14, 20);
        this.skidHead2.setRotationPoint(0F, -82F, 0F);
        this.skidHead2.setTextureSize(64, 32);
        this.skidHead2.mirror = true;
        this.setRotation(this.skidHead2, 0F, 0F, 0F);
        this.skidHead3 = new ModelRenderer(this, 0, 0);
        this.skidHead3.addBox(-6F, 0F, -6F, 12, 4, 12);
        this.skidHead3.setRotationPoint(0F, -68F, 0F);
        this.skidHead3.setTextureSize(64, 32);
        this.skidHead3.mirror = true;
        this.setRotation(this.skidHead3, 0F, 0F, 0F);
        this.armLeftTop = new ModelRenderer(this, 52, 8);
        this.armLeftTop.addBox(0F, -3F, -2.5F, 19, 3, 5);
        this.armLeftTop.setRotationPoint(7.3F, -74F, 0F);
        this.armLeftTop.setTextureSize(64, 32);
        this.armLeftTop.mirror = true;
        this.setRotation(this.armLeftTop, 0F, 0F, 1.099557F);
        this.armLeftMid = new ModelRenderer(this, 52, 23);
        this.armLeftMid.addBox(0F, -3F, -2F, 15, 3, 4);
        this.armLeftMid.setRotationPoint(15.4F, -58.4F, 0F);
        this.armLeftMid.setTextureSize(64, 32);
        this.armLeftMid.mirror = true;
        this.setRotation(this.armLeftMid, 0F, 0F, 1.53589F);
        this.armLeftBot = new ModelRenderer(this, 52, 35);
        this.armLeftBot.addBox(0F, -2F, -1.5F, 16, 2, 3);
        this.armLeftBot.setRotationPoint(16.4F, -43.8F, 0F);
        this.armLeftBot.setTextureSize(64, 32);
        this.armLeftBot.mirror = true;
        this.setRotation(this.armLeftBot, 0F, 0F, 1.675516F);
        this.armRightTop = new ModelRenderer(this, 52, 0);
        this.armRightTop.addBox(0F, 0F, -2.5F, 19, 3, 5);
        this.armRightTop.setRotationPoint(-7.3F, -74F, 0F);
        this.armRightTop.setTextureSize(64, 32);
        this.armRightTop.mirror = true;
        this.setRotation(this.armRightTop, 0F, 0F, 2.042035F);
        this.armRightMid = new ModelRenderer(this, 52, 16);
        this.armRightMid.addBox(0F, 0F, -2F, 15, 3, 4);
        this.armRightMid.setRotationPoint(-15.4F, -58.4F, 0F);
        this.armRightMid.setTextureSize(64, 32);
        this.armRightMid.mirror = true;
        this.setRotation(this.armRightMid, 0F, 0F, 1.605703F);
        this.armRightBot = new ModelRenderer(this, 52, 30);
        this.armRightBot.addBox(0F, 0F, -1.5F, 16, 2, 3);
        this.armRightBot.setRotationPoint(-16.4F, -43.8F, 0F);
        this.armRightBot.setTextureSize(64, 32);
        this.armRightBot.mirror = true;
        this.setRotation(this.armRightBot, 0F, 0F, 1.466077F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.tail2.render(f5);
        this.tail1.render(f5);
        this.skidMoore1pt1.render(f5);
        this.skidMoore1pt2.render(f5);
        this.skidMoore2pt1.render(f5);
        this.skidMoore2pt2.render(f5);
        this.skidHead1.render(f5);
        this.skidHead2.render(f5);
        this.skidHead3.render(f5);
        this.armLeftTop.render(f5);
        this.armLeftMid.render(f5);
        this.armLeftBot.render(f5);
        this.armRightTop.render(f5);
        this.armRightMid.render(f5);
        this.armRightBot.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    @Override
    public void doAnimation()
    {

    }
}
