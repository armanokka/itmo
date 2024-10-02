package mymoves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class MegaDrain extends SpecialMove {
    public MegaDrain() {super(Type.GRASS, 40, 100);}
    @Override
    public void applyOppDamage(Pokemon p, double damage) {
        p.setMod(Stat.HP, (int) damage);
    }
    @Override
    public String describe() {
        return "использует MegaDrain";
    }
}
