package de.pymob.games.jchess.engine.figuren;

import de.pymob.games.jchess.engine.Allianz;
import de.pymob.games.jchess.engine.Position;
import de.pymob.games.jchess.engine.Typ;

import java.util.Arrays;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class Bauer extends Figur {

    public Bauer(Allianz allianz) {
        super(Typ.BAUER, allianz);
    }

    @Override
    public Collection<Integer> getLegaleZuege() {
        return Arrays.asList(7, 8, 9, 16);
    }

    @Override
    public Collection<Position> getPfad(Position from, Position to) {
        SortedSet<Position> pfad = new TreeSet<>();
        int deviation = from.getDeviation(to);
        if (isZweiVorwaerts(deviation)) {
            // add 8
            pfad.add(Position.fromIndex(from.getIndex() + 8));
        }
        return pfad;
    }

    @Override
    public boolean kannBewegen(int differenz) {
        return differenz == 8 || differenz == 16;
    }

    @Override
    public boolean kannSchlagen(int differenz) {
        return differenz == 7 || differenz == 9;
    }

    @Override
    public boolean kannSpringen() {
        return false;
    }

    private boolean isEinVorwaerts(int differenz) {
        return differenz == 8;
    }

    private boolean isZweiVorwaerts(int differenz) {
        return differenz == 16;
    }

}
