package net.mymilkedeek.ludum.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Saves and loads the progress made by the player.
 *
 * @author MyMilkedEek
 */
public abstract class Progress {

    /**
     * Tag for the preferences.
     */
    private static final String PREFERENCE_NAME = "MMESPACE";

    /**
     * Key value for the preference.
     */
    private static final String PREFERENCE_KEY = "LEVEL";

    /**
     * Default level number. Fallback in case the value isn't found.
     */
    private static final int DEFAULT_LEVEL = 0;

    /**
     * Saves the current level progression
     * @param number - the current level
     */
    public static void save(int number) {
        final Preferences preferences = Gdx.app.getPreferences(PREFERENCE_NAME);
        preferences.putInteger(PREFERENCE_KEY, number);
    }

    /**
     * Loads the current level progression
     * @return the current level
     */
    public static int load() {
        final Preferences preferences = Gdx.app.getPreferences(PREFERENCE_NAME);
        return preferences.getInteger(PREFERENCE_KEY, DEFAULT_LEVEL);
    }
}
