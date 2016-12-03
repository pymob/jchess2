package de.pymob.games.jchess.engine;

import java.util.Collection;
import java.util.stream.Collectors;

public enum  Allianz {
    WEISS('W') {
        @Override
        public Collection<Integer> calculateMoves(Collection<Integer> baseMoves) {
            return baseMoves;
        }

        @Override
        public int applySignum(int to) {
            return to;
        }
    }, SCHWARZ('S') {
        @Override
        public Collection<Integer> calculateMoves(Collection<Integer> baseMoves) {
            return baseMoves.stream()
                    .mapToInt(this::applySignum)
                    .boxed()
                    .collect(Collectors.toList());
        }

        @Override
        public int applySignum(int to) {
            return -to;
        }
    };

    private final char wert;

    Allianz(char wert) {
        this.wert = wert;
    }

    public abstract Collection<Integer> calculateMoves(Collection<Integer> baseMoves);
    public abstract int applySignum(int to);
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
