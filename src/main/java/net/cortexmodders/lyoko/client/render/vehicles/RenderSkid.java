/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.render.vehicles;

import net.cortexmodders.lyoko.client.model.vehicles.ModelSkid;
import net.cortexmodders.lyoko.entities.vehicles.EntitySkid;

public class RenderSkid extends RenderVehicle
{
    public RenderSkid()
    {
        super(new ModelSkid(), 0.5F, "lyoko:textures/models/skid.png");
    }

    public void renderNavSkids(EntitySkid entSkid)
    {
        if (entSkid.getNavSkid(0))
        {
            // TODO render front navSkid
        }
        if (entSkid.getNavSkid(1))
        {
            // TODO render back navSkid
        }
        if (entSkid.getNavSkid(2))
        {
            // TODO render left navSkid
        }
        if (entSkid.getNavSkid(3))
        {
            // TODO render right navSkid
        }
    }
}
