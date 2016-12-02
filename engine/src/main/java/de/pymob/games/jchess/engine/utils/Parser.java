package de.pymob.games.jchess.engine.utils;

import de.pymob.games.jchess.engine.*;
import de.pymob.games.jchess.engine.figuren.Figur;

import java.util.AbstractMap.SimpleEntry;

public final class Parser {
    private Parser() {}

    public static Position position(String of) {
        return Position.valueOf(of);
    }

    public static Allianz allianz(String of) {
        return Allianz.fromWert(of);
    }

    public static Typ typ(String of) {
        return Typ.fromWert(of.charAt(0));
    }

    public static Spielfeld neuesSpielfeld(String stellung) {
        Spielfeld spielfeld = new Spielfeld();
        for (String string : stellung.split(",")) {
            SimpleEntry<Position, Figur> entry = neueFigur(string);
            spielfeld.add(entry.getKey(), entry.getValue());
        }
        return spielfeld;
    }

    private static SimpleEntry<Position, Figur> neueFigur(String of) {
        Position position = position(of.substring(0,2));
        Typ typ = typ(of.substring(2, 3));
        Allianz allianz = allianz(of.substring(3, 4));
        return new SimpleEntry<>(position, FigurFactory.create(typ, allianz));
    }
}
