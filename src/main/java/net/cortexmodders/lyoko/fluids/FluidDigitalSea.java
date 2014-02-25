/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.fluids;

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
