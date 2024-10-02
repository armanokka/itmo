package mymoves;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest(){
        super(Type.PSYCHIC, 0, 0 );
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        Effect effect = (new Effect()).condition(Status.SLEEP).turns(2);
        p.setMod(Stat.HP, (int) -73.00);
        p.setCondition(effect);
    }

    @Override
    protected String describe(){
        return "использует Rest";
    }
}
