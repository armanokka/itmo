package mymoves;

import ru.ifmo.se.pokemon.*;

public class RockTomb extends PhysicalMove {
    public RockTomb(){
        super(Type.ROCK, 60, 95);
    }

    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        p.setMod(Stat.HP, (int) damage);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.SPEED, -1);
    }

    @Override
    protected String describe(){
        return "использует RockTomb";
    }
}
