package mypokemons;

import mymoves.MegaDrain;
import mymoves.SleepPowder;
import mymoves.SludgeBomb;
import ru.ifmo.se.pokemon.Type;

public class Vileplume extends Gloom {
    {

    }
    public Vileplume(String name, int level) {
        super(name, level);
        setStats(75, 80, 85,110, 90, 50);
        setType(Type.GRASS, Type.POISON);

        setMove(new SludgeBomb(), new MegaDrain(), new SleepPowder());
    }
}
