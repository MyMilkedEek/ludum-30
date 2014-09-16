package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.mymilkedeek.ludum.screens.GameScreen;

import java.util.List;

/**
 * Level contains several worlds, which contain goals, excesses
 * boni and are used to calculate the victory condition.
 *
 * @author MyMilkedEek
 */
public class Level {

    /**
     * The scene2D stage to which we add actors.
     */
    private final Stage stage;

    /**
     * List of worlds in this level.
     */
    private final List<World> worldList;

    /**
     * This level's number.
     */
    private final int levelNumber;

    /**
     * UI button "next"
     */
    private final TextButton nextButton;

    /**
     * UI button "main"
     */
    private final TextButton mainButton;

    /**
     * UI button "reset"
     */
    private final TextButton resetButton;

    /**
     * Flag indicating if this level is finished.
     */
    private boolean nextLevel;

    /**
     * Should we display the main menu?
     */
    private boolean mainMenu;

    /**
     * Default constructor.
     *
     * @param levelNumber identifier for this level
     * @param worlds list of worlds
     * @param table tutorial
     */
    public Level(final int levelNumber, final List<World> worlds, final Table table) {
        this.worldList = worlds;
        this.levelNumber = levelNumber;
        this.nextLevel = false;

        this.stage = new Stage();

        for ( final World world : worlds ) {
            this.stage.addActor(world);
        }

        Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));

        this.nextButton = new TextButton("Next Level", uiSkin);
        this.nextButton.setVisible(false);
        this.nextButton.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2, 300, 50);
        this.nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Level.this.nextLevel = true;
            }
        });

        this.mainButton = new TextButton("Main menu", uiSkin);
        this.mainButton.setVisible(false);
        this.mainButton.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2 - 70, 300, 50);
        this.mainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Level.this.mainMenu = true;
            }
        });

        this.stage.addActor(nextButton);
        this.stage.addActor(mainButton);

        if ( table != null ) {
            float height = Gdx.graphics.getHeight() * 0.8f;
            if ( levelNumber == 2 ) {
                height -= 50f;
            }
            table.setBounds(Gdx.graphics.getWidth() * 0.2f, height, Gdx.graphics.getWidth() * 0.2f * 3, Gdx.graphics.getHeight() * 0.8f / 4);
            this.stage.addActor(table);
        }

        this.resetButton = new TextButton("Restart Level", uiSkin);
        this.resetButton.setBounds(0,0,100,30);
        this.resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(Level.this.levelNumber));
            }
        });

        this.stage.addActor(resetButton);

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the level.
     *
     * @param delta time passed
     */
    public final void render(float delta) {
        this.stage.act(delta);
        this.stage.draw();
    }

    /**
     * Getter for worldList.
     * @return list of worlds
     */
    public final List<World> getWorlds() {
        return this.worldList;
    }

    /**
     * Getter for levelNumber
     * @return number for this level
     */
    public final int getLevelNumber() {
        return this.levelNumber;
    }

    /**
     * Disposes of this object and its members.
     */
    public final void dispose() {
        this.stage.dispose();
    }

    /**
     * Getter for nextLevel
     * @return boolean
     */
    public final boolean isNextLevel() {
        return this.nextLevel;
    }

    /**
     * Getter for mainMenu
     * @return boolean
     */
    public final boolean isMainMenu() {
        return this.mainMenu;
    }

    /**
     * Toggle the popup menu.
     */
    public final void displayNextLevelMenu() {
        for ( final World world : this.worldList ) {
            world.setTouchable(Touchable.disabled);
        }

        this.nextButton.setVisible(true);
        this.mainButton.setVisible(true);
    }
}
