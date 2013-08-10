/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.render.mobs;

import matt.lyoko.model.mobs.ModelBlok;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;

public class RenderBlok extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation("lyoko:textures/models/blok.png");
    
    public RenderBlok()
    {
		super(new ModelBlok(), 0.5F);
	}
    
    @Override
    protected ResourceLocation func_110775_a(Entity entity)
    {
        return this.texture;
    }
}