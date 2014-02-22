/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.render.vehicles;

import net.cortexmodders.lyoko.client.model.vehicles.ModelOverboard;

public class RenderOverboard extends RenderVehicle
{
    public RenderOverboard()
    {
        super(new ModelOverboard(), 0.5F, "lyoko:textures/models/overboard.png");
    }
}
