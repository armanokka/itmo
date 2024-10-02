package objects;

import exceptions.ForgotBackpackException;
import exceptions.NoMembersException;

import java.util.ArrayList;
import java.util.Objects;

public class Expedition {
    public String name;
    private final ArrayList<Person> members;

    public Expedition(String name, ArrayList<Person> members) {
        this.name = name;
        this.members = members;
    }

    protected Person getRandomMember() {
        var min = 0;
        var max = this.members.size()-1;
        int randomIdx = min + (int)(Math.random() * ((max - min) + 1));
        return this.members.get(randomIdx);
    }

    public void prepareExpedition() throws NoMembersException {

        var kicked = new ArrayList<Person>();
        for (Person person: members) {
            if ((person.age < 5)
                    || (person.height < 100)
                    || (person.energy < 5)) {
                System.out.println(person.name + " is not going to expedition");
                kicked.add(person);
                continue;
            }
            System.out.println(person.name + " is going to expedition");
        }
//        for (Person person: kicked) {
//            this.members.remove(person);
//        }
        if (this.members.isEmpty()) {
            throw new NoMembersException();
        }
    }

    protected void sendHome(Person person) {
        this.members.remove(person);
    }

    public void startExpedition(City city) {
        System.out.println("Expedition " + name + " entered " + city.name);
        for (Building building: city.buildings) {
            System.out.println("Expedition entered building");
            for (Room room: building.rooms) {
                var member = this.getRandomMember();
                try {
                    member.exploreRoom(room);
                } catch (ForgotBackpackException e) {
                    this.sendHome(member);
                }
            }
            System.out.println("Expedition left building\n");
        }
        System.out.println("Expedition finished successfully!");
    }

    public class Mission {
        private final String missionName;
        private boolean isCompleted;

        public Mission(String missionName) {
            this.missionName = missionName;
            this.isCompleted = false;
        }

        // Метод для выполнения миссии
        public void completeMission() {
            if (!isCompleted) {
                System.out.println("Mission \"" + missionName + "\" completed!");
                isCompleted = true;
            } else {
                System.out.println("Mission \"" + missionName + "\" has already been completed.");
            }
        }
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Expedition)) {
//            return false;
//        }
//        var opp = (Expedition) obj;
//        if ((this.name == opp.name) && (this.members == opp.members)) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name, members);
    }
}
