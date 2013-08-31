/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.client.render.vehicles;

import matt.lyoko.client.model.vehicles.ModelVehicle;
import matt.lyoko.entities.vehicles.EntityVehicle;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public abstract class RenderVehicle extends Render {

    protected ModelVehicle model;
    public ResourceLocation texture;
    
    public RenderVehicle(ModelVehicle parModel, float shadow, String textureLocation) {
        shadowSize = shadow;
        model = parModel;
        texture = new ResourceLocation(textureLocation);
    }

    protected void renderModel(EntityVehicle parEntityVehicle, float x, float y, float z, float f, float f1, float f2) {
        this.func_110776_a(texture);
        model.render(parEntityVehicle, x, y, z, f, f1, f2);
    }

    public void doRenderVehicle(EntityVehicle parEntityVehicle, double x, double y, double z, float f, float f1) {
        GL11.glPushMatrix();
        GL11.glDisable(2896);

        // no idea what f1 is. the last value is like the length i think.
        //float hover = MathHelper.sin(f1 / 10.0F + parEntityVehicle.hoverSpeed) * 0.1F + 0.06F;
        GL11.glTranslated(x, y - 1/* + hover*/, z);
        this.renderModel(parEntityVehicle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float f1) {
        doRenderVehicle((EntityVehicle) entity, x, y, z, f, f1);
    }

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return texture;
	}
}