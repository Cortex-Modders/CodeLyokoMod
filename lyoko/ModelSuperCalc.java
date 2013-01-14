package matt.lyoko;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;

public class ModelSuperCalc extends ModelBase
{
  //fields
    ModelRenderer SuperCalcBase;
    ModelRenderer SuperCalcTower;
  
  public ModelSuperCalc()
  {
    textureWidth = 256;
    textureHeight = 64;
    
      SuperCalcBase = new ModelRenderer(this, 0, 0);
      SuperCalcBase.addBox(0F, 0F, 0F, 48, 16, 48);
      SuperCalcBase.setRotationPoint(-24F, 8F, -24F);
      SuperCalcBase.setTextureSize(256, 64);
      SuperCalcBase.mirror = true;
      setRotation(SuperCalcBase, 0F, 0F, 0F);
      SuperCalcTower = new ModelRenderer(this, 192, 0);
      SuperCalcTower.addBox(0F, 0F, 0F, 16, 32, 16);
      SuperCalcTower.setRotationPoint(-8F, -24F, -8F);
      SuperCalcTower.setTextureSize(256, 64);
      SuperCalcTower.mirror = true;
      setRotation(SuperCalcTower, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    SuperCalcBase.render(f5);
    SuperCalcTower.render(f5);
  }
  
  //public void renderModel(float f1)
  //{
  //	  SuperCalcBase.render(f1);
  //	  SuperCalcTower.render(f1);
  //}
  
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
