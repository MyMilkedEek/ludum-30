package net.mymilkedeek.ludum;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import net.mymilkedeek.ludum.screens.SplashScreen;

/**
 * Entry point for the Game. Does nothing but set the splash screen as its current screen.
 *
 * @author MyMilkedEek
 */
public class GameInstance extends Game {

    /**
     * The tag for logging purposes.
     */
    private final String LOG_TAG = "MMESPACE";

    /**
     * Logging message to indicate start of initialization.
     */
    private final String LOG_START = "Game initialization";

    /**
     * Instantiates the game and does nothing else but send a message to the log.
     */
    public GameInstance() {
        Gdx.app.log(LOG_TAG, LOG_START);
    }

    /**
     * Called when the application is created.
     * Sets the splash as its current screen.
     */
    @Override
    public void create() {
        setScreen(new SplashScreen(this));
    }
}
