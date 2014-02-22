/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.model.tileentity;

import net.cortexmodders.lyoko.tileentity.TileEntityHolomap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelHolomap extends ModelBase
{

    ModelRenderer edge1;
    ModelRenderer edge2;
    ModelRenderer floor;
    ModelRenderer edgeInside1;
    ModelRenderer edgeInside2;

    public ModelHolomap()
    {
        this.textureWidth = 64;
        this.textureHeight = 128;

        this.edge1 = new ModelRenderer(this, 0, 0);
        this.edge1.addBox(-1F, 0F, -8F, 2, 8, 14);
        this.edge1.setRotationPoint(-7F, 8F, 0F);
        this.edge1.setTextureSize(64, 128);
        this.edge1.mirror = true;
        this.setRotation(this.edge1, 0F, 0F, 0F);
        this.edge2 = new ModelRenderer(this, 0, 22);
        this.edge2.addBox(-8F, 0F, -1F, 16, 8, 2);
        this.edge2.setRotationPoint(0F, 8F, 7F);
        this.edge2.setTextureSize(64, 128);
        this.edge2.mirror = true;
        this.setRotation(this.edge2, 0F, 0F, 0F);
        this.floor = new ModelRenderer(this, 0, 32);
        this.floor.addBox(-8F, 0F, -8F, 16, 8, 16);
        this.floor.setRotationPoint(0F, 16F, 0F);
        this.floor.setTextureSize(64, 128);
        this.floor.mirror = true;
        this.setRotation(this.floor, 0F, 0F, 0F);
        this.edgeInside1 = new ModelRenderer(this, 0, 53);
        this.edgeInside1.addBox(0F, 0F, -7F, 0, 11, 14);
        this.edgeInside1.setRotationPoint(-6F, 8F, -1F);
        this.edgeInside1.setTextureSize(64, 128);
        this.edgeInside1.mirror = true;
        this.setRotation(this.edgeInside1, 0F, 0F, -0.7679449F);
        this.edgeInside2 = new ModelRenderer(this, 0, 56);
        this.edgeInside2.addBox(-9F, 0F, 0F, 16, 11, 0);
        this.edgeInside2.setRotationPoint(1F, 8F, 6F);
        this.edgeInside2.setTextureSize(64, 128);
        this.edgeInside2.mirror = true;
        this.setRotation(this.edgeInside2, -0.7679449F, 0F, 0F);
    }

    public void render(TileEntityHolomap tile, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, tile);
        if (tile != null)
        {
            if (tile.getBlockMetadata() < 4)
            {
                this.edge1.render(f5);
                this.edgeInside1.render(f5);
            }
            if (tile.getBlockMetadata() < 8)
            {
                this.edge2.render(f5);
                this.edgeInside2.render(f5);
            }
        }
        this.floor.render(f5);
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
