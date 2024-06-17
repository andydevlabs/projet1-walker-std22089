package location;

import andy.dev.labs.location.Location;
import andy.dev.labs.location.Street;
import andy.dev.labs.location.StreetMap;
import andy.dev.labs.location.Walker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WalkerTest {
    private StreetMap streetMap;
    private Walker walker;

    @BeforeEach
    public void setUp() {
        streetMap = new StreetMap();
        walker = new Walker();

        Location HEI = new Location("HEI");
        Location Pullman = new Location("Pullman");
        Location Balancoire = new Location("Balancoire");
        Location Sekolintsika = new Location("Sekolintsika");
        Location ESTI = new Location("ESTI");
        Location Boulevard = new Location("Boulevard");
        Location Marais = new Location("Marais");

        streetMap.addLocation(HEI);
        streetMap.addLocation(Pullman);
        streetMap.addLocation(Balancoire);
        streetMap.addLocation(Sekolintsika);
        streetMap.addLocation(ESTI);
        streetMap.addLocation(Boulevard);
        streetMap.addLocation(Marais);

        streetMap.connectLocations("Andriatsihoarana", "HEI", "Pullman");
        streetMap.connectLocations("Andriatsihoarana", "Pullman", "HEI");
        streetMap.connectLocations("HEIToSekolintsika", "HEI", "Sekolintsika");
        streetMap.connectLocations("HEIToSekolintsika", "Sekolintsika", "HEI");
        streetMap.connectLocations("HEIToBalancoire", "HEI", "Balancoire");
        streetMap.connectLocations("HEIToBalancoire", "Balancoire", "HEI");
        streetMap.connectLocations("Ranaivo", "Pullman", "Balancoire");
        streetMap.connectLocations("Ranaivo", "Balancoire", "Pullman");
        streetMap.connectLocations("UnnamedStreet", "Balancoire", "ESTI");
        streetMap.connectLocations("UnnamedStreet", "ESTI", "Balancoire");
        streetMap.connectLocations("BoulevardESTI", "Boulevard", "ESTI");
        streetMap.connectLocations("BoulevardESTI", "ESTI", "Boulevard");
        streetMap.connectLocations("BalancoireBoulevard", "Balancoire", "Boulevard");
        streetMap.connectLocations("BalancoireBoulevard", "Boulevard", "Balancoire");
        streetMap.connectLocations("MASE", "Marais", "Sekolintsika");
        streetMap.connectLocations("MASE", "Sekolintsika", "Marais");
    }

    @Test
    public void testEachLocationInPathIsConnected() {
        List<Location> path = walker.walk(streetMap, "HEI", "ESTI");
        assertFalse(path.isEmpty(), "Path should not be empty");
        assertNotNull(path, "Path should not be null");

        for (int i = 0; i < path.size() - 1; i++) {
            Location current = path.get(i);
            Location next = path.get(i + 1);
            assertTrue(areConnected(current, next), "Locations " + current + " and " + next + " should be connected by a street");
        }
    }

    private boolean areConnected(Location locationA, Location locationB) {
        for (Street street : locationA.getStreets()) {
            if (locationA.getConnectedLocation(street).equals(locationB)) {
                return true;
            }
        }
        return false;
    }
}
