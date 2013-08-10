/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelSuperCalcConsole extends ModelBase {
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

	public ModelSuperCalcConsole() {
		textureWidth = 128;
		textureHeight = 64;
		
		mainScreen = new ModelRenderer(this, 14, 7);
		mainScreen.addBox(-7.5F, -12F, 0F, 15, 12, 1);
		mainScreen.setRotationPoint(0F, 0F, -2F);
		mainScreen.setTextureSize(64, 32);
		mainScreen.mirror = true;
		setRotation(mainScreen, -0.3490659F, 0F, 0F);
		
		topScreen = new ModelRenderer(this, 17, 0);
		topScreen.addBox(-6F, -6F, 0F, 12, 6, 1);
		topScreen.setRotationPoint(0F, -12.3F, 2.6F);
		topScreen.setTextureSize(64, 32);
		topScreen.mirror = true;
		setRotation(topScreen, 0.2617994F, 0F, 0F);
		
		topScreenHinge = new ModelRenderer(this, 0, 2);
		topScreenHinge.addBox(-1.5F, -2F, 0F, 3, 2, 1);
		topScreenHinge.setRotationPoint(0F, -11F, 2.3F);
		topScreenHinge.setTextureSize(64, 32);
		topScreenHinge.mirror = true;
		setRotation(topScreenHinge, -0.221075F, 0F, 0F);
		
		leftScreen = new ModelRenderer(this, 46, 7);
		leftScreen.addBox(0F, -5F, 0F, 6, 10, 1);
		leftScreen.setRotationPoint(8F, -6F, 0F);
		leftScreen.setTextureSize(64, 32);
		leftScreen.mirror = true;
		setRotation(leftScreen, -0.3490659F, 0.2617994F, -0.0942478F);
		
		leftScreenTopHinge = new ModelRenderer(this, 0, 0);
		leftScreenTopHinge.addBox(-2F, -0.5F, 0F, 2, 1, 1);
		leftScreenTopHinge.setRotationPoint(-6.733333F, -9.066667F, 1.6F);
		leftScreenTopHinge.setTextureSize(64, 32);
		leftScreenTopHinge.mirror = true;
		setRotation(leftScreenTopHinge, -0.3490659F, -0.1605703F, 0.0674861F);
		
		leftScreenBottomHinge = new ModelRenderer(this, 0, 0);
		leftScreenBottomHinge.addBox(-2F, -0.5F, -0.9F, 2, 1, 1);
		leftScreenBottomHinge.setRotationPoint(-6.733333F, -2.066667F, 0F);
		leftScreenBottomHinge.setTextureSize(64, 32);
		leftScreenBottomHinge.mirror = true;
		setRotation(leftScreenBottomHinge, -0.3490659F, -0.1605703F, 0.0674861F);
		
		
		rightScreen = new ModelRenderer(this, 0, 7);
		rightScreen.addBox(-6F, -5F, 0F, 6, 10, 1);
		rightScreen.setRotationPoint(-8F, -6F, 0F);
		rightScreen.setTextureSize(64, 32);
		rightScreen.mirror = true;
		setRotation(rightScreen, -0.3490659F, -0.2617994F, 0.0942478F);
		
		rightScreenTopHinge = new ModelRenderer(this, 0, 0);
		rightScreenTopHinge.addBox(0F, -0.5F, 0F, 2, 1, 1);
		rightScreenTopHinge.setRotationPoint(6.7F, -9.066667F, 1.6F);
		rightScreenTopHinge.setTextureSize(64, 32);
		rightScreenTopHinge.mirror = true;
		setRotation(rightScreenTopHinge, -0.3665191F, 0.1605703F, -0.0674919F);
		
		rightScreenBottomHinge = new ModelRenderer(this, 0, 0);
		rightScreenBottomHinge.addBox(0F, -0.5F, -0.9F, 2, 1, 1);
		rightScreenBottomHinge.setRotationPoint(6.7F, -2.066667F, 0F);
		rightScreenBottomHinge.setTextureSize(64, 32);
		rightScreenBottomHinge.mirror = true;
		setRotation(rightScreenBottomHinge, -0.3490659F, 0.1605703F, -0.0674919F);
		
		keyboard = new ModelRenderer(this, 10, 20);
		keyboard.addBox(-7F, 0F, -5F, 14, 1, 5);
		keyboard.setRotationPoint(0F, 0.5F, -2.3F);
		keyboard.setTextureSize(64, 32);
		keyboard.mirror = true;
		setRotation(keyboard, 0.1396263F, 0F, 0F);
		
		keyboardHingeRightTop = new ModelRenderer(this, 7, 0);
		keyboardHingeRightTop.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		keyboardHingeRightTop.setRotationPoint(-5F, -1.1F, -1.1F);
		keyboardHingeRightTop.setTextureSize(128, 64);
		keyboardHingeRightTop.mirror = true;
		setRotation(keyboardHingeRightTop, -0.3490659F, 0F, 0F);
		
		keyboardHingeRightMid = new ModelRenderer(this, 7, 0);
		keyboardHingeRightMid.addBox(-0.5F, 0F, 0F, 1, 2, 1);
		keyboardHingeRightMid.setRotationPoint(-5F, -0.5F, -1.3F);
		keyboardHingeRightMid.setTextureSize(128, 64);
		keyboardHingeRightMid.mirror = true;
		setRotation(keyboardHingeRightMid, -0.7423467F, 0F, 0F);
		
		keyboardHingeRightBottom = new ModelRenderer(this, 7, 0);
		keyboardHingeRightBottom.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		keyboardHingeRightBottom.setRotationPoint(-5F, 0.8F, -3F);
		keyboardHingeRightBottom.setTextureSize(128, 64);
		keyboardHingeRightBottom.mirror = true;
		setRotation(keyboardHingeRightBottom, 0.1396263F, 0F, 0F);
		
		keyboardHingeLeftTop = new ModelRenderer(this, 7, 0);
		keyboardHingeLeftTop.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		keyboardHingeLeftTop.setRotationPoint(5F, -1.1F, -1.1F);
		keyboardHingeLeftTop.setTextureSize(128, 64);
		keyboardHingeLeftTop.mirror = true;
		setRotation(keyboardHingeLeftTop, -0.3490659F, 0F, 0F);
		
		keyboardHingeLeftMid = new ModelRenderer(this, 7, 0);
		keyboardHingeLeftMid.addBox(-0.5F, 0F, 0F, 1, 2, 1);
		keyboardHingeLeftMid.setRotationPoint(5F, -0.5F, -1.3F);
		keyboardHingeLeftMid.setTextureSize(128, 64);
		keyboardHingeLeftMid.mirror = true;
		setRotation(keyboardHingeLeftMid, -0.7423467F, 0F, 0F);
		
		keyboardHingeLeftBottom = new ModelRenderer(this, 7, 0);
		keyboardHingeLeftBottom.addBox(-0.5F, 0F, 0F, 1, 1, 1);
		keyboardHingeLeftBottom.setRotationPoint(5F, 0.8F, -3F);
		keyboardHingeLeftBottom.setTextureSize(128, 64);
		keyboardHingeLeftBottom.mirror = true;
		setRotation(keyboardHingeLeftBottom, 0.1396263F, 0F, 0F);
	}

	public void render(TileEntity entity, float par1, float par2, float par3, float par4, float par5, float partialTick) {
		mainScreen.render(partialTick);
		
		topScreen.render(partialTick);
		topScreenHinge.render(partialTick);
		
		leftScreen.render(partialTick);
		leftScreenTopHinge.render(partialTick);
		leftScreenBottomHinge.render(partialTick);
		
		rightScreen.render(partialTick);
		rightScreenTopHinge.render(partialTick);
		rightScreenBottomHinge.render(partialTick);
		
		keyboard.render(partialTick);
		
		keyboardHingeRightTop.render(partialTick);
		keyboardHingeRightMid.render(partialTick);
		keyboardHingeRightBottom.render(partialTick);
		
		keyboardHingeLeftTop.render(partialTick);
		keyboardHingeLeftMid.render(partialTick);
		keyboardHingeLeftBottom.render(partialTick);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		//    super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

}
