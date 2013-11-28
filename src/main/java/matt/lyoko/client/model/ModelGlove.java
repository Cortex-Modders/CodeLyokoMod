/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGlove extends ModelBase
{
    // fields
    ModelRenderer arm;
    ModelRenderer barrel;
    ModelRenderer barrel2;
    ModelRenderer barrel3;
    ModelRenderer palm;
    ModelRenderer finger1;
    ModelRenderer finger1mid;
    ModelRenderer finger1tip;
    ModelRenderer finger2;
    ModelRenderer finger2mid;
    ModelRenderer finger2tip;
    ModelRenderer finger3;
    ModelRenderer finger3mid;
    ModelRenderer finger3tip;
    ModelRenderer thumbRight;
    ModelRenderer thumbRightTip;
    ModelRenderer thumbLeft;
    ModelRenderer thumbLeftTip;

    public ModelGlove()
    {
        this.textureWidth = 64;
        this.textureHeight = 128;

        this.arm = new ModelRenderer(this, 0, 42);
        this.arm.addBox(-4F, 0F, 0F, 8, 6, 14);
        this.arm.setRotationPoint(0F, 0F, 0F);
        this.arm.setTextureSize(64, 32);
        this.arm.mirror = true;
        this.setRotation(this.arm, 0F, 0F, 0F);

        this.barrel = new ModelRenderer(this, 11, 25);
        this.barrel.addBox(-1F, -1F, -2F, 2, 2, 4);
        this.barrel.setRotationPoint(2.5F, 0.2F, -2.5F);
        this.barrel.setTextureSize(64, 32);
        this.barrel.mirror = true;
        this.setRotation(this.barrel, 0F, 0F, 0F);

        this.barrel2 = new ModelRenderer(this, 11, 25);
        this.barrel2.addBox(-1F, -1F, -2F, 2, 2, 4);
        this.barrel2.setRotationPoint(0F, 0.2F, -2.5F);
        this.barrel2.setTextureSize(64, 32);
        this.barrel2.mirror = true;
        this.setRotation(this.barrel2, 0F, 0F, 0F);

        this.barrel3 = new ModelRenderer(this, 11, 25);
        this.barrel3.addBox(-1F, -1F, -2F, 2, 2, 4);
        this.barrel3.setRotationPoint(-2.5F, 0.2F, -2.5F);
        this.barrel3.setTextureSize(64, 32);
        this.barrel3.mirror = true;
        this.setRotation(this.barrel3, 0F, 0F, 0F);

        this.palm = new ModelRenderer(this, 0, 32);
        this.palm.addBox(-4.5F, -2F, -6F, 9, 4, 6);
        this.palm.setRotationPoint(0F, 2.5F, 0F);
        this.palm.setTextureSize(64, 32);
        this.palm.mirror = true;
        this.setRotation(this.palm, 0F, 0F, 0F);

        this.finger1 = new ModelRenderer(this, 0, 27);
        this.finger1.addBox(-1F, 0F, -3F, 2, 2, 3);
        this.finger1.setRotationPoint(2.5F, 0.6F, -5.8F);
        this.finger1.setTextureSize(64, 32);
        this.finger1.mirror = true;
        this.setRotation(this.finger1, 0.1570796F, 0F, 0F);

        this.finger1.mirror = false;
        this.finger1mid = new ModelRenderer(this, 0, 23);
        this.finger1mid.addBox(-1F, -1.5F, -2F, 2, 2, 2);
        this.finger1mid.setRotationPoint(0F, 1.4F, -2.6F);
        this.finger1mid.setTextureSize(64, 32);
        this.finger1mid.mirror = true;
        this.setRotation(this.finger1mid, (float) Math.toRadians(5.2), 0F, 0F);

        this.finger1tip = new ModelRenderer(this, 0, 19);
        this.finger1tip.addBox(-1F, -1.5F, -2F, 2, 2, 2);
        this.finger1tip.setRotationPoint(0F, -0.2F, -1.7F);
        this.finger1tip.setTextureSize(64, 32);
        this.finger1tip.mirror = true;
        this.setRotation(this.finger1tip, (float) Math.toRadians(0.8), 0F, 0F);

        this.finger1.addChild(this.finger1mid);
        this.finger1mid.addChild(this.finger1tip);

        this.finger2 = new ModelRenderer(this, 0, 27);
        this.finger2.addBox(-1F, 0F, -3F, 2, 2, 3);
        this.finger2.setRotationPoint(0F, 0.6F, -5.8F);
        this.finger2.setTextureSize(64, 32);
        this.finger2.mirror = true;
        this.setRotation(this.finger2, 0.1570796F, 0F, 0F);

        this.finger2mid = new ModelRenderer(this, 0, 23);
        this.finger2mid.addBox(-1F, -1.5F, -2F, 2, 2, 2);
        this.finger2mid.setRotationPoint(0F, 1.4F, -2.6F);
        this.finger2mid.setTextureSize(64, 32);
        this.finger2mid.mirror = true;
        this.setRotation(this.finger2mid, (float) Math.toRadians(5.2), 0F, 0F);

        this.finger2tip = new ModelRenderer(this, 0, 19);
        this.finger2tip.addBox(-1F, -1.5F, -2F, 2, 2, 2);
        this.finger2tip.setRotationPoint(0F, -0.2F, -1.7F);
        this.finger2tip.setTextureSize(64, 32);
        this.finger2tip.mirror = true;
        this.setRotation(this.finger2tip, (float) Math.toRadians(0.8), 0F, 0F);

        this.finger2.addChild(this.finger2mid);
        this.finger2mid.addChild(this.finger2tip);

        this.finger3 = new ModelRenderer(this, 0, 27);
        this.finger3.addBox(-1F, 0F, -3F, 2, 2, 3);
        this.finger3.setRotationPoint(-2.5F, 0.6F, -5.8F);
        this.finger3.setTextureSize(64, 32);
        this.finger3.mirror = true;
        this.setRotation(this.finger3, 0.1570796F, 0F, 0F);

        this.finger3mid = new ModelRenderer(this, 0, 23);
        this.finger3mid.addBox(-1F, -1.5F, -2F, 2, 2, 2);
        this.finger3mid.setRotationPoint(0F, 1.4F, -2.6F);
        this.finger3mid.setTextureSize(64, 32);
        this.finger3mid.mirror = true;
        this.setRotation(this.finger3mid, 0.2478368F, 0F, 0F);

        this.finger3tip = new ModelRenderer(this, 0, 19);
        this.finger3tip.addBox(-1F, -1.5F, -2F, 2, 2, 2);
        this.finger3tip.setRotationPoint(0F, -0.2F, -1.7F);
        this.finger3tip.setTextureSize(64, 32);
        this.finger3tip.mirror = true;
        this.setRotation(this.finger3tip, 0.418879F, 0F, 0F);

        this.finger3.addChild(this.finger3mid);
        this.finger3mid.addChild(this.finger3tip);

        this.thumbRight = new ModelRenderer(this, 0, 15);
        this.thumbRight.addBox(0F, -2F, 0F, 4, 2, 2);
        this.thumbRight.setRotationPoint(3F, 4F, -3F);
        this.thumbRight.setTextureSize(64, 32);
        this.thumbRight.mirror = true;
        this.setRotation(this.thumbRight, 0F, (float) Math.toRadians(49), 0F);

        this.thumbRightTip = new ModelRenderer(this, 0, 11);
        this.thumbRightTip.addBox(0F, -2F, 0F, 3, 2, 2);
        this.thumbRightTip.setRotationPoint(-3F, 0F, 0.2F);
        this.thumbRightTip.setTextureSize(64, 32);
        this.thumbRightTip.mirror = true;
        this.setRotation(this.thumbRightTip, 0F, (float) Math.toRadians(10.4), 0.0546812F);

        this.thumbRight.addChild(this.thumbRightTip);

        this.thumbLeft = new ModelRenderer(this, 0, 15);
        this.thumbLeft.addBox(-4F, -2F, 0F, 4, 2, 2);
        this.thumbLeft.setRotationPoint(-3F, 4F, -3F);
        this.thumbLeft.setTextureSize(64, 32);
        this.thumbLeft.mirror = true;
        this.setRotation(this.thumbLeft, 0F, (float) Math.toRadians(-49), 0F);

        this.thumbLeftTip = new ModelRenderer(this, 0, 11);
        this.thumbLeftTip.addBox(-3.6F, -2F, 0F, 3, 2, 2);
        this.thumbLeftTip.setRotationPoint(-3F, 0F, 0.2F);
        this.thumbLeftTip.setTextureSize(64, 32);
        this.thumbLeftTip.mirror = true;
        this.setRotation(this.thumbLeftTip, 0F, (float) Math.toRadians(-10.4), -0.0546812F);

        this.thumbLeft.addChild(this.thumbLeftTip);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean isFirstPerson)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.arm.render(f5);
        this.barrel.render(f5);
        this.barrel2.render(f5);
        this.barrel3.render(f5);
        this.palm.render(f5);

        this.finger1.render(f5);

        this.finger2.render(f5);

        this.finger3.render(f5);

        if (isFirstPerson)
            this.thumbRight.render(f5);
        else
            this.thumbLeft.render(f5);
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

    public void clenchHand()
    {
        this.finger1.rotateAngleX = (float) Math.toRadians(50);
        this.finger1mid.rotateAngleX = (float) Math.toRadians(90);
        this.finger1tip.rotateAngleX = (float) Math.toRadians(90);

        this.finger2.rotateAngleX = (float) Math.toRadians(50);
        this.finger2mid.rotateAngleX = (float) Math.toRadians(90);
        this.finger2tip.rotateAngleX = (float) Math.toRadians(70);

        this.finger3.rotateAngleX = (float) Math.toRadians(50);
        this.finger3mid.rotateAngleX = (float) Math.toRadians(90);
        this.finger3tip.rotateAngleX = (float) Math.toRadians(90);

        this.thumbRight.rotateAngleZ = (float) Math.toRadians(-23);
        this.thumbRight.rotateAngleX = (float) Math.toRadians(-15);
        this.thumbRightTip.rotateAngleZ = (float) Math.toRadians(-60);

        this.thumbLeft.rotateAngleZ = (float) Math.toRadians(-23);
        this.thumbLeft.rotateAngleX = (float) Math.toRadians(-15);
        this.thumbLeftTip.rotateAngleZ = (float) Math.toRadians(-60);
    }

    public void unclenchHand()
    {
        this.finger1.rotateAngleX = 0.1570796F;
        this.finger1mid.rotateAngleX = 0.2478368F;
        this.finger1tip.rotateAngleX = 0.418879F;

        this.finger2.rotateAngleX = 0.1570796F;
        this.finger2mid.rotateAngleX = 0.2478368F;
        this.finger2tip.rotateAngleX = 0.418879F;

        this.finger3.rotateAngleX = 0.1570796F;
        this.finger3mid.rotateAngleX = 0.2478368F;
        this.finger3tip.rotateAngleX = 0.418879F;

        this.thumbRight.rotateAngleZ = 0F;
        this.thumbRight.rotateAngleX = 0F;
        this.thumbRightTip.rotateAngleZ = (float) Math.toRadians(3.133);

        this.thumbLeft.rotateAngleZ = 0F;
        this.thumbLeft.rotateAngleX = 0F;
        this.thumbLeftTip.rotateAngleZ = (float) Math.toRadians(3.133);
    }
}
