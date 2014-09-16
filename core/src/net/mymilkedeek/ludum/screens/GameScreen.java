package net.mymilkedeek.ludum.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import net.mymilkedeek.ludum.io.LevelLoader;
import net.mymilkedeek.ludum.io.Progress;
import net.mymilkedeek.ludum.model.Level;
import net.mymilkedeek.ludum.model.World;

/**
 * This is the screen that contains the game loop and checks.
 *
 * It checks if the victory condition per level has been reached
 * and sets the new level accordingly.
 *
 * If the victory condition has been reached,
 * set the victory screen as the current screen.
 *
 * @author MyMilkedEek
 */
public class GameScreen extends ScreenAdapter {

    /**
     * The current level.
     */
    private transient Level currentLevel;

    /**
     * The drawn background.
     */
    private Texture background;

    /**
     * The batch object to draw stuff to the screen!
     */
    private SpriteBatch batch;

    /**
     * Starting level for this player.
     */
    private final int startLevel;

    /**
     * Default constructor for the GameScreen.
     *
     * @param level starting level of the game.
     */
    public GameScreen(int level) {
        this.startLevel = level;
    }

    @Override
    public final void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.batch.draw(background, 0, 0,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch.end();

        this.currentLevel.render(delta);

        boolean completed = true;

        for (final World world : this.currentLevel.getWorlds()) {
            // loop over the worlds to find out if every world
            // is balanced or not
            if (! world.planetCompleted()) {
                completed = false;
                break;
            }
        }

        if (completed) {
            // display menu
            this.currentLevel.displayNextLevelMenu();

            if (this.currentLevel.isNextLevel()) {
                // save the progress
                Progress.save(this.currentLevel.getLevelNumber());
                try {
                    currentLevel = LevelLoader.loadLevel(
                            currentLevel.getLevelNumber() + 1
                    );
                } catch (GdxRuntimeException e) {
                    // end of game is reached
                    Gdx.app.log("MMESPACE", "End of game!");
                    this.currentLevel.dispose();

                    ((Game) Gdx.app.getApplicationListener())
                            .setScreen(new VictoryScreen());
                }
            } else if (this.currentLevel.isMainMenu()) {
                Progress.save(this.currentLevel.getLevelNumber());
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(new MainMenuScreen());
            }
        }
    }

    @Override
    public final void show() {
        if (this.currentLevel == null) {
            this.currentLevel = LevelLoader.loadLevel(startLevel);
        }

        this.background = new Texture("world/background.png");
        // repeat the background horizontally and vertically
        this.background.setWrap(
                Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat
        );
        this.batch = new SpriteBatch();
    }
}