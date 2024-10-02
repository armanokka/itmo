package mypokemons;

import mymoves.MegaDrain;
import mymoves.SleepPowder;
import mymoves.SludgeBomb;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Gloom extends Oddish {
    public Gloom(String name, int level) {
        super(name, level);
        super.setStats(60, 65, 70, 85, 75, 40);
        super.setType(Type.GRASS, Type.POISON);

        setMove(new SludgeBomb(), new MegaDrain(), new SleepPowder());
    }
}
