/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.render.mob;

import net.cortexmodders.lyoko.client.model.mob.ModelBlok;
import net.cortexmodders.lyoko.entities.mob.EntityXanafiedMob;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderXanafiedMob extends RendererLivingEntity
{
    private static ResourceLocation texture = new ResourceLocation("lyoko:textures/models/blok.png");

    public RenderXanafiedMob()
    {
        super(new ModelBlok(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return RenderXanafiedMob.texture;
    }

    @Override
    public void doRender(EntityLivingBase ent, double par2, double par4, double par6, float par8, float par9)
    {
        EntityXanafiedMob xana = (EntityXanafiedMob) ent;
        if (xana != null && xana.infectedMob != null) {
            RendererLivingEntity render = (RendererLivingEntity) RenderManager.instance.getEntityRenderObject(xana.infectedMob);
            render.doRender(xana, par2, par4, par6, par8, par9);
        } else
            super.doRender(ent, par2, par4, par6, par8, par9);
    }
}
