package matt.lyoko.render.vehicles;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import matt.lyoko.model.vehicles.*;

public class RenderSkid extends RenderVehicle {
	public RenderSkid() {
		super(new ModelSkid(), 0.5F);
	}
}
