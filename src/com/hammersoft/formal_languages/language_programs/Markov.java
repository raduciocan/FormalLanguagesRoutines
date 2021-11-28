package com.hammersoft.formal_languages.language_programs;
import com.hammersoft.formal_languages.ProgramType;
import com.hammersoft.formal_languages.file_handler.FileHandler;
import com.hammersoft.formal_languages.ProgramFactory;
import com.hammersoft.formal_languages.language_programs.builder_helpers.Builder;
import com.hammersoft.formal_languages.language_programs.builder_helpers.MarkovBuilderHelper;
import javafx.util.Pair;

import java.util.List;
import java.util.Set;

public final class Markov implements Program {
    private boolean hasTerminator;
    private Set<Character> alphabet;
    private List<Pair<String, String>> rules;

    private Markov(MarkovBuilder builder) {
        this.hasTerminator = builder.hasTerminator;
        this.alphabet = builder.alphabet;
        this.rules = builder.rules;
    }
    public boolean hasTerminator() { return hasTerminator; }
    public int getNumberOfRules(){
        return rules.size();
    }
    public int getAlphabetSize(){
        return alphabet.size();
    }
    public Set<Character> getAlphabet() {
        return alphabet;
    }

    //verificam daca cuvantul contine numai caractere existente alfabet
    public boolean isValid(String cuvant){
        for (Character litera : cuvant.toCharArray())
            if (!alphabet.contains(litera))
                return false;
        return true;
    }

    //algoritmul principal de inlocuire Markov
    public void derivare(String cuvant){
        if (isValid(cuvant)){
            System.out.print("\n\n\nPropozitia initiala: \"" + cuvant + "\"\n");

            int stepCounter = 1;
            for (int i = 0; i < rules.size(); i++){
                if (cuvant.contains(rules.get(i).getKey())){

                    System.out.print("\nInlocuirea #" + stepCounter + " (regula " + (i + 1) + "):\t \""+rules.get(i).getKey()+"\"\t->\t\""+rules.get(i).getValue()+"\"");
                    cuvant = cuvant.replaceFirst(rules.get(i).getKey(), rules.get(i).getValue());
                    System.out.print(": " + cuvant);

                    //daca atingem terminatorul ne oprim
                    if (hasTerminator && i == rules.size() - 1) {
                        System.out.print("\n + INFO: Program terminat datorita intalnirii caracterului punct '.' !\n");
                        return;
                    }
                    stepCounter++;
                    i = 0;
                }
            }
            if (stepCounter == 1) System.out.print("\n ! EROARE: Nu exista regula de derivare aplicabila!\n");
            System.out.print("\n\n + INFO: Program finished!\n");
        }
        else System.out.print("\n ! EROARE: Cuvantul contine caractere ce nu se afla in alfabet!");
    }

    public void afisare(){
        int counter = 1;
        System.out.print("Alfabetul aferent clasei Markov: \n" + alphabet);
        System.out.print("\nRegulile de derivare existente:\n");
        for (Pair<String, String> regula : rules)
        {
            System.out.print("Regula " + counter++ +": \n");
            System.out.print("\t\"" + regula.getKey() + "\" -> \"" + regula.getValue() + "\"\n");
        }
    }

    //Builder design pattern
    public static class MarkovBuilder implements Builder {
        FileHandler init = ProgramFactory.getInstance().getFileHandler(ProgramType.MARKOV);
        private boolean hasTerminator = false;
        private Set<Character> alphabet;
        private List<Pair<String, String>> rules;

        public MarkovBuilder() {
            this.alphabet = MarkovBuilderHelper.getAlphabet(init);
            this.rules = MarkovBuilderHelper.getRules(init);
            updateTerminator();
        }
        //verificam daca exista substitutie ce contine caracterul punt '.' (simbolul terminarii progrmului)
        private void updateTerminator(){
            for (Pair<String, String> rule : rules)
                if (rule.getValue().length() > 0)
                    hasTerminator = rule.getValue().charAt(0) == '.';
        }

        @Override
        public Program build() {
            return new Markov(this);
        }
    }
}
