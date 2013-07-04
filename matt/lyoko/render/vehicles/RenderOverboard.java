package matt.lyoko.render.vehicles;

import net.minecraft.client.resources.ResourceLocation;
import matt.lyoko.model.vehicles.ModelOverboard;

public class RenderOverboard extends RenderVehicle {
	public RenderOverboard() {
		super(new ModelOverboard(), 0.5F);
		texture = new ResourceLocation("/mods/lyoko/textures/models/overboard.png");
	}
}
