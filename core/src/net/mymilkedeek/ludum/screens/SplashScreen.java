package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import net.mymilkedeek.ludum.GameInstance;

/**
 * @author My Milked Eek
 */
public class SplashScreen implements Screen {
    private Stage stage;
    private GameInstance game;
    public SplashScreen(GameInstance game) {
        this.game = game;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Image splashImage = new Image(new Texture(Gdx.files.internal("splash/splash.png")));
        float scale = stage.getWidth() / splashImage.getWidth();
        float scaleY = stage.getHeight() / splashImage.getHeight();
        splashImage.setWidth(scale * splashImage.getWidth());
        splashImage.setHeight(scaleY * splashImage.getHeight());
        splashImage.setX(stage.getWidth() / 2 - splashImage.getWidth() / 2);
        splashImage.setY(stage.getHeight() / 2 - splashImage.getHeight() / 2);
        splashImage.addAction(Actions.sequence(Actions.fadeIn(2f), Actions.fadeOut(2f), Actions.run(onSplashFinishedRunnable)));
        stage.addActor(splashImage);
    }
    private final Runnable onSplashFinishedRunnable = new Runnable() {
        @Override
        public void run() {
            game.setScreen(new MainMenuScreen());
        }
    };
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
        stage.dispose();
    }
}