package matt.lyoko.render.mobs;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import matt.lyoko.model.mobs.*;

public class RenderBlok extends RenderLiving {
	public RenderBlok(ModelBlok modelblok, float f) {
		super(new ModelBlok(), 0.5F);
	}
}
