package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.List;

/**
 * @author MyMilkedEek
 */
public class Level {

    private Stage stage;
    private List<World> worldList;
    private int levelNumber;
    private TextButton nextButton;
    private TextButton mainButton;
    private ShapeRenderer renderer;
    private boolean nextLevel;
    private boolean mainMenu = false;

    public Level(int levelNumber, List<World> worlds) {
        this.worldList = worlds;
        this.levelNumber = levelNumber;
        nextLevel = false;

        stage = new Stage();

        for ( World world : worlds ) {
            stage.addActor(world);
        }

        Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));

        nextButton = new TextButton("Next Level", uiSkin);
        nextButton.setVisible(false);
        nextButton.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2, 300, 50);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextLevel = true;
            }
        });

        mainButton = new TextButton("Main menu", uiSkin);
        mainButton.setVisible(false);
        mainButton.setBounds(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2 - 70, 300, 50);
        mainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenu = true;
            }
        });

        stage.addActor(nextButton);
        stage.addActor(mainButton);

        Gdx.input.setInputProcessor(stage);

        renderer = new ShapeRenderer();
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();

        /*renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.ORANGE);
        renderer.rect(nextButton.getX(), nextButton.getY(), nextButton.getWidth(), nextButton.getHeight());
        renderer.end();*/
    }

    public List<World> getWorlds() {
        return worldList;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void dispose() {
        stage.dispose();
    }

    public boolean isNextLevel() {
        return nextLevel;
    }

    public boolean isMainMenu() {
        return mainMenu;
    }

    public void displayNextLevelMenu() {
        for ( World world : worldList ) {
            world.setTouchable(Touchable.disabled);
        }

        nextButton.setVisible(true);
        mainButton.setVisible(true);
    }
}
