package net.mymilkedeek.ludum;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by michael on 8/23/2014.
 */
public class DesktopStarter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Ludum Swear 30";
        cfg.width = 800;
        cfg.height = 480;
        new LwjglApplication(new GameInstance(), cfg);
    }
}
