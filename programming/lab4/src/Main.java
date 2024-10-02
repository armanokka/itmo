import exceptions.ForgotBackpackException;
import interfaces.Collectable;
import objects.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var Neznaika = new Person("Neznaika", 10, 150, 10);
        Neznaika.equipBackpack(new Backpack(Neznaika));
        var Pilulkin = new Person("Pilulkin", 11, 155, 8);
        Pilulkin.equipBackpack(new Backpack(Pilulkin));
        var Vintik = new Person("Vintik", 10, 130, 15);
        Vintik.equipBackpack(new Backpack(Vintik));
        var Baby = new Person("Baby", 7, 129, 3);
        Baby.equipBackpack(new Backpack(Baby));

        var crew = new ArrayList<Person>();
        crew.add(Neznaika);
        crew.add(Pilulkin);
        crew.add(Vintik);
        crew.add(Baby);
        var expedition = new Expedition("New Eagle", crew);
        var mission = expedition.new Mission("Find as many artifacts as we can");
        var room1 = new Room(new Collectable() {
            public String getName() {
                return "Eye of Time";
            }
            public int getDate() {
                return 200;
            }
            public String toString() {
                return this.getName() + " dated " + this.getDate() + " years ago";
            }
        }, "Living room");
        var room2 = new Room(new Collectable() {
            public String getName() {
                return "Keys of wisdom";
            }
            public int getDate() {
                return 1214;
            }
            public String toString() {
                return this.getName() + " dated " + this.getDate() + " years ago";
            }
        }, "Bedroom");
        var room3 = new Room(new Collectable() {
            public String getName() {
                return "Blade Of Eternity";
            }
            public int getDate() {
                return 2000;
            }
            public String toString() {
                return this.getName() + " dated " + this.getDate() + " years ago";
            }
        }, "Attic");
        var room4 = new Room(null, "Basement");
        var room5 = new Room(null, "Laundry room");

        var room6 = new Room(new Collectable() {
            public String getName() {
                return "Ancestor's Star";
            }
            public int getDate() {
                return 200;
            }
            public String toString() {
                return this.getName() + " dated " + this.getDate() + " years ago";
            }
        }, "Living room");
        var room7 = new Room(null, "Guest room");
        var room8 = new Room(null, "Kitchen");
        var room9 = new Room(null, "Bedroom 1");
        var room10 = new Room(new Collectable() {
            public String getName() {
                return "Amulet Of Power";
            }
            public int getDate() {
                return 500;
            }
            public String toString() {
                return this.getName() + " dated " + this.getDate() + " years ago";
            }
        }, "Bedroom 2");
        var room11 = new Room(null, "Dining room");

        var building1 = new Building(new Room[]{room1, room2, room3, room4, room5});
        var building2 = new Building(new Room[]{room6, room7, room8, room9, room10, room11});

        var city = new City("IceLand", new Building[]{building1, building2});
        expedition.prepareExpedition();
        System.out.println();
        expedition.startExpedition(city);

        class ExplorationReport {
            public void generateReport() {
                System.out.println("\nMISSION OVERVIEW:\n");
                for (Person member: crew) {
                    try {
                        member.exploreBackpack();
                    } catch (ForgotBackpackException e) {
                        continue;
                    }
                    System.out.println();
                }
            }
        }
        new ExplorationReport().generateReport();
        mission.completeMission();
    }
}
