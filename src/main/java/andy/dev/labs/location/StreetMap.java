package andy.dev.labs.location;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class StreetMap {
    private Set<Location> allLocations = new HashSet<>();

    public void addLocation(Location location) {
        allLocations.add(location);
    }

    public Location getLocationByName(String name) {
        for (Location location : allLocations) {
            if (location.getLocationName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    public void connectLocations(String streetName, String fromName, String toName) {
        Location from = getLocationByName(fromName);
        Location to = getLocationByName(toName);
        Street street = new Street(streetName);

        if (from != null && to != null) {
            from.connectLocation(street, to);
        }
    }
}
