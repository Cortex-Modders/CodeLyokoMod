/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko;

import matt.lyoko.lib.ModProperties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLogger
{

    private static Logger logger = LogManager.getLogger(ModProperties.MOD_ID);

    public static void log(Level parLevel, String parString)
    {
        logger.log(parLevel, parString);
    }
    
    public static void info(String parString)
    {
        log(Level.INFO, parString);
    }
    
    public static void severe(String parString)
    {
        log(Level.ERROR, parString);
    }
}
