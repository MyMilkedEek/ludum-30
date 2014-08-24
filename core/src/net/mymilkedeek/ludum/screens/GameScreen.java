package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.GdxRuntimeException;
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
    private Texture background;
    private SpriteBatch batch;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        currentLevel.render(delta);

        boolean completed = true;

        for ( World world : currentLevel.getWorlds() ) {
            if ( !world.planetCompleted() ) {
                completed = false;
                break;
            }
        }

        if ( completed ) {
            // display menu
            currentLevel.displayNextLevelMenu();

            if ( currentLevel.isNextLevel() ) {
                Level newCurrentLevel;
                try {
                    newCurrentLevel = LevelLoader.loadLevel(currentLevel.getLevelNumber() + 1);
                } catch (GdxRuntimeException e) {
                    newCurrentLevel = null;
                }

                if (newCurrentLevel == null) {
                    //TODO end of game
                    currentLevel.dispose();
                    currentLevel = null;
                    System.out.println("Hooray!");
                    Gdx.app.exit();
                } else {
                    currentLevel = newCurrentLevel;
                }
            } else if ( currentLevel.isMainMenu()) {
                // TODO set to main menu
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        if ( currentLevel == null ) {
            currentLevel = LevelLoader.loadLevel(1);
        }

        background = new Texture("world/background.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch = new SpriteBatch();
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
