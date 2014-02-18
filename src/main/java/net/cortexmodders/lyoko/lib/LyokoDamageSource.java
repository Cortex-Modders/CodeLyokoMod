/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.lib;

import net.minecraft.util.DamageSource;

public class LyokoDamageSource extends DamageSource
{
    public LyokoDamageSource(String par1Str)
    {
        super(par1Str);
    }

    public static LyokoDamageSource digitalSea = new LyokoDamageSource("CodeLyoko.digitalSea").setDamageBypassesArmor();
    public static LyokoDamageSource marabounta = new LyokoDamageSource("CodeLyoko.marabounta").setDamageBypassesArmor();

    @Override
    public LyokoDamageSource setDamageBypassesArmor()
    {
        return (LyokoDamageSource) super.setDamageBypassesArmor();
    }
}
