package de.pymob.games.jchess.engine;

import de.pymob.games.jchess.engine.figuren.Figur;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import static de.pymob.games.jchess.engine.utils.Parser.position;

public class Spielfeld {
    private final EnumMap<Position, Zelle> map;

    public Spielfeld() {
        map = new EnumMap<>(Position.class);
        Arrays.stream(Position.values())
                .forEach(position -> map.put(position, Zelle.LEERE_ZELLE));
    }

    public Zelle get(String pos) {
        return map.get(position(pos));
    }

    public void add(Position key, Figur value) {
        map.put(key, new Zelle.BelegteZelle(value));
    }

    public boolean isFigurVomGegner(Position position, Allianz aktuellerSpieler) {
        Zelle zelleAnPosition = map.get(position);
        return (zelleAnPosition != Zelle.LEERE_ZELLE) &&
                (!zelleAnPosition.getFigur().getAllianz().equals(aktuellerSpieler));
    }

    /**
     * Setzt eine Figur um.
     * @param start Position der ziehenden Figur
     * @param ende aufdie zu ziehende Position
     * @return die geschlagene Figur, oder {@code null}, wenn dort keine stand
     * @throws IllegalArgumentException bei einer ungültigen Anweisung
     */
    public Figur bewegeFigur(Position start, Position ende) throws IllegalArgumentException {
        Zelle startZelle = map.get(start);
        if (!startZelle.isOccupied()) {
            throw new IllegalArgumentException("Cannot move a figure which doesn't exist!");
        } else {
            Zelle geschlageneZelle = map.replace(ende, startZelle);
            map.replace(start, Zelle.LEERE_ZELLE);
            if (geschlageneZelle.isOccupied()) {
                return geschlageneZelle.getFigur();
            } else {
                return null;
            }
        }
    }

    public Figur[][] toArray() {
        Figur[][] figuren = new Figur[8][8];
        for (Map.Entry<Position, Zelle> entry : map.entrySet()) {
            int index = entry.getKey().getIndex();
            figuren[index / 8][index % 8] = entry.getValue().getFigur();
        }
        return figuren;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
