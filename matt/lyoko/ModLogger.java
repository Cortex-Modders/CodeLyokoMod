package matt.lyoko;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import matt.lyoko.lib.ModProperties;

public class ModLogger {

    private static Logger logger = Logger.getLogger(ModProperties.MOD_ID);
    
    public static void init() {
        logger.setParent(FMLLog.getLogger());
    }
    
    public static void log(Level parLevel, String parString) {
        logger.log(parLevel, parString);
    }
}
