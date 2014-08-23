package net.mymilkedeek.ludum.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import net.mymilkedeek.ludum.model.Level;
import net.mymilkedeek.ludum.model.World;

import java.util.ArrayList;
import java.util.List;
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

            while ( goalsTokenizer .hasMoreElements()) {
                goals.add(goalsTokenizer .nextToken());
            }

            World world = new World(excess, goals);

            // coords and radius
            StringTokenizer coordsTokenizer = new StringTokenizer(planetTokenizer.nextToken(), ":", false);
            world.setLocation(
                    Float.valueOf(coordsTokenizer.nextToken()),
                    Float.valueOf(coordsTokenizer.nextToken()),
                    Float.valueOf(coordsTokenizer.nextToken()));

            worlds.add(world);
        }

        Level level = new Level(levelNumber, worlds);

        return level;
    }
}