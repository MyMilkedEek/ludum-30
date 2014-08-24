package net.mymilkedeek.ludum.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyMilkedEek
 */
public class ImageDictionary {

    private static Map<String, Texture> map;

    public static Texture getImage(String name) {
        if ( map == null ) {
            init();
        }

        return map.get(name);
    }

    private static void init() {
        map = new HashMap<String, Texture>();

        FileHandle fileHandle = Gdx.files.internal("img");

        for ( FileHandle file : fileHandle.list() ) {
            if ( file.name().endsWith(".png")) {
                map.put(file.name().replace(".png", ""), new Texture(file));
            }
        }
    }
}
