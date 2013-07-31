package matt.lyoko.render.vehicles;

import net.minecraft.util.ResourceLocation;
import matt.lyoko.model.vehicles.ModelOverboard;

public class RenderOverboard extends RenderVehicle
{
	public RenderOverboard()
	{
		super(new ModelOverboard(), 0.5F, "lyoko:textures/models/overboard.png");
	}
}
