package matt.lyoko.render.vehicles;

import matt.lyoko.entities.vehicles.EntitySkid;
import matt.lyoko.model.vehicles.ModelSkid;
import net.minecraft.client.resources.ResourceLocation;

public class RenderSkid extends RenderVehicle
{
	public RenderSkid()
	{
		super(new ModelSkid(), 0.5F);
		texture = new ResourceLocation("lyoko", "textures/models/skid.png");
	}
	
	public void renderNavSkids(EntitySkid entSkid)
	{
		if(entSkid.getNavSkid(0))
		{
			//TODO render front navSkid
		}
		if(entSkid.getNavSkid(1))
		{
			//TODO render back navSkid
		}
		if(entSkid.getNavSkid(2))
		{
			//TODO render left navSkid
		}
		if(entSkid.getNavSkid(3))
		{
			//TODO render right navSkid
		}
	}
}
