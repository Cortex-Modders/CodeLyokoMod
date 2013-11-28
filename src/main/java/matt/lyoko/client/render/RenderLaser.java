/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.render;

import matt.lyoko.client.model.ModelLaser;
import matt.lyoko.entities.projectile.EntityLyokoRanged;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLaser extends Render
{

    protected ModelBase model;
    public ResourceLocation texture;

    public RenderLaser()
    {
        this.shadowSize = 0.0F;
        this.model = new ModelLaser();
        this.texture = new ResourceLocation("lyoko:textures/models/laser.png");
    }

    protected void renderModel(EntityLyokoRanged parEntityVehicle, float x, float y, float z, float f, float f1, float f2)
    {
        this.bindTexture(this.texture);
        this.model.render(parEntityVehicle, x, y, z, f, f1, f2);
    }

    public void doRenderVehicle(EntityLyokoRanged parEntityVehicle, double x, double y, double z, float f, float f1)
    {
        // GL11.glPushMatrix();
        // GL11.glDisable(2896);

        // no idea what f1 is. the last value is like the length i think.
        // float hover = MathHelper.sin(f1 / 10.0F +
        // parEntityVehicle.hoverSpeed) * 0.1F + 0.06F;
        // GL11.glTranslated(x, y - 1 + hover, z);
        this.renderModel(parEntityVehicle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        // GL11.glEnable(2896);
        // GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float f1)
    {
        this.doRenderVehicle((EntityLyokoRanged) entity, x, y, z, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.texture;
    }
}