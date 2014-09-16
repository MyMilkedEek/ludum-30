package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import net.mymilkedeek.ludum.GameInstance;

/**
 * Screen that animates a splash screen.
 * Animations:
 * - fade in
 * - display image
 * - fade out
 *
 * @author My Milked Eek
 */
public class SplashScreen extends ScreenAdapter {

    /**
     * The scene2D stage.
     */
    private Stage stage;

    /**
     * Instance of our game object.
     */
    private GameInstance game;

    /**
     * Runnable that sets the next screen after the splash animations have finished.
     */
    private final Runnable onSplashFinishedRunnable = new Runnable() {
        @Override
        public void run() {
            SplashScreen.this.game.setScreen(new MainMenuScreen());
        }
    };

    /**
     * Default constructor. Only sets the gameInstance.
     *
     * @param game GameInstance
     */
    public SplashScreen(GameInstance game) {
        this.game = game;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public void show() {
        // time to fade in or our
        final float fade = 2f;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        // load the image
        final Image splashImage = new Image(new Texture(Gdx.files.internal("splash/splash.png")));
        // calculate dimensions of the screen
        // and set them to the image
        final float scale = this.stage.getWidth() / splashImage.getWidth();
        final float scaleY = this.stage.getHeight() / splashImage.getHeight();
        splashImage.setWidth(scale * splashImage.getWidth());
        splashImage.setHeight(scaleY * splashImage.getHeight());
        splashImage.setX(this.stage.getWidth() / 2 - splashImage.getWidth() / 2);
        splashImage.setY(this.stage.getHeight() / 2 - splashImage.getHeight() / 2);
        // apply the animation
        splashImage.addAction(Actions.sequence(Actions.fadeIn(fade), Actions.fadeOut(fade), Actions.run(this.onSplashFinishedRunnable)));

        this.stage.addActor(splashImage);
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }
}