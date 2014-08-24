package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.mymilkedeek.ludum.io.Progress;

/**
 * @author MyMilkedEek
 */
public class VictoryScreen implements Screen {
    private Texture background;
    private Texture foreground;
    private SpriteBatch batch;
    private float foreX;
    private float foreY;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(foreground, foreX, foreY, foreground.getWidth(), foreground.getHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Progress.save(0);
        background = new Texture("world/background.png");
        foreground = new Texture("splash/victory.png");
        foreX = (Gdx.graphics.getWidth() / 2) - ( foreground.getWidth() / 2);
        foreY = (Gdx.graphics.getHeight() / 2) - ( foreground.getHeight() / 2);

        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                return true;
            }
        });
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
