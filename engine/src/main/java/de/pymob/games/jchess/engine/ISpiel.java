package de.pymob.games.jchess.engine;

public interface ISpiel extends IRSpiel {
    String getFigur(String pos);
    boolean ziehe(String startpos, String zielpos);
}
