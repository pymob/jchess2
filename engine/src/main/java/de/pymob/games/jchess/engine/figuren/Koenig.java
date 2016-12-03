package de.pymob.games.jchess.engine.figuren;

import de.pymob.games.jchess.engine.Allianz;
import de.pymob.games.jchess.engine.Position;
import de.pymob.games.jchess.engine.Typ;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Koenig extends Figur {
    private static final Collection<Integer> zuege =
            Arrays.asList(7, 8, 9, -1, 1, -7, -8, -9);

    public Koenig(Allianz allianz) {
        super(Typ.KOENIG, allianz);
    }

    @Override
    public Collection<Integer> getLegaleZuege() {
        return zuege;
    }

    @Override
    public Collection<Position> getPfad(Position from, Position to) {
        return Collections.emptyList();
    }

    @Override
    public boolean kannBewegen(int differenz) {
        return zuege.contains(differenz);
    }

    @Override
    public boolean kannSchlagen(int differenz) {
        return kannBewegen(differenz);
    }

    @Override
    public boolean kannSpringen() {
        return false;
    }
}
