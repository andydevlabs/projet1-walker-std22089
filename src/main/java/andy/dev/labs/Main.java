package andy.dev.labs;

import andy.dev.labs.location.Location;
import andy.dev.labs.location.StreetMap;
import andy.dev.labs.location.Walker;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StreetMap streetMap = new StreetMap();

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



        Walker walker = new Walker();

        Location origin = streetMap.getLocationByName("Marais");
        Location destination = streetMap.getLocationByName("Boulevard");
        List<Location> path = walker.walk(streetMap, origin, destination);
        System.out.println("Path: " + path);
    }
}