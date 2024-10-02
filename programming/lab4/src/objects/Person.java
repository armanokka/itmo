package objects;

import exceptions.ForgotBackpackException;
import exceptions.NotFoundException;

import java.util.Objects;

public class Person {
    public String name = "";
    public int age = 10;
    public int height = 150;
    public int energy = 5;
    private Backpack backpack;

    public Person(String name, int age, int height, int energy) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.energy = energy;
    }

    public void equipBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public void putInBackpack(Object object) {
        backpack.add(object);
    }

    public void getFromBackpack(Object object) throws NotFoundException {
        backpack.get(object);
    }

    public void exploreBackpack() throws ForgotBackpackException {
        if (backpack == null) {
            throw new ForgotBackpackException(this.name);
        }
        backpack.open();
        backpack.explore();
        backpack.close();
    }

    public void exploreRoom(Room room) throws ForgotBackpackException {
        System.out.println(name + " is exploring the " + room.name + "...");
        if (room.hasArtifact()) {
                var artifact = room.collectArtifact();
                if (backpack == null) {
                    throw new ForgotBackpackException(this.name);
                }

                this.backpack.open();
                System.out.println(name + " collected a valuable artifact " + artifact.getName() + " in the " + room.name + "!");
                this.putInBackpack(artifact);
                this.backpack.close();

        } else {
            System.out.println("The " + room.name + " has no artifacts.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person opp)) {
            return false;
        }
        if ((this.name == opp.name) && (this.height == opp.height) && (this.energy == opp.energy) && (this.backpack == opp.backpack) && (this.age == opp.age) && (this.backpack.equals(opp.backpack))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height, energy, backpack);
    }
}
