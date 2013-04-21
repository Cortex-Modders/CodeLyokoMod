package matt.lyoko.render.vehicles;

import org.lwjgl.opengl.GL11;

import matt.lyoko.entities.vehicles.EntityVehicle;
import matt.lyoko.model.vehicles.ModelVehicle;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderVehicle extends Render {

	protected ModelVehicle model;
	
	public RenderVehicle(ModelVehicle parModel, float shadow) {
		this.shadowSize = shadow;
		model = parModel;
	}
	
	protected void renderModel(EntityVehicle parEntityVehicle, float x, float y, float z, float f, float f1, float f2)
    {
        if (!parEntityVehicle.getHasActivePotion())
        {
            this.loadTexture(parEntityVehicle.getTexture());
            this.model.render(parEntityVehicle, x, y, z, f, f1, f2);
        }
        else
        {
            this.model.setRotationAngles(x, y, z, f, f1, f2, parEntityVehicle);
        }
        
        this.model.doAnimation();
    }

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1) {
		
		GL11.glPushMatrix();
		GL11.glDisable(2896);
		GL11.glTranslated(x, y-1, z);
		
		this.renderModel((EntityVehicle)entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glEnable(2896);
		GL11.glPopMatrix();
	}
	
}