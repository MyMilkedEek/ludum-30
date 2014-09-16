package net.mymilkedeek.ludum.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Loads and keeps every image in memory.
 *
 * @author MyMilkedEek
 */
public abstract class ImageDictionary {

    /**
     * The path to the image directory
     */
    private static final String PATH = "img";

    /**
     * Extension of the files.
     */
    private static final String EXTENSION = ".png";

    /**
     * Empty string.
     */
    private static final String EMPTY = "";

    /**
     * The map containing every texture loaded.
     */
    private static Map<String, Texture> map;

    /**
     * Get the loaded image based on its identifier.
     * @param name String
     * @return Texture linked to the identifier
     */
    public static Texture getImage(String name) {
        if (map == null) {
            init();
        }

        return map.get(name);
    }

    /**
     * Initializes the image dictionary by looping over the file system.
     */
    private static void init() {
        map = new HashMap<String, Texture>();

        final FileHandle fileHandle = Gdx.files.internal(PATH);

        for (final FileHandle file : fileHandle.list()) {
            if (file.name().endsWith(EXTENSION)) {
                map.put(file.name().replace(EXTENSION, EMPTY), new Texture(file));
            }
        }
    }
}
