package de.pymob.games.jchess.engine;

import de.pymob.games.jchess.engine.figuren.Figur;

import static de.pymob.games.jchess.engine.utils.Parser.allianz;
import static de.pymob.games.jchess.engine.utils.Parser.neuesSpielfeld;
import static de.pymob.games.jchess.engine.utils.Parser.position;

public class Spiel implements ISpiel {
    private static final String ANFANGSSTELLUNG = "A2BW,B2BW,C2BW,D2BW,E2BW,F2BW,G2BW,H2BW," +
            "A7BS,B7BS,C7BS,D7BS,E7BS,F7BS,G7BS,H7BS";
    private final Spielfeld spielfeld;
    private Allianz spieler;

    public Spiel() {
        this(ANFANGSSTELLUNG, "W");
    }

    public Spiel(String stellung, String spielerAmZug) {
        spielfeld = neuesSpielfeld(stellung);
        spieler = allianz(spielerAmZug);
    }

    public Figur[][] getSpielfeld() {
        return spielfeld.toArray();
    }

    public String getFigur(String pos) {
        return spielfeld.get(pos).toString();
    }

    public boolean ziehe(String startpos, String zielpos) {
        // Vorbedingungen
        if (!spielfeld.get(startpos).isOccupied()) return false;
        if (spielfeld.isFigurVomGegner(position(zielpos), spieler)) return false;
        Figur startFigur = spielfeld.get(startpos).getFigur();
        Integer deviation = position(startpos).getDeviation(position(zielpos));
        if (!startFigur.getLegaleZuege().contains(deviation)) return false;
        // legaler Zug
        spielfeld.bewegeFigur(position(startpos), position(zielpos));
        return true;
    }

    @Override
    public String toString() {
        return spielfeld.toString();
    }

}
