package matt.lyoko;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class RenderTank extends RenderLiving
{
    public RenderTank(ModelTank modeltank, float f)
    {
        super(new ModelTank(), 0.5F);
    }
}
