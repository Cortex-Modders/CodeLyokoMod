package matt.lyoko.render.mobs;

import matt.lyoko.model.mobs.ModelBlok;
import net.minecraft.client.renderer.entity.RenderLiving;

public class RenderBlok extends RenderLiving {
	public RenderBlok(ModelBlok modelblok, float f) {
		super(new ModelBlok(), 0.5F);
	}
}
