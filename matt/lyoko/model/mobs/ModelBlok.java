package matt.lyoko.model.mobs;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBlok extends ModelBase
{
	//fields
	ModelRenderer Body;
	ModelRenderer body_joint;
	ModelRenderer leg_1;
	ModelRenderer leg_2;
	ModelRenderer leg_3;
	ModelRenderer leg_4;
	ModelRenderer leg_5;
	ModelRenderer leg_6;
	ModelRenderer sub_leg_1;
	ModelRenderer sub_leg_2;
	ModelRenderer sub_leg_3;
	ModelRenderer sub_leg_4;
	ModelRenderer sub_leg_5;
	ModelRenderer sub_leg_6;
	
	public ModelBlok()
	{
		textureWidth = 69;
		textureHeight = 30;
		
		Body = new ModelRenderer(this, 9, 0);
		Body.addBox(-7.5F, 0F, -7.5F, 15, 15, 15);
		Body.setRotationPoint(0F, 1F, 0F);
		Body.setTextureSize(69, 30);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		body_joint = new ModelRenderer(this, 0, 0);
		body_joint.addBox(-3F, 15F, -3F, 6, 2, 6);
		body_joint.setRotationPoint(0F, 1F, 0F);
		body_joint.setTextureSize(69, 30);
		body_joint.mirror = true;
		setRotation(body_joint, 0F, 0F, 0F);
		leg_1 = new ModelRenderer(this, 0, 0);
		leg_1.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
		leg_1.setRotationPoint(2F, 17.5F, -2.5F);
		leg_1.setTextureSize(69, 30);
		leg_1.mirror = true;
		setRotation(leg_1, 0F, 0F, 0.8552113F);
		leg_2 = new ModelRenderer(this, 0, 0);
		leg_2.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
		leg_2.setRotationPoint(2F, 17.5F, 0F);
		leg_2.setTextureSize(69, 30);
		leg_2.mirror = true;
		setRotation(leg_2, 0F, 0F, 0.8552113F);
		leg_3 = new ModelRenderer(this, 0, 0);
		leg_3.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
		leg_3.setRotationPoint(2F, 17.5F, 2.5F);
		leg_3.setTextureSize(69, 30);
		leg_3.mirror = true;
		setRotation(leg_3, 0F, 0F, 0.8552113F);
		leg_4 = new ModelRenderer(this, 0, 0);
		leg_4.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
		leg_4.setRotationPoint(-2F, 17.5F, -2.5F);
		leg_4.setTextureSize(69, 30);
		leg_4.mirror = true;
		setRotation(leg_4, 0F, 0F, 2.286486F);
		leg_5 = new ModelRenderer(this, 0, 0);
		leg_5.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
		leg_5.setRotationPoint(-2F, 17.5F, 0F);
		leg_5.setTextureSize(69, 30);
		leg_5.mirror = true;
		setRotation(leg_5, 0F, 0F, 2.286486F);
		leg_6 = new ModelRenderer(this, 0, 0);
		leg_6.addBox(0F, -0.5F, -0.5F, 6, 1, 1);
		leg_6.setRotationPoint(-2F, 17.5F, 2.5F);
		leg_6.setTextureSize(69, 30);
		leg_6.mirror = true;
		setRotation(leg_6, 0F, 0F, 2.286486F);
		sub_leg_1 = new ModelRenderer(this, 0, 0);
		sub_leg_1.addBox(3.5F, 3.5F, -0.5F, 1, 3, 1);
		sub_leg_1.setRotationPoint(2F, 17.5F, -2.5F);
		sub_leg_1.setTextureSize(69, 30);
		sub_leg_1.mirror = true;
		setRotation(sub_leg_1, 0F, 0F, 0F);
		sub_leg_2 = new ModelRenderer(this, 0, 0);
		sub_leg_2.addBox(3.5F, 3.5F, -0.5F, 1, 3, 1);
		sub_leg_2.setRotationPoint(2F, 17.5F, 0F);
		sub_leg_2.setTextureSize(69, 30);
		sub_leg_2.mirror = true;
		setRotation(sub_leg_2, 0F, 0F, 0F);
		sub_leg_3 = new ModelRenderer(this, 0, 0);
		sub_leg_3.addBox(3.5F, 3.5F, -0.5F, 1, 3, 1);
		sub_leg_3.setRotationPoint(2F, 17.5F, 2.5F);
		sub_leg_3.setTextureSize(69, 30);
		sub_leg_3.mirror = true;
		setRotation(sub_leg_3, 0F, 0F, 0F);
		sub_leg_4 = new ModelRenderer(this, 0, 0);
		sub_leg_4.addBox(-4.5F, 3.5F, -0.5F, 1, 3, 1);
		sub_leg_4.setRotationPoint(-2F, 17.5F, -2.5F);
		sub_leg_4.setTextureSize(69, 30);
		sub_leg_4.mirror = true;
		setRotation(sub_leg_4, 0F, 0F, 0F);
		sub_leg_5 = new ModelRenderer(this, 0, 0);
		sub_leg_5.addBox(-4.5F, 3.5F, -0.5F, 1, 3, 1);
		sub_leg_5.setRotationPoint(-2F, 17.5F, 0F);
		sub_leg_5.setTextureSize(69, 30);
		sub_leg_5.mirror = true;
		setRotation(sub_leg_5, 0F, 0F, 0F);
		sub_leg_6 = new ModelRenderer(this, 0, 0);
		sub_leg_6.addBox(-4.5F, 3.5F, -0.5F, 1, 3, 1);
		sub_leg_6.setRotationPoint(-2F, 17.5F, 2.5F);
		sub_leg_6.setTextureSize(69, 30);
		sub_leg_6.mirror = true;
		setRotation(sub_leg_6, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		body_joint.render(f5);
		leg_1.render(f5);
		leg_2.render(f5);
		leg_3.render(f5);
		leg_4.render(f5);
		leg_5.render(f5);
		leg_6.render(f5);
		sub_leg_1.render(f5);
		sub_leg_2.render(f5);
		sub_leg_3.render(f5);
		sub_leg_4.render(f5);
		sub_leg_5.render(f5);
		sub_leg_6.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		this.Body.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.leg_1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.sub_leg_1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.leg_2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		this.sub_leg_2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		
		this.leg_3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.sub_leg_3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.leg_4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		this.sub_leg_4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		
		this.leg_5.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.sub_leg_5.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		this.leg_6.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		this.sub_leg_6.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
		//this.leg_4.rotateAngleZ = 0.0F;
		//this.sub_leg_4.rotateAngleZ = 0.0F;
		//this.leg_1.rotateAngleZ = 0.0F;
		//this.sub_leg_1.rotateAngleZ = 0.0F;
		//this.LegRight.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		//this.LegLeft.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
		//this.LegRight.rotateAngleY = 0.0F;
		//this.LegLeft.rotateAngleY = 0.0F;
	}
}
