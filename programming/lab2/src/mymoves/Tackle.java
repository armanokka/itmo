package mymoves;

import ru.ifmo.se.pokemon.*;

public class Tackle extends PhysicalMove {
    public Tackle(){
        super(Type.NORMAL, 40, 100 );
    }
    @Override
    protected void applyOppDamage(Pokemon pokemon, double v) {
        pokemon.setMod(Stat.HP, (int) v);
    }

    @Override
    protected String describe(){
        return "использует Tackle";
    }
}
