package com.hammersoft.formal_languages.language_programs.builder_helpers;

import com.hammersoft.formal_languages.file_handler.FileHandler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MarkovBuilderHelper {

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

    public static List<Pair<String, String>> getRules(FileHandler F){
        List<Pair<String, String>> ruleBook = new ArrayList<>();
        String linie;

        while(F.hasNextLine()){
            linie = F.getNextLine();

            String[] conditii = linie.split("->", 2);

            if(conditii[1].equals(""))
                conditii[1]=".";

            ruleBook.add(new Pair<>(conditii[0], conditii[1]));
        }
        return ruleBook;
    }

}
