package de.pymob.games.jchess.engine;

public enum Typ {
    BAUER('B'), KOENIG('K'), DAME('D'), SPRINGER('S'), LAUEFER('L'), TURM('T');

    private char wert;

    Typ(char wert) {
        this.wert = wert;
    }

    public char getWert() {
        return wert;
    }

    public static Typ fromWert(char wert) {
        for (Typ typ : Typ.values()) {
            if (typ.getWert() == wert) {
                return typ;
            }
        }
        throw new IllegalArgumentException("No type found for: " + wert);
    }
}
