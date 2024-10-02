package objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City {
    public String name;
    public Building[] buildings;

    public City(String name, Building[] buildings) {
        this.name = name;
        this.buildings = buildings;
    }

    public static class ArtifactCatalog {
        private static Map<String, String> artifacts = new HashMap<>();
        static {
            // Заполним каталог начальными артефактами
            artifacts.put("Ancient Amulet", "A mysterious amulet with ancient symbols.");
            artifacts.put("Crystal Skull", "A crystal skull believed to have mystical powers.");
            artifacts.put("Lunar Relic", "An ancient relic associated with lunar energy.");
            artifacts.put("Eye of Time", "A magic eye that can predict the future");
            artifacts.put("Keys of wisdom", "The owner of this knowledge will find get to know the sence of life");
            artifacts.put("Blade Of Eternity", "The blade that was found near the dormitory of scary Ender dragon");
        }
        // Метод для получения описания артефакта
        public static String getArtifactDescription(String artifactName) {
            return artifacts.get(artifactName);
        }
    }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof City)) {
                return false;
            }
            var opp = (City) obj;
            if ((this.name == opp.name) && (this.buildings == opp.buildings)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, buildings);
        }
}
