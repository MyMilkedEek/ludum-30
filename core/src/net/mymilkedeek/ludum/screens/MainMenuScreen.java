package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.mymilkedeek.ludum.io.Progress;

/**
 * The Main Menu Screen.
 * Links to Game screen and a Level Selection (not working)
 *
 * @author MyMilkedEek
 */
public class MainMenuScreen extends ScreenAdapter {

    /**
     * The background graphic.
     */
    private Texture background;

    /**
     * Batch to draw things.
     */
    private SpriteBatch batch;

    /**
     * Stage to add buttons.
     */
    private Stage stage;

    @Override
    public final void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.draw(this.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch.end();

        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public final void show() {
        final TextButton gameButton;

        this.background = new Texture("world/background.png");
        this.background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.batch = new SpriteBatch();

        final Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));

        gameButton = new TextButton("Play the game", uiSkin);
        gameButton.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2, 300, 50);
        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(Progress.load() + 1));
            }
        });

        this.stage = new Stage();
        this.stage.addActor(gameButton);

        Gdx.input.setInputProcessor(this.stage);
    }
}
