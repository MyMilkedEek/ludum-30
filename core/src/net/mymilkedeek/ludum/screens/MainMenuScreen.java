package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.mymilkedeek.ludum.io.LevelLoader;
import net.mymilkedeek.ludum.io.Progress;

/**
 * @author MyMilkedEek
 */
public class MainMenuScreen implements Screen {
    private Texture background;
    private SpriteBatch batch;

    private TextButton gameButton;
    private TextButton levelSelectButton;

    private Stage stage;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        background = new Texture("world/background.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch = new SpriteBatch();

        Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));

        gameButton = new TextButton("Play the game", uiSkin);
        gameButton.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2, 300, 50);
        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(Progress.load() + 1));
            }
        });
        levelSelectButton = new TextButton("Select a Level", uiSkin);
        levelSelectButton.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2 - 70, 300, 50);
        levelSelectButton.setDisabled(true);
        levelSelectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        stage = new Stage();
        stage.addActor(gameButton);
        stage.addActor(levelSelectButton);

        Gdx.input.setInputProcessor(stage);
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
