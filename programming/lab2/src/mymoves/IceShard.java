package mymoves;

import ru.ifmo.se.pokemon.*;

public class IceShard extends PhysicalMove {
    public IceShard() {super(Type.ICE, 40, 100);}

    @Override
    protected void applyOppDamage(Pokemon pokemon, double v) {pokemon.setMod(Stat.HP, (int) v);}
    @Override
    protected void applySelfEffects(Pokemon p) { // библиотека не расчитана на выставление приоритетов атак. оставим метод пустым}
    }
    protected String describe(){
        return "использует IceShard";
    }

}
