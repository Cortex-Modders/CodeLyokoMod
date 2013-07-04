package matt.lyoko.lib;

public class ModProperties {

    // Type inferance in use.
    public static final String MOD_ID = "CodeLyoko";
    public static final String MOD_NAME = "Code Lyoko";
    // Ant build script will replace this String with the actual version.
    public static final String MOD_VERSION = "0.5.0-Beta";

    public static final String CLIENT_PROXY = "matt.lyoko.client.ClientProxy";
    public static final String COMMON_PROXY = "matt.lyoko.client.CommonProxy";

    public static final String RESOURCE_PREFIX = "assets/lyoko/";
    public static final String BLOCK_TEXTURE_PREFIX = RESOURCE_PREFIX + "blocks/";
    public static final String ARMOR_TEXTURE_PREFIX = RESOURCE_PREFIX + "armor/";
    public static final String ITEM_TEXTURE_PREFIX = RESOURCE_PREFIX + "items/";
    public static final String GUI_TEXTURE_PREFIX = RESOURCE_PREFIX + "gui/";
    public static final String MODEL_TEXTURE_PREFIX = RESOURCE_PREFIX + "models/";
    
    /** Use this when loading sounds. */
    public static final String SOUND_LOAD_PREFIX = RESOURCE_PREFIX + "sounds/";
    /** Use this when playing sounds. */
    public static final String SOUND_PLAY_PREFIX = SOUND_LOAD_PREFIX.replaceAll("/", ".");
}
