package objects;

import java.util.Objects;

public class Building {
    public Room[] rooms;

    public Building(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Building)) {
            return false;
        }
        var opp = (Building) obj;
        if (this.rooms == opp.rooms) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rooms);
    }
}
