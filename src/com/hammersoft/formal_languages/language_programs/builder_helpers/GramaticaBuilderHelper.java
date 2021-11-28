package com.hammersoft.formal_languages.language_programs.builder_helpers;

import com.hammersoft.formal_languages.file_handler.FileHandler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GramaticaBuilderHelper {

    public static Set<Character> getAlphabet(FileHandler F){

        Set<Character> alphabetSet = new HashSet<>();
        String alphabet;

        if(F.hasNextLine()) {
            alphabet = F.getNextLine();

            //caracterul de terminare punt '.' trebuie permanent in alfabet
            alphabetSet.add('.');
            //daca exista 2 spatii libere invecinare sau un spatiu intre virgule, utilizatorul doreste caracterul spatiu in alfabet
            if (alphabet.contains("  ") || alphabet.contains(", ,"))
                alphabetSet.add(' ');

            alphabet = alphabet.replace(" ", "");
            alphabet = alphabet.replace(",", "");
            for (char litera : alphabet.toCharArray()) {
                alphabetSet.add(litera);
            }
        }
        else System.out.print("\n ! EROARE: Fisierul nu contine alfabetul definit");

        return alphabetSet;
    }

    public static List<Pair<String, String>> getProductions(FileHandler F){
        List<Pair<String, String>> ruleBook = new ArrayList<>();
        int nrRules = 0;
        String linie;

        while(F.hasNextLine()){
            linie = F.getNextLine();

            /*Daca este declarat numarul de proceduri in fisier ignoram randul
              Salvam totusi nr-ul pentru eventuale verificari
              Implementarea actuala este superioara*/
            if(linie.matches("-?(0|[1-9]\\d*)")) {
                nrRules = Integer.parseInt(linie);
                linie = F.getNextLine();
            }

            if(linie.contains("->")) {
                String[] conditii = linie.split("->", 2);
                ruleBook.add(new Pair<>(conditii[0], conditii[1]));
            }
            else {
                String[] conditii = linie.split(" ", 2);
                ruleBook.add(new Pair<>(conditii[0], conditii[1]));
            }

        }
        return ruleBook;
    }

    public static Character getStartSymbol(FileHandler F) {
        String S = "";
        if(F.hasNextLine()) {
            S = F.getNextLine();
            if(S.length() > 1){
                System.out.print("\n ! EROARE: Simbolul de 'Start' nu este un caracter unic!");
                System.out.print("\n + INFO: Simbolul 'Start' fost initializat cu primul caracter din linia citita");
            }
        }
        else System.out.print("\n ! EROARE: Fisierul nu contine alfabetul definit");

        return S.toCharArray()[0];
    }
}
