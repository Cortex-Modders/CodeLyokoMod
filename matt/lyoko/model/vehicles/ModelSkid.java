package matt.lyoko.model.vehicles;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSkid extends ModelVehicle
{
	//fields
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
		textureWidth = 256;
		textureHeight = 128;

		tail2 = new ModelRenderer(this, 0, 0);
		tail2.addBox(-4F, 0F, -4F, 8, 13, 8);
		tail2.setRotationPoint(0F, 11F, 0F);
		tail2.setTextureSize(64, 32);
		tail2.mirror = true;
		setRotation(tail2, 0F, 0F, 0F);
		tail1 = new ModelRenderer(this, 0, 0);
		tail1.addBox(-2F, 0F, -2F, 4, 75, 4);
		tail1.setRotationPoint(0F, -64F, 0F);
		tail1.setTextureSize(64, 32);
		tail1.mirror = true;
		setRotation(tail1, 0F, 0F, 0F);
		skidMoore1pt1 = new ModelRenderer(this, 0, 0);
		skidMoore1pt1.addBox(-3F, 0F, -2F, 6, 8, 4);
		skidMoore1pt1.setRotationPoint(0F, -47F, 0F);
		skidMoore1pt1.setTextureSize(64, 32);
		skidMoore1pt1.mirror = true;
		setRotation(skidMoore1pt1, 0F, 0F, 0F);
		skidMoore1pt2 = new ModelRenderer(this, 0, 0);
		skidMoore1pt2.addBox(-3.5F, 0F, -2F, 7, 6, 4);
		skidMoore1pt2.setRotationPoint(0F, -46F, 0F);
		skidMoore1pt2.setTextureSize(64, 32);
		skidMoore1pt2.mirror = true;
		setRotation(skidMoore1pt2, 0F, 0F, 0F);
		skidMoore2pt1 = new ModelRenderer(this, 0, 0);
		skidMoore2pt1.addBox(-2F, 0F, -3F, 4, 8, 6);
		skidMoore2pt1.setRotationPoint(0F, -16F, 0F);
		skidMoore2pt1.setTextureSize(64, 32);
		skidMoore2pt1.mirror = true;
		setRotation(skidMoore2pt1, 0F, 0F, 0F);
		skidMoore2pt2 = new ModelRenderer(this, 0, 0);
		skidMoore2pt2.addBox(-2F, 0F, -3.5F, 4, 6, 7);
		skidMoore2pt2.setRotationPoint(0F, -15F, 0F);
		skidMoore2pt2.setTextureSize(64, 32);
		skidMoore2pt2.mirror = true;
		setRotation(skidMoore2pt2, 0F, 0F, 0F);
		skidHead1 = new ModelRenderer(this, 0, 0);
		skidHead1.addBox(-6.5F, 0F, -6.5F, 13, 5, 13);
		skidHead1.setRotationPoint(0F, -87F, 0F);
		skidHead1.setTextureSize(64, 32);
		skidHead1.mirror = true;
		setRotation(skidHead1, 0F, 0F, 0F);
		skidHead2 = new ModelRenderer(this, 0, 59);
		skidHead2.addBox(-10F, 0F, -10F, 20, 14, 20);
		skidHead2.setRotationPoint(0F, -82F, 0F);
		skidHead2.setTextureSize(64, 32);
		skidHead2.mirror = true;
		setRotation(skidHead2, 0F, 0F, 0F);
		skidHead3 = new ModelRenderer(this, 0, 0);
		skidHead3.addBox(-6F, 0F, -6F, 12, 4, 12);
		skidHead3.setRotationPoint(0F, -68F, 0F);
		skidHead3.setTextureSize(64, 32);
		skidHead3.mirror = true;
		setRotation(skidHead3, 0F, 0F, 0F);
		armLeftTop = new ModelRenderer(this, 52, 8);
		armLeftTop.addBox(0F, -3F, -2.5F, 19, 3, 5);
		armLeftTop.setRotationPoint(7.3F, -74F, 0F);
		armLeftTop.setTextureSize(64, 32);
		armLeftTop.mirror = true;
		setRotation(armLeftTop, 0F, 0F, 1.099557F);
		armLeftMid = new ModelRenderer(this, 52, 23);
		armLeftMid.addBox(0F, -3F, -2F, 15, 3, 4);
		armLeftMid.setRotationPoint(15.4F, -58.4F, 0F);
		armLeftMid.setTextureSize(64, 32);
		armLeftMid.mirror = true;
		setRotation(armLeftMid, 0F, 0F, 1.53589F);
		armLeftBot = new ModelRenderer(this, 52, 35);
		armLeftBot.addBox(0F, -2F, -1.5F, 16, 2, 3);
		armLeftBot.setRotationPoint(16.4F, -43.8F, 0F);
		armLeftBot.setTextureSize(64, 32);
		armLeftBot.mirror = true;
		setRotation(armLeftBot, 0F, 0F, 1.675516F);
		armRightTop = new ModelRenderer(this, 52, 0);
		armRightTop.addBox(0F, 0F, -2.5F, 19, 3, 5);
		armRightTop.setRotationPoint(-7.3F, -74F, 0F);
		armRightTop.setTextureSize(64, 32);
		armRightTop.mirror = true;
		setRotation(armRightTop, 0F, 0F, 2.042035F);
		armRightMid = new ModelRenderer(this, 52, 16);
		armRightMid.addBox(0F, 0F, -2F, 15, 3, 4);
		armRightMid.setRotationPoint(-15.4F, -58.4F, 0F);
		armRightMid.setTextureSize(64, 32);
		armRightMid.mirror = true;
		setRotation(armRightMid, 0F, 0F, 1.605703F);
		armRightBot = new ModelRenderer(this, 52, 30);
		armRightBot.addBox(0F, 0F, -1.5F, 16, 2, 3);
		armRightBot.setRotationPoint(-16.4F, -43.8F, 0F);
		armRightBot.setTextureSize(64, 32);
		armRightBot.mirror = true;
		setRotation(armRightBot, 0F, 0F, 1.466077F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		tail2.render(f5);
		tail1.render(f5);
		skidMoore1pt1.render(f5);
		skidMoore1pt2.render(f5);
		skidMoore2pt1.render(f5);
		skidMoore2pt2.render(f5);
		skidHead1.render(f5);
		skidHead2.render(f5);
		skidHead3.render(f5);
		armLeftTop.render(f5);
		armLeftMid.render(f5);
		armLeftBot.render(f5);
		armRightTop.render(f5);
		armRightMid.render(f5);
		armRightBot.render(f5);
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
	}

	public void doAnimation() {

	}
}
