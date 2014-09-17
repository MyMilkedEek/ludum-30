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
 * Screen that shows a victory message!
 *
 * @author MyMilkedEek
 */
public class VictoryScreen extends ScreenAdapter {

    /**
     * The background graphic.
     */
    private Texture background;

    /**
     * The foreground graphic.
     */
    private Texture foreground;

    /**
     * The batch to draw everything
     */
    private SpriteBatch batch;

    /**
     * Coordinate of foreground
     */
    private float foreX;

    /**
     * Coordinate of foreground
     */
    private float foreY;

    @Override
    public final void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.draw(this.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch.draw(this.foreground, this.foreX, this.foreY, this.foreground.getWidth(), this.foreground.getHeight());
        this.batch.end();
    }

    @Override
    public final void show() {
        Progress.save(0);
        this.background = new Texture("world/background.png");
        this.foreground = new Texture("splash/victory.png");
        this.foreX = (Gdx.graphics.getWidth() / 2) - ( this.foreground.getWidth() / 2);
        this.foreY = (Gdx.graphics.getHeight() / 2) - ( this.foreground.getHeight() / 2);

        this.background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                return true;
            }
        });
    }
}
