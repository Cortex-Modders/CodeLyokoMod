/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.lib;

public class ModProperties
{

    // Type inferance in use.
    public static final String MOD_ID = "CodeLyoko";
    public static final String MOD_NAME = "Code Lyoko";
    // Ant build script will replace this String with the actual version.
    public static final String MOD_VERSION = "${version}@";

    public static final String CLIENT_PROXY = "net.cortexmodders.lyoko.proxy.ClientProxy";
    public static final String COMMON_PROXY = "net.cortexmodders.lyoko.proxy.CommonProxy";

    public static final String RESOURCE_PREFIX = "assets/lyoko/";
    public static final String BLOCK_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/blocks/";
    public static final String ARMOR_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/armor/";
    public static final String ITEM_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/items/";
    public static final String GUI_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/gui/";
    public static final String MODEL_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/models/";

    /**
     * Use this when loading or playing sounds.
     */
    public static final String SOUND_PREFIX = "lyoko:";
//    public static final String CAPE_CONFIG_URL = "https://raw2.github.com/Cortex-Modders/CodeLyokoMod/master/capes/config.json";
//    public static final String CAPE_CONFIG_URL = "https://dl.dropboxusercontent.com/u/22865035/ModHosting/capes/capes.json";
    public static final String CAPE_CONFIG_URL = "http://forgewareinc.com/modresources/DevCapes/devs.json";

    public static enum ConfigCategories
    {
        BLOCKS, ITEMS, DIMENSIONS, OTHER,
    }
}
