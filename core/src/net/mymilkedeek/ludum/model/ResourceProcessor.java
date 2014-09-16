package net.mymilkedeek.ludum.model;

import com.badlogic.gdx.Gdx;

import java.io.IOException;
import java.util.Properties;

/**
 * Loads and retrieves the combinations of resources.
 *
 * @author MyMilkedEek
 */
public class ResourceProcessor {

    /**
     * The map of combinations.
     */
    private static Properties resourceDictionary;

    /**
     * Adds one resource to another.
     *
     * @param resource resource that needs to be added
     * @param worldToAddResourceTo the world that the resource is added to
     * @return a new resource
     */
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

        for ( final String excess : worldToAddResourceTo.getExcesses() ) {
            final String output = resourceDictionary.getProperty(resource+"."+excess);

            if ( output != null && !worldToAddResourceTo.getBonuses().contains(output) ) {
                return output;
            }
        }

        for ( final String excess : worldToAddResourceTo.getBonuses() ) {
            final String output = resourceDictionary.getProperty(resource+"."+excess);

            if ( output != null ) {
                return output;
            }
        }


        return null;
    }

    /**
     * Removes a resource from the provided world.
     *
     * @param toRemoveResource the resource that needs to be removed.
     * @param worldToRemoveResourceFrom the world that the resource is going to be removed from
     * @return removal success
     */
    public static boolean removeResource(String toRemoveResource, World worldToRemoveResourceFrom) {
        final String addedResource = addResource(toRemoveResource, worldToRemoveResourceFrom);

        if (addedResource != null ) {
            worldToRemoveResourceFrom.getBonuses().remove(addedResource);
            worldToRemoveResourceFrom.rebuildVisualString();
            return true;
        }
        return false;
    }
}
