package matt.lyoko.model.vehicles;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;

public class ModelSkid extends ModelBase
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
  
  public ModelSkid()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      tail2 = new ModelRenderer(this, 0, 0);
      tail2.addBox(-4F, 0F, -4F, 8, 13, 8);
      tail2.setRotationPoint(0F, 11F, 0F);
      tail2.setTextureSize(256, 128);
      tail2.mirror = true;
      setRotation(tail2, 0F, 0F, 0F);
      tail1 = new ModelRenderer(this, 0, 0);
      tail1.addBox(-2F, 0F, -2F, 4, 75, 4);
      tail1.setRotationPoint(0F, -64F, 0F);
      tail1.setTextureSize(256, 128);
      tail1.mirror = true;
      setRotation(tail1, 0F, 0F, 0F);
      skidMoore1pt1 = new ModelRenderer(this, 0, 0);
      skidMoore1pt1.addBox(-2F, 0F, -3F, 4, 8, 6);
      skidMoore1pt1.setRotationPoint(0F, -47F, 0F);
      skidMoore1pt1.setTextureSize(256, 128);
      skidMoore1pt1.mirror = true;
      setRotation(skidMoore1pt1, 0F, 0F, 0F);
      skidMoore1pt2 = new ModelRenderer(this, 0, 0);
      skidMoore1pt2.addBox(-2F, 0F, -3.5F, 4, 6, 7);
      skidMoore1pt2.setRotationPoint(0F, -46F, 0F);
      skidMoore1pt2.setTextureSize(256, 128);
      skidMoore1pt2.mirror = true;
      setRotation(skidMoore1pt2, 0F, 0F, 0F);
      skidMoore2pt1 = new ModelRenderer(this, 0, 0);
      skidMoore2pt1.addBox(-3F, 0F, -2F, 6, 8, 4);
      skidMoore2pt1.setRotationPoint(0F, -16F, 0F);
      skidMoore2pt1.setTextureSize(256, 128);
      skidMoore2pt1.mirror = true;
      setRotation(skidMoore2pt1, 0F, 0F, 0F);
      skidMoore2pt2 = new ModelRenderer(this, 0, 0);
      skidMoore2pt2.addBox(-3.5F, 0F, -2F, 7, 6, 4);
      skidMoore2pt2.setRotationPoint(0F, -15F, 0F);
      skidMoore2pt2.setTextureSize(256, 128);
      skidMoore2pt2.mirror = true;
      setRotation(skidMoore2pt2, 0F, 0F, 0F);
      skidHead1 = new ModelRenderer(this, 0, 0);
      skidHead1.addBox(-6.5F, 0F, -6.5F, 13, 5, 13);
      skidHead1.setRotationPoint(0F, -87F, 0F);
      skidHead1.setTextureSize(256, 128);
      skidHead1.mirror = true;
      setRotation(skidHead1, 0F, 0F, 0F);
      skidHead2 = new ModelRenderer(this, 0, 59);
      skidHead2.addBox(-10F, 0F, -10F, 20, 14, 20);
      skidHead2.setRotationPoint(0F, -82F, 0F);
      skidHead2.setTextureSize(256, 128);
      skidHead2.mirror = true;
      setRotation(skidHead2, 0F, 0F, 0F);
      skidHead3 = new ModelRenderer(this, 0, 0);
      skidHead3.addBox(-6F, 0F, -6F, 12, 4, 12);
      skidHead3.setRotationPoint(0F, -68F, 0F);
      skidHead3.setTextureSize(256, 128);
      skidHead3.mirror = true;
      setRotation(skidHead3, 0F, 0F, 0F);
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
