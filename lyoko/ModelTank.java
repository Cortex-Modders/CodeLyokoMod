package matt.lyoko;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.src.*;

public class ModelTank extends ModelBase
{
  //fields
    ModelRenderer megatank;
  
  public ModelTank()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      megatank = new ModelRenderer(this, 0, 0);
      megatank.addBox(0F, 0F, 0F, 16, 16, 16);
      megatank.setRotationPoint(-8F, 0F, -8F);
      megatank.setTextureSize(64, 32);
      megatank.mirror = true;
      setRotation(megatank, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    megatank.render(f5);
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

}
