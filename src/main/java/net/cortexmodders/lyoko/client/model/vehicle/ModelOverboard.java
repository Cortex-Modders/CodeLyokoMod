/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */
package net.cortexmodders.lyoko.client.model.vehicle;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOverboard extends ModelVehicle
{
    // fields
    ModelRenderer base;
    ModelRenderer rightEngine;
    ModelRenderer leftEngine;
    ModelRenderer baseFront2;
    ModelRenderer baseFront1;
    ModelRenderer baseMid;
    ModelRenderer baseBack;
    ModelRenderer baseRear1;
    ModelRenderer baseRear2;
    ModelRenderer baseRear3;
    ModelRenderer baseRear4;
    
    public ModelOverboard()
    {
        this.textureWidth = 256;
        this.textureHeight = 128;
        
        this.base = new ModelRenderer(this, 0, 74);
        this.base.addBox(-3F, -1F, -8F, 6, 2, 14);
        this.base.setRotationPoint(-7F, 19F, 0F);
        this.base.setTextureSize(256, 128);
        this.base.mirror = true;
        this.setRotation(this.base, 0F, 0F, 0F);
        this.rightEngine = new ModelRenderer(this, 65, 0);
        this.rightEngine.addBox(-4.5F, -2F, -0.5F, 9, 4, 3);
        this.rightEngine.setRotationPoint(10F, 19F, 6F);
        this.rightEngine.setTextureSize(256, 128);
        this.rightEngine.mirror = true;
        this.setRotation(this.rightEngine, 0F, 0F, 0F);
        this.leftEngine = new ModelRenderer(this, 30, 0);
        this.leftEngine.addBox(-4.5F, -2F, -2.5F, 9, 4, 3);
        this.leftEngine.setRotationPoint(10F, 19F, -8F);
        this.leftEngine.setTextureSize(256, 128);
        this.leftEngine.mirror = true;
        this.setRotation(this.leftEngine, 0F, 0F, 0F);
        this.baseFront2 = new ModelRenderer(this, 0, 90);
        this.baseFront2.addBox(-1F, -1F, -6F, 1, 2, 12);
        this.baseFront2.setRotationPoint(-10F, 19F, -1F);
        this.baseFront2.setTextureSize(256, 128);
        this.baseFront2.mirror = true;
        this.setRotation(this.baseFront2, 0F, 0F, 0F);
        this.baseFront1 = new ModelRenderer(this, 0, 104);
        this.baseFront1.addBox(-1F, -1F, -5F, 1, 2, 10);
        this.baseFront1.setRotationPoint(-11F, 19F, -1F);
        this.baseFront1.setTextureSize(256, 128);
        this.baseFront1.mirror = true;
        this.setRotation(this.baseFront1, 0F, 0F, 0F);
        this.baseMid = new ModelRenderer(this, 0, 60);
        this.baseMid.addBox(-3F, -1F, -6F, 6, 2, 12);
        this.baseMid.setRotationPoint(-1F, 19F, -1F);
        this.baseMid.setTextureSize(256, 128);
        this.baseMid.mirror = true;
        this.setRotation(this.baseMid, 0F, 0F, 0F);
        this.baseBack = new ModelRenderer(this, 0, 44);
        this.baseBack.addBox(-3F, -1F, -8F, 13, 2, 14);
        this.baseBack.setRotationPoint(5F, 19F, 0F);
        this.baseBack.setTextureSize(256, 128);
        this.baseBack.mirror = true;
        this.setRotation(this.baseBack, 0F, 0F, 0F);
        this.baseRear1 = new ModelRenderer(this, 0, 30);
        this.baseRear1.addBox(0F, -1F, -6F, 1, 2, 12);
        this.baseRear1.setRotationPoint(15F, 19F, -1F);
        this.baseRear1.setTextureSize(256, 128);
        this.baseRear1.mirror = true;
        this.setRotation(this.baseRear1, 0F, 0F, 0F);
        this.baseRear2 = new ModelRenderer(this, 0, 18);
        this.baseRear2.addBox(0F, -1F, -5F, 1, 2, 10);
        this.baseRear2.setRotationPoint(16F, 19F, -1F);
        this.baseRear2.setTextureSize(256, 128);
        this.baseRear2.mirror = true;
        this.setRotation(this.baseRear2, 0F, 0F, 0F);
        this.baseRear3 = new ModelRenderer(this, 0, 8);
        this.baseRear3.addBox(0F, -1F, -4F, 1, 2, 8);
        this.baseRear3.setRotationPoint(17F, 19F, -1F);
        this.baseRear3.setTextureSize(256, 128);
        this.baseRear3.mirror = true;
        this.setRotation(this.baseRear3, 0F, 0F, 0F);
        this.baseRear4 = new ModelRenderer(this, 0, 0);
        this.baseRear4.addBox(0F, -1F, -3F, 1, 2, 6);
        this.baseRear4.setRotationPoint(18F, 19F, -1F);
        this.baseRear4.setTextureSize(256, 128);
        this.baseRear4.mirror = true;
        this.setRotation(this.baseRear4, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.base.render(f5);
        this.rightEngine.render(f5);
        this.leftEngine.render(f5);
        this.baseFront2.render(f5);
        this.baseFront1.render(f5);
        this.baseMid.render(f5);
        this.baseBack.render(f5);
        this.baseRear1.render(f5);
        this.baseRear2.render(f5);
        this.baseRear3.render(f5);
        this.baseRear4.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
    
    @Override
    public void doAnimation()
    {
        
    }
}
