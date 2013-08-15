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

import matt.lyoko.entities.tileentity.TileEntityHolomap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelHolomap extends ModelBase
{
	//fields
	ModelRenderer edge1;
	ModelRenderer edge2;
	ModelRenderer floor;
	ModelRenderer edgeInside1;
	ModelRenderer edgeInside2;
	
	public ModelHolomap()
	{
		textureWidth = 64;
		textureHeight = 128;
		
		edge1 = new ModelRenderer(this, 0, 0);
		edge1.addBox(-1F, 0F, -8F, 2, 7, 14);
		edge1.setRotationPoint(-7F, 16F, 0F);
		edge1.setTextureSize(64, 128);
		edge1.mirror = true;
		setRotation(edge1, 0F, 0F, 0F);
		edge2 = new ModelRenderer(this, 0, 21);
		edge2.addBox(-8F, 0F, -1F, 16, 7, 2);
		edge2.setRotationPoint(0F, 16F, 7F);
		edge2.setTextureSize(64, 128);
		edge2.mirror = true;
		setRotation(edge2, 0F, 0F, 0F);
		floor = new ModelRenderer(this, 0, 30);
		floor.addBox(-8F, 0F, -8F, 16, 1, 16);
		floor.setRotationPoint(0F, 23F, 0F);
		floor.setTextureSize(64, 128);
		floor.mirror = true;
		setRotation(floor, 0F, 0F, 0F);
		edgeInside1 = new ModelRenderer(this, 0, 43);
		edgeInside1.addBox(0F, 0F, -7F, 0, 10, 14);
		edgeInside1.setRotationPoint(-6F, 16F, -1F);
		edgeInside1.setTextureSize(64, 128);
		edgeInside1.mirror = true;
		setRotation(edgeInside1, 0F, 0F, -0.8028515F);
		edgeInside2 = new ModelRenderer(this, 0, 47);
		edgeInside2.addBox(-9F, 0F, 0F, 16, 10, 0);
		edgeInside2.setRotationPoint(1F, 16F, 6F);
		edgeInside2.setTextureSize(64, 128);
		edgeInside2.mirror = true;
		setRotation(edgeInside2, -0.8028515F, 0F, 0F);
	}
	
	public void render(TileEntityHolomap entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if(entity != null)
		{
			if(entity.getBlockMetadata() < 4)
			{
				edge1.render(f5);
				edgeInside1.render(f5);
			}
			if(entity.getBlockMetadata() < 8)
			{
				edge2.render(f5);
				edgeInside2.render(f5);
			}
		}
		floor.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, TileEntity ent)
	{
		
	}
}