package de.pymob.games.jchess.engine.figuren;

import de.pymob.games.jchess.engine.Allianz;
import de.pymob.games.jchess.engine.Typ;

import java.util.Arrays;
import java.util.Collection;

public class Bauer extends Figur {

    public Bauer(Allianz allianz) {
        super(Typ.BAUER, allianz);
    }

    @Override
    public Collection<Integer> getLegaleZuege() {
        return Arrays.asList(7, 8, 9, 16);
    }
}
