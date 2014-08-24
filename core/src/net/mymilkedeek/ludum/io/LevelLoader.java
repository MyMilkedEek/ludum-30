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
 * @author MyMilkedEek
 */
public class LevelLoader {

    public static Level loadLevel(int levelNumber) {

        String levelId = String.valueOf(levelNumber);

        if ( levelNumber < 10 ) {
            levelId = "0"+levelId;
        }

        FileHandle internal = Gdx.files.internal("saves/" + levelId + ".mme");
        String fileContent = internal.readString();

        // splits on planets
        StringTokenizer stringTokenizer = new StringTokenizer(fileContent, "||", false);

        List<World> worlds = new ArrayList<World>();

        while ( stringTokenizer.hasMoreElements()) {
            String newPlanetString = stringTokenizer.nextToken();

            StringTokenizer planetTokenizer = new StringTokenizer(newPlanetString, ";", false);

            // excess
            List<String> excess = new ArrayList<String>();
            StringTokenizer excessTokenizer = new StringTokenizer(planetTokenizer.nextToken(), ":", false);

            while ( excessTokenizer.hasMoreElements()) {
                excess.add(excessTokenizer.nextToken());
            }

            // goals
            List<String> goals = new ArrayList<String>();
            StringTokenizer goalsTokenizer = new StringTokenizer(planetTokenizer.nextToken(), ":", false);

            while ( goalsTokenizer.hasMoreElements()) {
                String goal = goalsTokenizer.nextToken();
                if ( goal != "empty" ) {
                    goals.add(goal);
                }
            }

            World world = new World(excess, goals);

            // coords and radius
            StringTokenizer coordsTokenizer = new StringTokenizer(planetTokenizer.nextToken(), ":", false);
            world.setLocation(
                    Float.valueOf(coordsTokenizer.nextToken()),
                    Float.valueOf(coordsTokenizer.nextToken()),
                    Float.valueOf(coordsTokenizer.nextToken()));

            world.setWorldImg(new Texture("world/" + (new Random().nextInt(10)+1) + ".png"));

            worlds.add(world);
        }

        Table table = null;

        if ( levelNumber == 1 ) {
            Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));
            table = new Table(uiSkin);
            Label label = new Label("This is a world. Its name? ... Doesn't matter.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
            table.row();
            label = new Label("Doesn't matter isn't in perfect harmony. The yellow field indicates its needs while the green indicates its excesses.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
            table.row();
            label = new Label("Touch it to fulfill its needs.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
        } else if ( levelNumber == 2 ) {
            Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));
            table = new Table(uiSkin);
            Label label = new Label("As you might know there are multiple world in the universe.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
            table.row();
            label = new Label("The lower world craves more food like the gluttons they are.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
            table.row();
            label = new Label("Luckily the upper world can provide its excesses to other worlds.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
            table.row();
            label = new Label("Drag from one world to the next to transfer goods.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
            table.row();
            label = new Label("The teal zone describes transferred products.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
        } else if ( levelNumber == 3 ) {
            Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));
            table = new Table(uiSkin);
            Label label = new Label("Not all worlds are happy. The task of a higher being isn't always easy...", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
        } else if ( levelNumber == 5 ) {
            Skin uiSkin = new Skin(Gdx.files.internal("font/uiskin.json"));
            table = new Table(uiSkin);
            Label label = new Label("You can also transfer transferred products. Try chaining planets.", uiSkin);
            label.setWrap(true);
            table.add(label).width(((float)Gdx.graphics.getWidth())*0.8f);
        }

        Level level = new Level(levelNumber, worlds, table);



        return level;
    }
}
