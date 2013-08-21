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

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;

public class ModelCable extends ModelBase
{
	//fields
	ModelRenderer cableCore;
	ModelRenderer cableTop;
	ModelRenderer cableBottom;
	ModelRenderer cableRight;
	ModelRenderer cableLeft;
	ModelRenderer cableBack;
	ModelRenderer cableFront;
	
	public ModelCable()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		cableCore = new ModelRenderer(this, 0, 0);
		cableCore.addBox(-3F, 0F, -3F, 6, 6, 6);
		cableCore.setRotationPoint(0F, 13F, 0F);
		cableCore.setTextureSize(64, 32);
		cableCore.mirror = true;
		setRotation(cableCore, 0F, 0F, 0F);
		cableTop = new ModelRenderer(this, 24, 0);
		cableTop.addBox(-3F, 0F, -3F, 6, 5, 6);
		cableTop.setRotationPoint(0F, 8F, 0F);
		cableTop.setTextureSize(64, 32);
		cableTop.mirror = true;
		setRotation(cableTop, 0F, 0F, 0F);
		cableBottom = new ModelRenderer(this, 24, 0);
		cableBottom.addBox(-3F, 0F, -3F, 6, 5, 6);
		cableBottom.setRotationPoint(0F, 19F, 0F);
		cableBottom.setTextureSize(64, 32);
		cableBottom.mirror = true;
		setRotation(cableBottom, 0F, 0F, 0F);
		cableRight = new ModelRenderer(this, 0, 12);
		cableRight.addBox(-3F, 0F, -3F, 5, 6, 6);
		cableRight.setRotationPoint(-5F, 13F, 0F);
		cableRight.setTextureSize(64, 32);
		cableRight.mirror = true;
		setRotation(cableRight, 0F, 0F, 0F);
		cableLeft = new ModelRenderer(this, 0, 12);
		cableLeft.addBox(-2F, 0F, -3F, 5, 6, 6);
		cableLeft.setRotationPoint(5F, 13F, 0F);
		cableLeft.setTextureSize(64, 32);
		cableLeft.mirror = true;
		setRotation(cableLeft, 0F, 0F, 0F);
		cableBack = new ModelRenderer(this, 24, 11);
		cableBack.addBox(-3F, 0F, -2F, 6, 6, 5);
		cableBack.setRotationPoint(0F, 13F, 5F);
		cableBack.setTextureSize(64, 32);
		cableBack.mirror = true;
		setRotation(cableBack, 0F, 0F, 0F);
		cableFront = new ModelRenderer(this, 24, 11);
		cableFront.addBox(-3F, 0F, -3F, 6, 6, 5);
		cableFront.setRotationPoint(0F, 13F, -5F);
		cableFront.setTextureSize(64, 32);
		cableFront.mirror = true;
		setRotation(cableFront, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		cableCore.render(f5);
		cableTop.render(f5);
		cableBottom.render(f5);
		cableRight.render(f5);
		cableLeft.render(f5);
		cableBack.render(f5);
		cableFront.render(f5);
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
	
	public void renderModel(float f, boolean top, boolean bottom, boolean left, boolean right, boolean front, boolean back)
	{
		cableCore.render(f);
		if(top)
		cableTop.render(f);
		if(bottom)
		cableBottom.render(f);
		if(right)
		cableRight.render(f);
		if(left)
		cableLeft.render(f);
		if(back)
		cableBack.render(f);
		if(front)
		cableFront.render(f);
	}
}