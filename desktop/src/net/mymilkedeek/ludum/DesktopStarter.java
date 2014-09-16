package net.mymilkedeek.ludum;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Launcher for the desktop port of the game.
 *
 * @author MyMilkedEek
 */
public class DesktopStarter {

    /**
     * Width of the screen.
     */
    private static final int width = 800;

    /**
     * Height of the screen
     */
    private static final int height = 480;

    /**
     * Title of the game. Visible in the title bar.
     */
    private static final String title = "Planets in the scale";

    /**
     * Entry point for the desktop part of the game. Sets a few variables to the configuration:
     * - title
     * - width
     * - height
     *
     * @param args
     */
    public static void main(String[] args) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = title;
        cfg.width = width;
        cfg.height = height;
        new LwjglApplication(new GameInstance(), cfg);
    }

}