package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        if ( currentLevel == null ) {
            List<World> worlds = new ArrayList<World>();
            List<String> e = new ArrayList<String>();
            e.add("food");
            List<String> s = new ArrayList<String>();
            s.add("tourism");
            World world = new World(e, s);
            world.setLocation(250f, 250f, 50f);
            worlds.add(world);

            e = new ArrayList<String>();
            e.add("tourism");
            s = new ArrayList<String>();
            world = new World(e, s);
            world.setLocation(500f, 250, 50f);
            worlds.add(world);
            currentLevel = new Level(1, worlds);
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
