package de.pymob.games.jchess.engine.figuren;

import de.pymob.games.jchess.engine.Allianz;
import de.pymob.games.jchess.engine.Typ;

import java.util.Collection;

public abstract class Figur {
    private final Typ typ;
    private final Allianz allianz;

    protected Figur(Typ typ, Allianz allianz) {
        this.typ = typ;
        this.allianz = allianz;
    }

    public abstract Collection<Integer> getLegaleZuege();

    public Typ getTyp() {
        return typ;
    }

    public Allianz getAllianz() {
        return allianz;
    }


    @Override
    public String toString() {
        return typ.getWert() + "" + allianz.getWert();
    }
}
