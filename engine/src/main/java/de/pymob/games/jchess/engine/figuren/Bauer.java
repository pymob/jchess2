package de.pymob.games.jchess.engine.figuren;

import de.pymob.games.jchess.engine.Allianz;
import de.pymob.games.jchess.engine.Position;
import de.pymob.games.jchess.engine.Typ;

import java.util.*;

public class Bauer extends Figur {
    private static final List<Integer> zuege = Arrays.asList(7, 8, 9, 16);

    public Bauer(Allianz allianz) {
        super(Typ.BAUER, allianz);
    }

    @Override
    public Collection<Integer> getLegaleZuege() {
        return allianz.calculateMoves(zuege);
    }

    @Override
    public Collection<Position> getPfad(Position from, Position to) {
        SortedSet<Position> pfad = new TreeSet<>();
        int deviation = from.getDeviation(to);
        if (isZweiVorwaerts(deviation)) {
            // add 8
            pfad.add(Position.fromIndex(from.getIndex() + allianz.applySignum(8)));
        }
        return pfad;
    }

    @Override
    public boolean kannBewegen(int differenz) {
        if (differenz > 0) {
            return differenz == 8 || differenz == 16;
        } else {
            return differenz == -8 || differenz == -16;
        }
    }

    @Override
    public boolean kannSchlagen(int differenz) {
        if (differenz > 0) {
            return differenz == 7 || differenz == 9;
        } else {
            return differenz == -7 || differenz == -9;
        }
    }

    @Override
    public boolean kannSpringen() {
        return false;
    }

    private boolean isEinVorwaerts(int differenz) {
        return Math.abs(differenz) == 8;
    }

    private boolean isZweiVorwaerts(int differenz) {
        return Math.abs(differenz) == 16;
    }

}
