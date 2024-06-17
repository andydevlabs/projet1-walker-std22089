package andy.dev.labs.location;


import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Location {
    private final String locationName;
    private final Map<Street, Location> connectedLocation;

    public Location(String name) {
        this.locationName = name;
        this.connectedLocation = new HashMap<>();
    }

    public void connectLocation(Street street, Location location) {
        connectedLocation.put(street, location);
    }

    public Location getConnectedLocation(Street street) {
        return connectedLocation.get(street);
    }

    public List<Street> getStreets() {
        return new ArrayList<>(connectedLocation.keySet());
    }

    @Override
    public String toString() {
        return locationName;
    }
}
