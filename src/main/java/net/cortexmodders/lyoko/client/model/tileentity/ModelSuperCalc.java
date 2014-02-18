/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSuperCalc extends ModelBase
{
    // fields
    ModelRenderer SuperCalcBase;
    ModelRenderer SuperCalcTower;

    public ModelSuperCalc()
    {
        this.textureWidth = 256;
        this.textureHeight = 64;

        this.SuperCalcBase = new ModelRenderer(this, 0, 0);
        this.SuperCalcBase.addBox(0F, 0F, 0F, 48, 16, 48);
        this.SuperCalcBase.setRotationPoint(-24F, 8F, -24F);
        this.SuperCalcBase.setTextureSize(256, 64);
        this.SuperCalcBase.mirror = true;
        this.setRotation(this.SuperCalcBase, 0F, 0F, 0F);
        this.SuperCalcTower = new ModelRenderer(this, 192, 0);
        this.SuperCalcTower.addBox(0F, 0F, 0F, 16, 32, 16);
        this.SuperCalcTower.setRotationPoint(-8F, -24F, -8F);
        this.SuperCalcTower.setTextureSize(256, 64);
        this.SuperCalcTower.mirror = true;
        this.setRotation(this.SuperCalcTower, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.SuperCalcBase.render(f5);
        this.SuperCalcTower.render(f5);
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

    public void renderModel(float f)
    {
        this.SuperCalcBase.render(f);
        this.SuperCalcTower.render(f);
    }
}
