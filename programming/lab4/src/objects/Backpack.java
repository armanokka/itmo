package objects;

import exceptions.NotFoundException;
import interfaces.Collectable;
import interfaces.Container;

import java.util.ArrayList;
import java.util.Objects;
// Класс, представляющий рюкзак Денфорта

public class Backpack implements Container {
    private boolean opened = false;
    protected ArrayList<Object> items = new ArrayList<>();
    private final Person owner;

    // TODO: equals, hashcode, toString
    public Backpack(Person owner) {
        this.owner = owner;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Backpack)) {
            return false;
        }
        var opp = (Backpack) obj;
        if ((this.opened == opp.opened) && (this.items == opp.items) && (this.owner == opp.owner)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(opened, items, owner);
    }

    // Метод для открытия рюкзака
    public void open() {
        System.out.println("Opening the backpack of " + owner.name);
        opened = true;
    }
    public void close() {
        System.out.println("Closing the backpack of "+ owner.name);
        opened = false;
    }
    @Override
    public void add(Object obj) {
        this.items.add(obj);
    }

    @Override
    public Object get(Object obj) throws NotFoundException {
        if (this.items.contains(obj)) {
            throw new NotFoundException(obj);
        }
        return obj;
    }

    @Override
    public void explore() {
        if (!opened) {
            System.out.println("Can't explore the backpack, it's closed!");
            return;
        }
        System.out.println("Exploring the backpack of " + owner.name);
        for (Object object: this.items) {
            String description = "";
            if (object instanceof Collectable) {
                var c = (Collectable) object;
                description = City.ArtifactCatalog.getArtifactDescription(c.getName());
            }
            System.out.println("Found: " + object.toString() + ". " + description);
        }
    }
}
