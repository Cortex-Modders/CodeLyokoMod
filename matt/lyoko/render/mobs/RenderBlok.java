package matt.lyoko.render.mobs;

import matt.lyoko.model.mobs.ModelBlok;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;

public class RenderBlok extends RenderLiving {
	
    private final ResourceLocation texture = new ResourceLocation("/mods/lyoko/textures/models/blok.png");
    
    public RenderBlok(ModelBlok modelblok, float f) {
		super(new ModelBlok(), 0.5F);
	}

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        return this.texture;
    }
}
