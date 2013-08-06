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
import net.minecraft.entity.Entity;

public class ModelSuperCalcConsole extends ModelBase
{
	//fields
	ModelRenderer keyboard;
	ModelRenderer mainScreen;
	ModelRenderer rightScreen;
	ModelRenderer leftScreen;
	ModelRenderer topScreen;
	
	public ModelSuperCalcConsole()
	{
		textureWidth = 128;
		textureHeight = 64;
		
		keyboard = new ModelRenderer(this, 10, 20);
		keyboard.addBox(-8F, 0F, -5F, 16, 1, 5);
		keyboard.setRotationPoint(0F, 21F, -2F);
		keyboard.setTextureSize(64, 32);
		keyboard.mirror = true;
		setRotation(keyboard, 0.3490659F, 0F, 0F);
		mainScreen = new ModelRenderer(this, 14, 7);
		mainScreen.addBox(-8F, -12F, 0F, 16, 12, 1);
		mainScreen.setRotationPoint(0F, 21F, -2F);
		mainScreen.setTextureSize(64, 32);
		mainScreen.mirror = true;
		setRotation(mainScreen, -0.3490659F, 0F, 0F);
		rightScreen = new ModelRenderer(this, 48, 7);
		rightScreen.addBox(-6F, -12F, 0F, 6, 12, 1);
		rightScreen.setRotationPoint(-8F, 21F, -2F);
		rightScreen.setTextureSize(64, 32);
		rightScreen.mirror = true;
		setRotation(rightScreen, -0.3385939F, -0.2617994F, 0.0942478F);
		leftScreen = new ModelRenderer(this, 0, 7);
		leftScreen.addBox(0F, -12F, 0F, 6, 12, 1);
		leftScreen.setRotationPoint(8F, 21F, -2F);
		leftScreen.setTextureSize(64, 32);
		leftScreen.mirror = true;
		setRotation(leftScreen, -0.3385939F, 0.2617994F, -0.0942478F);
		topScreen = new ModelRenderer(this, 16, 0);
		topScreen.addBox(-7F, -6F, 0F, 14, 6, 1);
		topScreen.setRotationPoint(0F, 9.7F, 2.11F);
		topScreen.setTextureSize(64, 32);
		topScreen.mirror = true;
		setRotation(topScreen, 0.2617994F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		keyboard.render(f5);
		mainScreen.render(f5);
		rightScreen.render(f5);
		leftScreen.render(f5);
		topScreen.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}
}