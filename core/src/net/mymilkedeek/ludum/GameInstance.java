package net.mymilkedeek.ludum;

import com.badlogic.gdx.Game;
import net.mymilkedeek.ludum.screens.GameScreen;
import net.mymilkedeek.ludum.screens.IntroScreen;
import net.mymilkedeek.ludum.screens.SplashScreen;

/**
 * Created by michael on 8/23/2014.
 */
public class GameInstance extends Game {
    @Override
    public void create() {
        setScreen(new SplashScreen(this));
    }
}
