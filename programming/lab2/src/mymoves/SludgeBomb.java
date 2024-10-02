package mymoves;

import ru.ifmo.se.pokemon.*;

public class SludgeBomb extends SpecialMove {
    public SludgeBomb(){
        super(Type.POISON, 90, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        p.setMod(Stat.HP, (int) damage);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        if (Math.random() < 0.3) {
            Effect.poison(p);
        }
    }

    @Override
    protected String describe(){
        return "использует SludgeBomb";
    }
}
