package de.pymob.games.jchess.engine;

import de.pymob.games.jchess.engine.figuren.Figur;

public class Spiel implements ISpiel {
    private static final String ANFANGSSTELLUNG = "A2BW,B2BW,C2BW,D2BW,E2BW,F2BW,G2BW,H2BW," +
            "A7BS,B7BS,C7BS,D7BS,E7BS,F7BS,G7BS,H7BS";
    private final Spielfeld spielfeld;

    public Spiel() {
        this(ANFANGSSTELLUNG, "W");
    }

    public Spiel(String stellung, String spielerAmZug) {
        spielfeld = new Spielfeld();
    }

    public Figur[][] getSpielfeld() {
        return spielfeld.toArray();
    }

    public String getFigur(String pos) {
        return null;
    }

    public boolean ziehe(String startpos, String zielpos) {
        return false;
    }
}
