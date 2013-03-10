package matt.lyoko.render.vehicles;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import matt.lyoko.model.vehicles.*;

public class RenderOverboard extends RenderLiving {
	public RenderOverboard(ModelOverboard modelboard, float f) {
		super(new ModelOverboard(), 0.5F);
	}
}
