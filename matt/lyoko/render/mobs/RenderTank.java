package matt.lyoko.render.mobs;

import matt.lyoko.model.mobs.ModelTank;
import net.minecraft.client.renderer.entity.RenderLiving;

public class RenderTank extends RenderLiving {
	public RenderTank(ModelTank modeltank, float f) {
		super(new ModelTank(), 0.5F);
	}
}
