package de.pymob.games.jchess.engine;

import de.pymob.games.jchess.engine.figuren.Figur;

public abstract class Zelle {
    abstract boolean isOccupied();
    abstract Figur getFigur();
    public abstract String toString();

    public static final Zelle LEERE_ZELLE = new Zelle() {
        @Override
        boolean isOccupied() {
            return false;
        }
        @Override
        Figur getFigur() {
            return null;
        }
        @Override
        public String toString() {
            return "";
        }
    };

    public static final class BelegteZelle extends Zelle {
        private final Figur figur;

        public BelegteZelle(Figur figur) {
            this.figur = figur;
        }

        @Override
        boolean isOccupied() {
            return true;
        }

        @Override
        Figur getFigur() {
            return figur;
        }

        @Override
        public String toString() {
            return figur.toString();
        }
    }
}
