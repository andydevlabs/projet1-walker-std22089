package andy.dev.labs.location;

import java.util.*;

public class Walker {


    public List<Location> walk(StreetMap streetMap, String startName, String destinationName) {
        Location start = streetMap.getLocationByName(startName);
        Location destination = streetMap.getLocationByName(destinationName);
        if (start == null || destination == null) {
            return new ArrayList<>();
        }
        return walk(streetMap, start, destination);
    }

    public List<Location> walk(StreetMap streetMap, Location start, Location destination) {
        List<Location> path = new ArrayList<>();
        Set<Location> visitedLocations = new HashSet<>();
        if (walkProcess(start, destination, visitedLocations, path)) {
            return path;
        }
        return new ArrayList<>();
    }

    private boolean walkProcess(Location currentLocation, Location destination, Set<Location> visitedLocations, List<Location> path) {
        path.add(currentLocation);
        visitedLocations.add(currentLocation);

        if (currentLocation.equals(destination)) {
            return true;
        }

        for (Street street : currentLocation.getStreets()) {
            Location nextLocation = currentLocation.getConnectedLocation(street);
            if (!visitedLocations.contains(nextLocation)) {
                if (walkProcess(nextLocation, destination, visitedLocations, path)) {
                    return true;
                }
            }
        }

        path.removeLast();
        return false;
    }
}
