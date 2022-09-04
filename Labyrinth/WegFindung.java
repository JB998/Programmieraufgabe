package Labyrinth;

import java.io.BufferedInputStream;
import java.util.*;

public class WegFindung {
    private static int[][] RICHTUNGEN
            = { { 1 , 0, 0 }, { -1, 0, 0 }, { 0, 1,0 }, { 0,-1, 0 },{ 0,0, 1 },{ 0,0, -1 } };

    /**
     * Ermittelt den Pfad durch den Parent
     * @param koord
     * @return Pfad von Koordinaten
     */

    private List<Koordinate> ermittlePfad(
            Koordinate koord) {
        List<Koordinate> pfad = new ArrayList<>();

        Koordinate parentKoordinate = koord;

        while (parentKoordinate != null) {
            pfad.add(parentKoordinate);
            parentKoordinate = parentKoordinate.getParent();
        }

        return pfad;
    }

    /**
     * Berechnet für ein Labyrinth den kuerzesten Pfad vom Start zum Ziel
     * @param labyrinth das Labyrinth
     * @return den kürzesten Pfad vom Start zum Ziel
     */
    public List<Koordinate> berechneKuerzestenWeg(Labyrinth labyrinth) {
        LinkedList<Koordinate> offeneKoordinaten
                = new LinkedList<>();
        Koordinate start = labyrinth.getStart();
        offeneKoordinaten.add(start);

        while (!offeneKoordinaten.isEmpty()) {
            Koordinate koord = offeneKoordinaten.remove();

            // Pruefe ob Koordinate zulässig
            if (!labyrinth.isValidePosition(koord.getX(), koord.getY(), koord.getZ())
                    || labyrinth.isBesucht(koord.getX(), koord.getY(), koord.getZ())
            ) {
                continue;
            }

            if (labyrinth.isStein(koord.getX(), koord.getY(),koord.getZ())) {
                labyrinth.setBesucht(koord.getX(), koord.getY(),koord.getZ(), true);
                continue;
            }

            if (labyrinth.isEnde(koord.getX(), koord.getY(),koord.getZ())) {
                // Weg gefunden
                return ermittlePfad(koord);
            }

            // Füge alle neuen Richtungen hinzu
            for (int[] direction : RICHTUNGEN) {
                Koordinate coordinate
                        = new Koordinate(
                        koord.getX() + direction[0],
                        koord.getY() + direction[1],
                        koord.getZ() + direction[2],
                        koord
                );
                offeneKoordinaten.add(coordinate);
                labyrinth.setBesucht(koord.getX(), koord.getY(),koord.getZ(), true);
            }
        }
        // kein Weg gefunden
        return Collections.emptyList();
    }




    public static void main(String[] args){

        System.out.println("Eingabe Reihe Beschreibungen von Labyrinth (Eingabe pro Zeile; Ende mit '0 0 0')");
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        List<String> lines = new ArrayList<>();
        // Eingabe
        while (stdin.hasNextLine()) {

            String line = stdin.nextLine();
            if (line.equals("0 0 0")){
                break;
            }
            lines.add(line);
        }

        List<String> labyrinthBeschreibungListe = new ArrayList<String>();
        // Erstelle aus den eingegebenen Zeilen die Labyrinth-Beschreibungen
        StringBuilder labyrinthBeschreibung = new StringBuilder();
        for (String line: lines) {
            if(!line.isEmpty() && Character.isDigit(line.charAt(0))){
                if (!labyrinthBeschreibung.toString().equals("")){
                    labyrinthBeschreibungListe.add(labyrinthBeschreibung.toString());
                }
                labyrinthBeschreibung = new StringBuilder();
            }
            labyrinthBeschreibung.append(line+ "\n");


        }
        labyrinthBeschreibungListe.add(labyrinthBeschreibung.toString());

        // Berechne für jede Labyrinth-Beschreibung den kürzesten Weg vom Start zum Ziel (Wenn es ein Weg gibt)
        for (String labyrinthBeschreibungString: labyrinthBeschreibungListe) {
            Labyrinth labyrinth = new Labyrinth(labyrinthBeschreibungString);
            WegFindung wegFindung = new WegFindung();
            List<Koordinate> weg = wegFindung.berechneKuerzestenWeg(labyrinth);
            if (weg.size() == 0){
                System.out.println("Gefangen :-(");

            }else {
                System.out.println("Entkommen in "+(weg.size()-1)+" Minute(n)!");
            }
        }




    }





}


