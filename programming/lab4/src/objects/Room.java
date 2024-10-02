package objects;

import interfaces.Collectable;

import java.util.Objects;

public class Room {
    public String name;
    protected boolean explored = false;
    public Collectable collectable;
    public Room(Collectable collectable, String name) {
        if (collectable != null) {
            this.collectable = collectable;
        }
        this.name = name;
    }

    public boolean hasArtifact() {
        return this.collectable != null;
    }

    public Collectable collectArtifact() {
        if (this.explored) {
            return null;
        }
        this.explored = true;
        return collectable;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Room)) {
            return false;
        }
        var opp = (Room) obj;
        if ((this.name == opp.name) && (this.explored == opp.explored) && (this.collectable == opp.collectable)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, explored, collectable);
    }
}
