/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.lib;

public class ModProperties
{

    // Type inferance in use.
    public static final String MOD_ID = "CodeLyoko";
    public static final String MOD_NAME = "Code Lyoko";
    // Ant build script will replace this String with the actual version.
    public static final String MOD_VERSION = "0.5.0-Beta";

    public static final String CLIENT_PROXY = "matt.lyoko.client.ClientProxy";
    public static final String COMMON_PROXY = "matt.lyoko.client.CommonProxy";

    public static final String RESOURCE_PREFIX = "assets/lyoko/";
    public static final String BLOCK_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/blocks/";
    public static final String ARMOR_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/armor/";
    public static final String ITEM_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/items/";
    public static final String GUI_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/gui/";
    public static final String MODEL_TEXTURE_PREFIX = RESOURCE_PREFIX + "textures/models/";

    /** Use this when loading sounds. */
    public static final String SOUND_LOAD_PREFIX = RESOURCE_PREFIX + "sounds/";
    /** Use this when playing sounds. */
    public static final String SOUND_PLAY_PREFIX = SOUND_LOAD_PREFIX.replaceAll("/", ".");
}
