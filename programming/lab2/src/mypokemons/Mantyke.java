package mypokemons;

import mymoves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Mantyke extends Mantine {
    public Mantyke(String name, int level) {
        super(name, level);
        setStats(45, 20, 50, 60, 120, 50);
        setType(Type.WATER, Type.FLYING);

        super.setMove(new Bubble(), new Tackle(), new Haze());
    }
}
