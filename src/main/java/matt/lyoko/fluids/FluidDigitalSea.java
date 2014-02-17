/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.fluids;

import net.minecraftforge.fluids.Fluid;

public class FluidDigitalSea extends Fluid
{
    public FluidDigitalSea(String fluidName)
    {
        super(fluidName);
        this.setLuminosity(15);
    }

    @Override
    public int getColor()
    {
        return 0x0000FF;
    }
}
