/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.render.vehicles;

import matt.lyoko.client.model.vehicles.ModelOverboard;

public class RenderOverboard extends RenderVehicle
{
    public RenderOverboard()
    {
        super(new ModelOverboard(), 0.5F, "lyoko:textures/models/overboard.png");
    }
}
