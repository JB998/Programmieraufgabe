package Fibonacci;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {


    /**
     * Iterative Berechnung von Fibonacci Zahl
     * @param n
     * @return Fibonacci Zahl zur Zahl n.
     *         BigInteger, da Ergebnis bei zu hohen n größer als Int oder Long
     */
    public static BigInteger calcFibonacci(int n) {
        BigInteger fib = BigInteger.ZERO;
        BigInteger alteZahl = BigInteger.ONE;
        for(int i=0; i<n; i++) {
            BigInteger temp = fib;
            fib = fib.add(alteZahl);
            alteZahl = temp;
        }
        return fib;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Eingabe (Eine Zahl pro Zeile), leere Zeile = stopp");
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        List<String> lines = new ArrayList<>();
        // Eingabe
        while (stdin.hasNextLine()) {

          String line = stdin.nextLine();
          if (line.equals("")){
              break;
          }
            lines.add(line);
       }
        // Ausgabe
        for (String line: lines) {
            System.out.println("Die Fibonacci Zahl für " + Integer.valueOf(line) + " ist: " + calcFibonacci(Integer.valueOf(line)));
        }

    }
}
