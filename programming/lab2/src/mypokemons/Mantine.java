package mypokemons;

import mymoves.Bubble;
import mymoves.Haze;
import mymoves.RockTomb;
import mymoves.Tackle;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Mantine extends Pokemon {
    public Mantine(String name, int level) {
        super(name, level);
        setStats(85, 40, 70, 80, 140,70);
        setType(Type.WATER, Type.FLYING);

        super.setMove(new Bubble(), new Tackle(), new Haze(), new RockTomb());
    }
}
