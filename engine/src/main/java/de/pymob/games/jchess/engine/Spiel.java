package de.pymob.games.jchess.engine;

import de.pymob.games.jchess.engine.figuren.Figur;

public class Spiel implements ISpiel {

    public Spiel() {
    }

    public Spiel(String stellung, String spielerAmZug) {

    }

    public Figur[][] getSpielfeld() {
        return new Figur[0][];
    }

    public String getFigur(String pos) {
        return null;
    }

    public boolean ziehe(String startpos, String zielpos) {
        return false;
    }
}
