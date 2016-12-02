package de.pymob.games.jchess.engine;

import de.pymob.games.jchess.engine.figuren.Figur;

public abstract class Zelle {
    abstract boolean isOccupied();
    abstract Figur getFigur();

    static final Zelle LEERE_ZELLE = new Zelle() {
        @Override
        boolean isOccupied() {
            return false;
        }
        @Override
        Figur getFigur() {
            return null;
        }
    };

    static final class BelegteZelle extends Zelle {
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
    }
}
