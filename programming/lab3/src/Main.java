import objects.*;
public class Main {
    public static void main(String[] args) {
        DebrisMountain debrisMountain = new DebrisMountain("Mountain of Debris");
        House firstHouse = new House("First Ancient House");
        Corridor corridor = new Corridor("Arch-shaped Corridor");

        Paper paper1 = new Paper("Paper 1");
        Paper paper2 = new Paper("Paper 2");

        DenfortsBackpack denfortsBackpack = new DenfortsBackpack("Denfort's Backpack");
        denfortsBackpack.addPaper(paper1);
        denfortsBackpack.addPaper(paper2);

        // Пример использования объектов и их методов
        System.out.println(debrisMountain);
        System.out.println(firstHouse);
        System.out.println(corridor);
        System.out.println(denfortsBackpack);
    }
}
