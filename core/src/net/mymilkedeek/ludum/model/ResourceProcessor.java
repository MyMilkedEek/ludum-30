package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.util.Properties;

/**
 * @author MyMilkedEek
 */
public class ResourceProcessor {

    /**
     *
     */
    private static Properties resourceDictionary;

    public static String addResource(String resource, World worldToAddResourceTo) {
        if ( resourceDictionary == null ) {
            resourceDictionary = new Properties();
            try {
                resourceDictionary.load(Gdx.files.internal("data/resources.properties").reader());
            } catch (IOException e) {
                // uhh yeah critical error and stuff
                Gdx.app.log("MMESPACE", "Critical error loading resources.");
                Gdx.app.exit();
            }
        }

        for ( String excess : worldToAddResourceTo.getExcesses() ) {
            String output = resourceDictionary.getProperty(resource+"."+excess);

            if ( output != null && !worldToAddResourceTo.getBonuses().contains(output) ) {
                return output;
            }
        }

        for ( String excess : worldToAddResourceTo.getBonuses() ) {
            String output = resourceDictionary.getProperty(resource+"."+excess);

            if ( output != null ) {
                return output;
            }
        }


        return null;
    }

    public static boolean removeResource(String toRemoveResource, World worldToRemoveResourceFrom) {
        String addedResource = addResource(toRemoveResource, worldToRemoveResourceFrom);

        if (addedResource != null ) {
            worldToRemoveResourceFrom.getBonuses().remove(addedResource);
            worldToRemoveResourceFrom.rebuildVisualString();
            return true;
        }

        return false;
    }

}