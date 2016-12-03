package de.pymob.games.jchess.engine.utils;

import de.pymob.games.jchess.engine.Allianz;
import de.pymob.games.jchess.engine.Typ;
import de.pymob.games.jchess.engine.figuren.Bauer;
import de.pymob.games.jchess.engine.figuren.Figur;
import de.pymob.games.jchess.engine.figuren.Koenig;

public final class FigurFactory {
    private FigurFactory() {}

    public static Figur create(Typ typ, Allianz allianz) {
        switch (typ) {
            case BAUER:
                return new Bauer(allianz);
            case KOENIG:
                return new Koenig(allianz);
            case DAME:
                break;
            case SPRINGER:
                break;
            case LAUEFER:
                break;
            case TURM:
                break;
            default: throw new IllegalArgumentException("No figure mapped to: " + typ);
        }
        // TODO factory
        return null;
    }
}
