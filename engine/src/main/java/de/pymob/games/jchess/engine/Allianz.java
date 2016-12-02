package de.pymob.games.jchess.engine;

public enum  Allianz {
    WEISS('W'), SCHWARZ('S');

    private final char wert;

    Allianz(char wert) {
        this.wert = wert;
    }

    public String getWert() {
        return wert + "";
    }

    public static Allianz fromWert(String wert) {
        for (Allianz allianz : Allianz.values()) {
            if (allianz.getWert().equals(wert)) {
                return allianz;
            }
        }
        throw new IllegalArgumentException("No alliance for: " + wert);
    }
}
