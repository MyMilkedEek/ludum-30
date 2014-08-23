package net.mymilkedeek.ludum.model;

/**
 * @author MyMilkedEek
 */
public class ResourceProcessor {

    public static String addResource(String resource, World worldToAddResourceTo) {
        if ( resource.equalsIgnoreCase("food") ) {
            if ( worldToAddResourceTo.getExcesses().contains("food")) {
                return "gluttony";
            }
        }

        return null;
    }

    public static boolean removeResource(String toRemoveResource, World worldToRemoveResourceFrom) {
        String addedResource = addResource(toRemoveResource, worldToRemoveResourceFrom);

        if (addedResource != null ) {
            worldToRemoveResourceFrom.getBonuses().remove(addedResource);
            return true;
        }

        return false;
    }

}