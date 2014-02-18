/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGlove2 extends ModelBase
{
    // fields
    ModelRenderer hand;
    ModelRenderer finger1;
    ModelRenderer finger2;
    ModelRenderer finger3;
    ModelRenderer finger4;

    public ModelGlove2()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.hand = new ModelRenderer(this, 0, 0);
        this.hand.addBox(0F, 0F, 0F, 4, 4, 7);
        this.hand.setRotationPoint(0F, 0F, 0F);
        this.hand.setTextureSize(64, 32);
        this.hand.mirror = true;
        this.setRotation(this.hand, 0F, 0F, 0F);
        this.finger1 = new ModelRenderer(this, 0, 0);
        this.finger1.addBox(0F, 0F, 0F, 1, 3, 1);
        this.finger1.setRotationPoint(0F, 0F, -1F);
        this.finger1.setTextureSize(64, 32);
        this.finger1.mirror = true;
        this.setRotation(this.finger1, 0F, 0F, 0F);
        this.finger2 = new ModelRenderer(this, 0, 0);
        this.finger2.addBox(0F, 0F, 0F, 1, 3, 1);
        this.finger2.setRotationPoint(1F, 0F, -1F);
        this.finger2.setTextureSize(64, 32);
        this.finger2.mirror = true;
        this.setRotation(this.finger2, 0F, 0F, 0F);
        this.finger3 = new ModelRenderer(this, 0, 0);
        this.finger3.addBox(0F, 0F, 0F, 1, 3, 1);
        this.finger3.setRotationPoint(2F, 0F, -1F);
        this.finger3.setTextureSize(64, 32);
        this.finger3.mirror = true;
        this.setRotation(this.finger3, 0F, 0F, 0F);
        this.finger4 = new ModelRenderer(this, 0, 0);
        this.finger4.addBox(0F, 0F, 0F, 1, 3, 1);
        this.finger4.setRotationPoint(3F, 0F, -1F);
        this.finger4.setTextureSize(64, 32);
        this.finger4.mirror = true;
        this.setRotation(this.finger4, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.hand.render(f5);
        this.finger1.render(f5);
        this.finger2.render(f5);
        this.finger3.render(f5);
        this.finger4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }

}
