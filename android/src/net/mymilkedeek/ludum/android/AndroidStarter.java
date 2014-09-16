package net.mymilkedeek.ludum.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import net.mymilkedeek.ludum.GameInstance;

/**
 * Launcher for the Android port of the game.
 *
 * @author MyMilkedEek
 */
public class AndroidStarter extends AndroidApplication {
    /**
     * Called when the activity is first created.
     * Sets the following variables:
     * - accelerometer: off
     * - compass: off
     * - wakeLock: on
     *
     * Nothing is done with the saved instance.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        // configure the application
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useWakelock = true;

        // start the game
        initialize(new GameInstance(), cfg);
    }
}
