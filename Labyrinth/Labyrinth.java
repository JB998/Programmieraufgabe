package Labyrinth;

import java.util.List;
import java.util.stream.Stream;

public class Labyrinth {
    private static final String WEG = ".";
    private static final String STEIN = "#";
    private static final String START = "S";
    private static final String ENDE = "E";

    private String[][][] labyrinth;

    // L ist die Anzahl der Ebenen
    private int l ;
    // R ist die Länge
    private int r ;
    // C die Breite
    private int c ;
    private boolean[][][] besucht;
    private Koordinate start;
    private Koordinate ende;


    /**
     * Erstellt Labyrinth basierend auf einer Reihe von Beschreibungen
     * Bsp. :
     * 1 3 3
     * S##
     * #E#
     * ###
     *
     * @param input Beschreibung
     */
    public Labyrinth(String input) {
        Stream<String> lines = input.lines();
        List<String> linesList = lines.toList();
        String[] size = linesList.get(0).split(" ");
        l = Integer.valueOf(size[0]);
        r = Integer.valueOf(size[1]);
        c = Integer.valueOf(size[2]);
        labyrinth = new String[l][r][c];
        besucht = new boolean[l][r][c];


        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                String line = linesList.get(1 + j + (r+1) * i);
                for (int k = 0; k < c; k++) {
                    labyrinth[i][j][k] =  String.valueOf(line.charAt(k));
                    // Finde Startposition
                    if(labyrinth[i][j][k].equals(START) ){
                        start = new Koordinate(i, j,k);
                    }
                    // Finde Endposition
                    if(labyrinth[i][j][k].equals(ENDE) ){
                        ende = new Koordinate(i, j,k);
                    }

                }
            }
        }

    }


    public int getLaenge() {
        return r;
    }

    public int getBreite() {
        return c;
    }

    public int getEbenen() {
        return l;
    }

    public Koordinate getStart() {
        return start;
    }

    public Koordinate getEnde() {
        return ende;
    }

    public boolean isEnde(int x, int y, int z) {
        return x == ende.getX() && y == ende.getY() && z == ende.getZ();
    }

    public boolean isStart(int x, int y, int z) {
        return x == start.getX() && y == start.getY() && z == start.getZ();
    }

    public boolean isBesucht(int x, int y, int z) {
        return besucht[x][y][z];
    }

    public boolean isStein(int x, int y, int z) {
        return labyrinth[x][y][z].equals(STEIN) ;
    }

    public void setBesucht(int x, int y, int z, boolean wert) {
        besucht[x][y][z] = wert;
    }

    public boolean isValidePosition(int x, int y, int z) {
        if (x < 0 || x >= getEbenen() || y < 0 || y >= getLaenge()|| z < 0 || z >= getBreite()) {
            return false;
        }
        return true;
    }

}
