/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import net.minecraft.client.renderer.texture.IconRegister;

public class ItemDataFragment extends ItemLyoko
{
    public ItemDataFragment(int par1)
    {
        super(par1);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        if (this.itemID == ModItems.DataFragment.itemID)
            this.itemIcon = iconRegister.registerIcon("lyoko:datafrag");
    }
}
