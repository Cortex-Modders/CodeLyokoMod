/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGlove2 extends ModelBase
{
	//fields
	ModelRenderer hand;
	ModelRenderer finger1;
	ModelRenderer finger2;
	ModelRenderer finger3;
	ModelRenderer finger4;

	public ModelGlove2()
	{
		textureWidth = 64;
		textureHeight = 32;

		hand = new ModelRenderer(this, 0, 0);
		hand.addBox(0F, 0F, 0F, 4, 4, 7);
		hand.setRotationPoint(0F, 0F, 0F);
		hand.setTextureSize(64, 32);
		hand.mirror = true;
		setRotation(hand, 0F, 0F, 0F);
		finger1 = new ModelRenderer(this, 0, 0);
		finger1.addBox(0F, 0F, 0F, 1, 3, 1);
		finger1.setRotationPoint(0F, 0F, -1F);
		finger1.setTextureSize(64, 32);
		finger1.mirror = true;
		setRotation(finger1, 0F, 0F, 0F);
		finger2 = new ModelRenderer(this, 0, 0);
		finger2.addBox(0F, 0F, 0F, 1, 3, 1);
		finger2.setRotationPoint(1F, 0F, -1F);
		finger2.setTextureSize(64, 32);
		finger2.mirror = true;
		setRotation(finger2, 0F, 0F, 0F);
		finger3 = new ModelRenderer(this, 0, 0);
		finger3.addBox(0F, 0F, 0F, 1, 3, 1);
		finger3.setRotationPoint(2F, 0F, -1F);
		finger3.setTextureSize(64, 32);
		finger3.mirror = true;
		setRotation(finger3, 0F, 0F, 0F);
		finger4 = new ModelRenderer(this, 0, 0);
		finger4.addBox(0F, 0F, 0F, 1, 3, 1);
		finger4.setRotationPoint(3F, 0F, -1F);
		finger4.setTextureSize(64, 32);
		finger4.mirror = true;
		setRotation(finger4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		hand.render(f5);
		finger1.render(f5);
		finger2.render(f5);
		finger3.render(f5);
		finger4.render(f5);
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
