package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import net.mymilkedeek.ludum.io.LevelLoader;
import net.mymilkedeek.ludum.model.Level;
import net.mymilkedeek.ludum.model.World;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyMilkedEek
 */
public class GameScreen implements Screen {

    private Level currentLevel;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentLevel.render(delta);

        boolean completed = true;

        for ( World world : currentLevel.getWorlds() ) {
            if ( !world.planetCompleted() ) {
                completed = false;
                break;
            }
        }

        if ( completed ) {
            System.out.println("Hooray!");
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        if ( currentLevel == null ) {
            currentLevel = LevelLoader.loadLevel(3);
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
