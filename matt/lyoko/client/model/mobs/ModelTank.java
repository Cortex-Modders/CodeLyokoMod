/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.client.model.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTank extends ModelBase
{
  //fields
    ModelRenderer core;
    ModelRenderer top;
    ModelRenderer bottom;
    ModelRenderer left;
    ModelRenderer right;
    ModelRenderer front;
    ModelRenderer back;
  
  public ModelTank()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      core = new ModelRenderer(this, 0, 0);
      core.addBox(-8F, -8F, -8F, 16, 16, 16);
      core.setRotationPoint(0F, 14F, 0F);
      core.setTextureSize(128, 64);
      core.mirror = true;
      setRotation(core, 0F, 0F, 0F);
      top = new ModelRenderer(this, 64, 0);
      top.addBox(-6F, -10F, -6F, 12, 2, 12);
      top.setRotationPoint(0F, 14F, 0F);
      top.setTextureSize(128, 64);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
      bottom = new ModelRenderer(this, 64, 0);
      bottom.addBox(-6F, 8F, -6F, 12, 2, 12);
      bottom.setRotationPoint(0F, 14F, 0F);
      bottom.setTextureSize(128, 64);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      left = new ModelRenderer(this, 0, 32);
      left.addBox(8F, -6F, -6F, 2, 12, 12);
      left.setRotationPoint(0F, 14F, 0F);
      left.setTextureSize(128, 64);
      left.mirror = true;
      setRotation(left, 0F, 0F, 0F);
      right = new ModelRenderer(this, 0, 32);
      right.addBox(-10F, -6F, -6F, 2, 12, 12);
      right.setRotationPoint(0F, 14F, 0F);
      right.setTextureSize(128, 64);
      right.mirror = true;
      setRotation(right, 0F, 0F, 0F);
      front = new ModelRenderer(this, 64, 14);
      front.addBox(-6F, -6F, -10F, 12, 12, 2);
      front.setRotationPoint(0F, 14F, 0F);
      front.setTextureSize(128, 64);
      front.mirror = true;
      setRotation(front, 0F, 0F, 0F);
      back = new ModelRenderer(this, 64, 14);
      back.addBox(-6F, -6F, 8F, 12, 12, 2);
      back.setRotationPoint(0F, 14F, 0F);
      back.setTextureSize(128, 64);
      back.mirror = true;
      setRotation(back, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    core.render(f5);
    top.render(f5);
    bottom.render(f5);
    left.render(f5);
    right.render(f5);
    front.render(f5);
    back.render(f5);
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
