package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.mymilkedeek.ludum.io.Progress;

/**
 * @author MyMilkedEek
 */
public class VictoryScreen extends ScreenAdapter {
    private Texture background;
    private Texture foreground;
    private SpriteBatch batch;
    private float foreX;
    private float foreY;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch.draw(foreground, foreX, foreY, foreground.getWidth(), foreground.getHeight());
        this.batch.end();
    }

    @Override
    public void show() {
        Progress.save(0);
        this.background = new Texture("world/background.png");
        this.foreground = new Texture("splash/victory.png");
        this.foreX = (Gdx.graphics.getWidth() / 2) - ( foreground.getWidth() / 2);
        this.foreY = (Gdx.graphics.getHeight() / 2) - ( foreground.getHeight() / 2);

        this.background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                return true;
            }
        });
    }
}
