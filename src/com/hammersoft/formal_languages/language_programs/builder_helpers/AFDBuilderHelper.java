package com.hammersoft.formal_languages.language_programs.builder_helpers;

import com.hammersoft.formal_languages.file_handler.FileHandler;
import com.hammersoft.formal_languages.language_programs.AFD_Transition;

import java.util.HashSet;
import java.util.Set;

public class AFDBuilderHelper {

    public static <T> Set<T> getSymbols(Class<T> type, FileHandler F){
        Set<T> symbols = new HashSet<>();
        String linie;
        if(F.hasNextLine()) {
            linie = F.getNextLine();
            if (!linie.isEmpty()) {
                if (type == Character.class) {
                    linie = linie.replace(" ", "");
                    for (Character litera : linie.toCharArray())
                        symbols.add((T) litera);
                } else if (type == Integer.class) {
                    String[] valori = linie.split(" ");
                    for (String valoare : valori)
                        symbols.add((T) (Integer) Integer.parseInt(valoare));

                } else
                    System.out.print("\n ! EROARE: tipul setului de simboluri poate fi doar <Integer> sau <Character>!");

            }
        }
        else System.out.print("\n ! EROARE: Fisierul nu contine alfabetul definit");
        return symbols;
    }

    public static Set<AFD_Transition> getRules(FileHandler F){
        Set<AFD_Transition> ruleBook = new HashSet<>();
        AFD_Transition tranzitie;
        String linie;

        while(F.hasNextLine()) {
            linie = F.getNextLine();
            if(!linie.isEmpty()) {
                String[] conditii = linie.split(" ");
                for (String conditie : conditii)
                    ruleBook.add(new AFD_Transition(Integer.parseInt(conditii[0]), Integer.parseInt(conditii[2]), conditii[1].charAt(0)));
            }
        }
        return ruleBook;
    }

    public static int getInteger(FileHandler F){
        if(F.hasNextLine())
            return Integer.parseInt(F.getNextLine());
        return -1;
    }
    public static char getCharacter(FileHandler F){
        return F.hasNextLine() ? F.getNextLine().charAt(0) : Character.MIN_VALUE;
    }
}
