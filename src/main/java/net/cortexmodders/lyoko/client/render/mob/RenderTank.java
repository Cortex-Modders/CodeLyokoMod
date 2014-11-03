/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.render.mob;

import net.cortexmodders.lyoko.client.model.mob.ModelTank;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTank extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation("lyoko:textures/models/tank.png");

    public RenderTank()
    {
        super(new ModelTank(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
}
