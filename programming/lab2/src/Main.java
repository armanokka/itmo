import mypokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;


public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon a1 = new Lapras("Lapras", 1);
        Pokemon a2 = new Mantyke("Mantyke", 1);
        Pokemon a3 = new Mantine("Mantine", 1);

        Pokemon b1 = new Oddish("Oddish", 1);
        Pokemon b2 = new Gloom("Gloom", 1);
        Pokemon b3 = new Vileplume("Vileplume", 1);

        b.addAlly(a1);
        b.addAlly(a2);
        b.addAlly(a3);

        b.addFoe(b1);
        b.addFoe(b2);
        b.addFoe(b3);
        b.go();
    }

    public static boolean chance(double c) {
        return (1 - c) >= Math.random();
    }
}