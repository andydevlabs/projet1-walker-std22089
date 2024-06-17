package andy.dev.labs.location;

import java.util.*;

public class Walker {


    public List<Location> walk(StreetMap streetMap, Location start, Location destination) {
        List<Location> path = new ArrayList<>();
        Set<Location> visitedLocations = new HashSet<>();
        if (walkProcess(start, destination, visitedLocations, path)) {
            return path;
        }
        return new ArrayList<>(); // Return an empty path if no path is found
    }

    // Depth-First Search (DFS)
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
