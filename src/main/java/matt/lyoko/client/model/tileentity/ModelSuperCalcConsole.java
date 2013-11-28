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
import net.minecraft.tileentity.TileEntity;

public class ModelSuperCalcConsole extends ModelBase
{
    ModelRenderer mainScreen;

    ModelRenderer topScreen;
    ModelRenderer topScreenHinge;

    ModelRenderer leftScreen;
    ModelRenderer leftScreenBottomHinge;
    ModelRenderer leftScreenTopHinge;

    ModelRenderer rightScreen;
    ModelRenderer rightScreenBottomHinge;
    ModelRenderer rightScreenTopHinge;

    ModelRenderer keyboard;
    ModelRenderer keyboardHingeRightTop;
    ModelRenderer keyboardHingeRightMid;
    ModelRenderer keyboardHingeRightBottom;

    ModelRenderer keyboardHingeLeftTop;
    ModelRenderer keyboardHingeLeftMid;
    ModelRenderer keyboardHingeLeftBottom;

    public ModelSuperCalcConsole()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;

        this.mainScreen = new ModelRenderer(this, 14, 7);
        this.mainScreen.addBox(-7.5F, -12F, 0F, 15, 12, 1);
        this.mainScreen.setRotationPoint(0F, 0F, -2F);
        this.mainScreen.setTextureSize(64, 32);
        this.mainScreen.mirror = true;
        this.setRotation(this.mainScreen, -0.3490659F, 0F, 0F);

        this.topScreen = new ModelRenderer(this, 17, 0);
        this.topScreen.addBox(-6F, -6F, 0F, 12, 6, 1);
        this.topScreen.setRotationPoint(0F, -12.3F, 2.6F);
        this.topScreen.setTextureSize(64, 32);
        this.topScreen.mirror = true;
        this.setRotation(this.topScreen, 0.2617994F, 0F, 0F);

        this.topScreenHinge = new ModelRenderer(this, 0, 2);
        this.topScreenHinge.addBox(-1.5F, -2F, 0F, 3, 2, 1);
        this.topScreenHinge.setRotationPoint(0F, -11F, 2.3F);
        this.topScreenHinge.setTextureSize(64, 32);
        this.topScreenHinge.mirror = true;
        this.setRotation(this.topScreenHinge, -0.221075F, 0F, 0F);

        this.leftScreen = new ModelRenderer(this, 46, 7);
        this.leftScreen.addBox(0F, -5F, 0F, 6, 10, 1);
        this.leftScreen.setRotationPoint(8F, -6F, 0F);
        this.leftScreen.setTextureSize(64, 32);
        this.leftScreen.mirror = true;
        this.setRotation(this.leftScreen, -0.3490659F, 0.2617994F, -0.0942478F);

        this.leftScreenTopHinge = new ModelRenderer(this, 0, 0);
        this.leftScreenTopHinge.addBox(-2F, -0.5F, 0F, 2, 1, 1);
        this.leftScreenTopHinge.setRotationPoint(-6.733333F, -9.066667F, 1.6F);
        this.leftScreenTopHinge.setTextureSize(64, 32);
        this.leftScreenTopHinge.mirror = true;
        this.setRotation(this.leftScreenTopHinge, -0.3490659F, -0.1605703F, 0.0674861F);

        this.leftScreenBottomHinge = new ModelRenderer(this, 0, 0);
        this.leftScreenBottomHinge.addBox(-2F, -0.5F, -0.9F, 2, 1, 1);
        this.leftScreenBottomHinge.setRotationPoint(-6.733333F, -2.066667F, 0F);
        this.leftScreenBottomHinge.setTextureSize(64, 32);
        this.leftScreenBottomHinge.mirror = true;
        this.setRotation(this.leftScreenBottomHinge, -0.3490659F, -0.1605703F, 0.0674861F);

        this.rightScreen = new ModelRenderer(this, 0, 7);
        this.rightScreen.addBox(-6F, -5F, 0F, 6, 10, 1);
        this.rightScreen.setRotationPoint(-8F, -6F, 0F);
        this.rightScreen.setTextureSize(64, 32);
        this.rightScreen.mirror = true;
        this.setRotation(this.rightScreen, -0.3490659F, -0.2617994F, 0.0942478F);

        this.rightScreenTopHinge = new ModelRenderer(this, 0, 0);
        this.rightScreenTopHinge.addBox(0F, -0.5F, 0F, 2, 1, 1);
        this.rightScreenTopHinge.setRotationPoint(6.7F, -9.066667F, 1.6F);
        this.rightScreenTopHinge.setTextureSize(64, 32);
        this.rightScreenTopHinge.mirror = true;
        this.setRotation(this.rightScreenTopHinge, -0.3665191F, 0.1605703F, -0.0674919F);

        this.rightScreenBottomHinge = new ModelRenderer(this, 0, 0);
        this.rightScreenBottomHinge.addBox(0F, -0.5F, -0.9F, 2, 1, 1);
        this.rightScreenBottomHinge.setRotationPoint(6.7F, -2.066667F, 0F);
        this.rightScreenBottomHinge.setTextureSize(64, 32);
        this.rightScreenBottomHinge.mirror = true;
        this.setRotation(this.rightScreenBottomHinge, -0.3490659F, 0.1605703F, -0.0674919F);

        this.keyboard = new ModelRenderer(this, 10, 20);
        this.keyboard.addBox(-7F, 0F, -5F, 14, 1, 5);
        this.keyboard.setRotationPoint(0F, 0.5F, -2.3F);
        this.keyboard.setTextureSize(64, 32);
        this.keyboard.mirror = true;
        this.setRotation(this.keyboard, 0.1396263F, 0F, 0F);

        this.keyboardHingeRightTop = new ModelRenderer(this, 7, 0);
        this.keyboardHingeRightTop.addBox(-0.5F, 0F, 0F, 1, 1, 1);
        this.keyboardHingeRightTop.setRotationPoint(-5F, -1.1F, -1.1F);
        this.keyboardHingeRightTop.setTextureSize(128, 64);
        this.keyboardHingeRightTop.mirror = true;
        this.setRotation(this.keyboardHingeRightTop, -0.3490659F, 0F, 0F);

        this.keyboardHingeRightMid = new ModelRenderer(this, 7, 0);
        this.keyboardHingeRightMid.addBox(-0.5F, 0F, 0F, 1, 2, 1);
        this.keyboardHingeRightMid.setRotationPoint(-5F, -0.5F, -1.3F);
        this.keyboardHingeRightMid.setTextureSize(128, 64);
        this.keyboardHingeRightMid.mirror = true;
        this.setRotation(this.keyboardHingeRightMid, -0.7423467F, 0F, 0F);

        this.keyboardHingeRightBottom = new ModelRenderer(this, 7, 0);
        this.keyboardHingeRightBottom.addBox(-0.5F, 0F, 0F, 1, 1, 1);
        this.keyboardHingeRightBottom.setRotationPoint(-5F, 0.8F, -3F);
        this.keyboardHingeRightBottom.setTextureSize(128, 64);
        this.keyboardHingeRightBottom.mirror = true;
        this.setRotation(this.keyboardHingeRightBottom, 0.1396263F, 0F, 0F);

        this.keyboardHingeLeftTop = new ModelRenderer(this, 7, 0);
        this.keyboardHingeLeftTop.addBox(-0.5F, 0F, 0F, 1, 1, 1);
        this.keyboardHingeLeftTop.setRotationPoint(5F, -1.1F, -1.1F);
        this.keyboardHingeLeftTop.setTextureSize(128, 64);
        this.keyboardHingeLeftTop.mirror = true;
        this.setRotation(this.keyboardHingeLeftTop, -0.3490659F, 0F, 0F);

        this.keyboardHingeLeftMid = new ModelRenderer(this, 7, 0);
        this.keyboardHingeLeftMid.addBox(-0.5F, 0F, 0F, 1, 2, 1);
        this.keyboardHingeLeftMid.setRotationPoint(5F, -0.5F, -1.3F);
        this.keyboardHingeLeftMid.setTextureSize(128, 64);
        this.keyboardHingeLeftMid.mirror = true;
        this.setRotation(this.keyboardHingeLeftMid, -0.7423467F, 0F, 0F);

        this.keyboardHingeLeftBottom = new ModelRenderer(this, 7, 0);
        this.keyboardHingeLeftBottom.addBox(-0.5F, 0F, 0F, 1, 1, 1);
        this.keyboardHingeLeftBottom.setRotationPoint(5F, 0.8F, -3F);
        this.keyboardHingeLeftBottom.setTextureSize(128, 64);
        this.keyboardHingeLeftBottom.mirror = true;
        this.setRotation(this.keyboardHingeLeftBottom, 0.1396263F, 0F, 0F);
    }

    public void render(TileEntity entity, float par1, float par2, float par3, float par4, float par5, float partialTick)
    {
        this.mainScreen.render(partialTick);

        this.topScreen.render(partialTick);
        this.topScreenHinge.render(partialTick);

        this.leftScreen.render(partialTick);
        this.leftScreenTopHinge.render(partialTick);
        this.leftScreenBottomHinge.render(partialTick);

        this.rightScreen.render(partialTick);
        this.rightScreenTopHinge.render(partialTick);
        this.rightScreenBottomHinge.render(partialTick);

        this.keyboard.render(partialTick);

        this.keyboardHingeRightTop.render(partialTick);
        this.keyboardHingeRightMid.render(partialTick);
        this.keyboardHingeRightBottom.render(partialTick);

        this.keyboardHingeLeftTop.render(partialTick);
        this.keyboardHingeLeftMid.render(partialTick);
        this.keyboardHingeLeftBottom.render(partialTick);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        // super.setRotationAngles(f, f1, f2, f3, f4, f5);
    }

}
