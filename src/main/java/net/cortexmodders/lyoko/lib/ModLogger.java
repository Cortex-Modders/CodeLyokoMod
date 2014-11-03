/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.lib;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class ModLogger
{

    private final Logger logger;

    public ModLogger(Logger modLog)
    {
        this.logger = modLog;
    }

    public void log(Level parLevel, String parString)
    {
        this.logger.log(parLevel, parString);
    }

    public void info(String parString)
    {
        this.log(Level.INFO, parString);
    }

    public void severe(String parString)
    {
        this.log(Level.ERROR, parString);
    }

    public void warn(String parString)
    {
        this.log(Level.WARN, parString);
    }
}
