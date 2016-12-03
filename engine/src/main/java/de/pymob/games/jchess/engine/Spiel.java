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
        return spielfeld.get(position(pos)).toString();
    }

    public boolean ziehe(String startpos, String zielpos) {
        Position startPosition = position(startpos);
        Position endPosition = position(zielpos);
        Zelle startZelle = spielfeld.get(startPosition);
        Zelle zielZelle = spielfeld.get(endPosition);
        // Vorbedingungen
        // ist start belegt
        if (!startZelle.isOccupied()) return false;
        // ist ziel gegner-figur oder leer
        if (zielZelle.isOccupied() &&
                !spielfeld.isFigurVomGegner(endPosition, spieler)) return false;
        Figur startFigur = startZelle.getFigur();
        // ist start figur vom aktuellen Spieler
        if (!startFigur.getAllianz().equals(spieler)) return false;
        Integer deviation = startPosition.getDeviation(endPosition);
        // wäre zug zulässig
        if (!startFigur.getLegaleZuege().contains(deviation)) return false;
        System.out.println(deviation);
        if (zielZelle.isOccupied()) {
           if (!startFigur.kannSchlagen(deviation)) return false;
        } else {
            if (!startFigur.kannBewegen(deviation)) return false;
        }
        // pfad prüfen, ob wer im Weg steht
        if (!startFigur.kannSpringen()) {
            for (Position position : startFigur.getPfad(startPosition, endPosition)) {
                if (spielfeld.get(position).isOccupied()) return false;
            }
        }
        // legaler Zug
        spielfeld.bewegeFigur(startPosition, endPosition);
        return true;
    }

    @Override
    public String toString() {
        return spielfeld.toString();
    }

}
