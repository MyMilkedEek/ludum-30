package net.mymilkedeek.ludum.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import net.mymilkedeek.ludum.model.Level;
import net.mymilkedeek.ludum.model.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Utility class to load levels.
 *
 * @author MyMilkedEek
 */
public class LevelLoader {

    /**
     * Empty private constructor to disallow instantiating this class.
     */
    private LevelLoader() {
        // empty body
    }

    /**
     * Loads the level based on its level number.
     * If it is an early level a tutorial will be loaded.
     *
     * @param levelNumber the number of the level to load
     * @return the loaded level
     */
    public static Level loadLevel(int levelNumber) {
        final StringBuilder levelId = new StringBuilder();

        levelId.append("saves/");

        if (levelNumber < 10) {
            levelId.append("0");
        }

        levelId.append(levelNumber);
        levelId.append(".mme");

        final FileHandle internal = Gdx.files.internal(levelId.toString());
        final String fileContent = internal.readString();

        // splits on planets
        final StringTokenizer stringTokenizer = new StringTokenizer(fileContent, "||", false);

        final List<World> worlds = new ArrayList<World>();

        while (stringTokenizer.hasMoreElements()) {
            final String newPlanetString = stringTokenizer.nextToken();

            final StringTokenizer planetTokenizer = new StringTokenizer(newPlanetString, ";", false);

            // excess
            final List<String> excess = new ArrayList<String>();
            final StringTokenizer excessTokenizer = new StringTokenizer(planetTokenizer.nextToken(), ":", false);

            while (excessTokenizer.hasMoreElements()) {
                excess.add(excessTokenizer.nextToken());
            }

            // goals
            final List<String> goals = new ArrayList<String>();
            final StringTokenizer goalsTokenizer = new StringTokenizer(planetTokenizer.nextToken(), ":", false);

            while (goalsTokenizer.hasMoreElements()) {
                final String goal = goalsTokenizer.nextToken();
                if (!goal.equals("empty")) {
                    goals.add(goal);
                }
            }

            final World world = new World(excess, goals);

            // coords and radius
            final StringTokenizer coordsTokenizer = new StringTokenizer(planetTokenizer.nextToken(), ":", false);
            world.setLocation(
                    Float.valueOf(coordsTokenizer.nextToken()),
                    Float.valueOf(coordsTokenizer.nextToken()),
                    Float.valueOf(coordsTokenizer.nextToken()));

            world.setWorldImg(new Texture("world/" + ( new Random().nextInt(10) + 1 ) + ".png"));

            worlds.add(world);
        }

        Table table = null;
        final String jsonSkin = "font/uiskin.json";

        Skin uiSkin;
        Label label;

        switch (levelNumber ) {
            case 1:
                uiSkin = new Skin(Gdx.files.internal(jsonSkin));
                table = new Table(uiSkin);
                label = new Label("This is a world. Its name? ... Doesn't matter.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                table.row();
                label = new Label("Doesn't matter isn't in perfect harmony. The yellow field indicates its needs while the green indicates its excesses.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                table.row();
                label = new Label("Touch it to fulfill its needs.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                break;
            case 2:
                uiSkin = new Skin(Gdx.files.internal(jsonSkin));
                table = new Table(uiSkin);
                label = new Label("As you might know there are multiple world in the universe.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                table.row();
                label = new Label("The lower world craves more food like the gluttons they are.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                table.row();
                label = new Label("Luckily the upper world can provide its excesses to other worlds.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                table.row();
                label = new Label("Drag from one world to the next to transfer goods.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                table.row();
                label = new Label("The teal zone describes transferred products.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                break;
            case 3:
                uiSkin = new Skin(Gdx.files.internal(jsonSkin));
                table = new Table(uiSkin);
                label = new Label("Not all worlds are happy. The task of a higher being isn't always easy...", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                break;
            case 4:
                uiSkin = new Skin(Gdx.files.internal(jsonSkin));
                table = new Table(uiSkin);
                label = new Label("Connections are unidirectional. Think before you act.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                table.row();
                label = new Label("Or act wildly and use the restart level functionality.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                break;
            case 5:
                uiSkin = new Skin(Gdx.files.internal(jsonSkin));
                table = new Table(uiSkin);
                label = new Label("You can also transfer transferred products. Try chaining planets.", uiSkin);
                label.setWrap(true);
                table.add(label).width(( (float) Gdx.graphics.getWidth() ) * 0.8f);
                break;
        }

        return new Level(levelNumber, worlds, table);
    }
}
