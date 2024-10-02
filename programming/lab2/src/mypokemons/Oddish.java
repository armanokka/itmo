package mypokemons;

import mymoves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Oddish extends Pokemon {
    public Oddish(String name, int level) {
        super(name, level);
        setStats(45, 50, 55, 75, 65, 30);
        setType(Type.GRASS, Type.POISON);

        super.setMove(new SludgeBomb(), new MegaDrain());
    }
}
