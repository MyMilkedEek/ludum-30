package net.mymilkedeek.ludum.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * @author MyMilkedEek
 */
public class Progress {

    public static void save(int number) {
        Preferences preferences = Gdx.app.getPreferences("MMESPACE");
        preferences.putInteger("LEVEL", number);
    }

    public static int load() {
        Preferences preferences = Gdx.app.getPreferences("MMESPACE");
        return preferences.getInteger("LEVEL", 0);
    }
}
