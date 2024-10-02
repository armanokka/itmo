package mypokemons;

import mymoves.ConfuseRay;
import mymoves.Rest;
import mymoves.Waterfall;
import mymoves.Waterfall;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Lapras extends Pokemon {
    public Lapras(String name, int level) {
        super(name, level);
        setStats(130, 85, 80,85, 95, 60);
        setType(Type.WATER, Type.ICE);

        super.setMove(new Waterfall(), new ConfuseRay(), new Rest());
    }
}
