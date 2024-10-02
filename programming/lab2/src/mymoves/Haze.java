package mymoves;

import ru.ifmo.se.pokemon.*;

public class Haze extends StatusMove {
    public Haze(){
        super(Type.ICE, 0, 0 );
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        p.restore();
    }

    @Override
    protected String describe(){
        return "использует Haze";
    }
}
