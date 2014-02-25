/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemDataFragment extends ItemLyoko
{
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        if (this == ModItems.dataFragment)
            this.itemIcon = iconRegister.registerIcon("lyoko:datafrag");
    }
}
