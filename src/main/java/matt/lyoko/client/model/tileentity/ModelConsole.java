/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.model.tileentity;

import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelConsole extends ModelBase
{
    // fields
    ModelRenderer consolePlane;

    public ModelConsole()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.consolePlane = new ModelRenderer(this, 0, 0);
        this.consolePlane.addBox(-12F, -8F, 0F, 24, 16, 0);
        this.consolePlane.setRotationPoint(0F, 0F, 0F);
        this.consolePlane.setTextureSize(64, 32);
        this.consolePlane.mirror = true;
        this.setRotation(this.consolePlane, -0.5235988F, 0F, 0F);
    }

    public void render(TileEntityTowerConsole entity, float x, float y, float z, float yaw, float pitch, float scale)
    {
        this.consolePlane.render(scale);
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

}
