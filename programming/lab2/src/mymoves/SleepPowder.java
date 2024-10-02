package mymoves;

import ru.ifmo.se.pokemon.*;

public class SleepPowder extends StatusMove {
    public SleepPowder(){
        super(Type.GRASS, 0, 75 );
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        var turns = (int) (Math.random() * 2 + 1);
        Effect e = (new Effect()).condition(Status.SLEEP).turns(turns);
        p.setCondition(e);
    }
    @Override
    protected String describe(){
        return "использует SleepPowder";
    }
}
