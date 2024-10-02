package mymoves;

import ru.ifmo.se.pokemon.*;

public class Bubble extends SpecialMove {
    int x;
    public Bubble(){
        super(Type.WATER, 40, 100 );
    }

    @Override
    protected void applyOppDamage(Pokemon pokemon, double v) {
        pokemon.setMod(Stat.HP, (int) v);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            p.setMod(Stat.SPEED, -1);
            Effect.flinch(p);
        }
    }

    @Override
    protected String describe(){
        return "использует Bubble";
    }
}
