package matt.lyoko.render.mobs;

import matt.lyoko.model.mobs.ModelTank;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;

public class RenderTank extends RenderLiving {
	
    private static final ResourceLocation texture = new ResourceLocation("");
    
    public RenderTank(ModelTank modeltank, float f) {
		super(new ModelTank(), 0.5F);
	}

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {
        // TODO Auto-generated method stub
        return texture;
    }
}
