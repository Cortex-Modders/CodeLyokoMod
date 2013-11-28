/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko;

import java.util.logging.Level;
import java.util.logging.Logger;

import matt.lyoko.lib.ModProperties;
import cpw.mods.fml.common.FMLLog;

public class ModLogger
{

    private static Logger logger = Logger.getLogger(ModProperties.MOD_ID);

    public static void init()
    {
        logger.setParent(FMLLog.getLogger());
    }

    public static void log(Level parLevel, String parString)
    {
        logger.log(parLevel, parString);
    }
}
