package mymoves;

import ru.ifmo.se.pokemon.*;

public class ConfuseRay extends StatusMove {
    public ConfuseRay() {
        super(Type.GHOST, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);
        if (Math.random() < 0.33) {
            Effect.confuse(pokemon);
        }
    }

    @Override
    protected String describe() {
        return "использует ConfuseRay";
    }
}
