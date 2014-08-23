package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.List;

/**
 * @author MyMilkedEek
 */
public class Level {

    private Stage stage;
    private List<World> worldList;
    private int levelNumber;

    public Level(int levelNumber, List<World> worlds) {
        this.worldList = worlds;
        this.levelNumber = levelNumber;

        stage = new Stage();

        for ( World world : worlds ) {
            stage.addActor(world);
        }

        Gdx.input.setInputProcessor(stage);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
}
