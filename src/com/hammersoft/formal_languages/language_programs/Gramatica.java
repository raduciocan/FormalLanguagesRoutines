package com.hammersoft.formal_languages.language_programs;

import com.hammersoft.formal_languages.ProgramType;
import com.hammersoft.formal_languages.file_handler.FileHandler;
import com.hammersoft.formal_languages.ProgramFactory;
import com.hammersoft.formal_languages.language_programs.builder_helpers.Builder;
import com.hammersoft.formal_languages.language_programs.builder_helpers.GramaticaBuilderHelper;
import javafx.util.Pair;

import java.util.*;

public final class Gramatica implements Program {
    private boolean hasTerminator = false;
    private Set<Character> VN, VT;
    private Character S;
    private List<Pair<String, String>> rules;

    public Gramatica(GramaticaBuilder builder) {
        this.VN = builder.VN;
        this.VT = builder.VT;
        this.S = builder.S;
        this.rules = builder.rules;
    }


    public int getNumberOfRules(){
        return rules.size();
    }
    public int getVNSize(){
        return VN.size();
    }
    public int getVTSize(){
        return VT.size();
    }
    public char getStartSymbol() {
        return S;
    }

    //verificam daca terminalul/nonterminlul contine numai caractere existente in alfabetul aferent
    public boolean isNonTerminalValid(String cuvant) {
        for (char litera : cuvant.toCharArray()) {
            if (!VN.contains(litera))
                return false;
        }
        return true;
    }

    public boolean isTerminalValid(String cuvant) {
        for (char litera : cuvant.toCharArray()){
            if (!VT.contains(litera))
                return false;
        }
        return true;
    }

    //region TEST_CASES Conditiile din cerinta
    public boolean verificare(){
        boolean isValid = true;
        if (!check_1()){
            System.out.print("\n ! EROARE: Este incalcata regula 1 (intersectia VN cu VT trebuie sa fie nula) !\n");
            isValid = false;
        }
        if (!check_2()){
            System.out.print("\n ! EROARE: Este incalcata regula 2 (S trebuie sa apartina de VN) !\n");
            isValid = false;
        }
        if (!check_3()){
            System.out.print("\n ! EROARE: Este incalcata regula 3 (nu trebuie sa existe element null in VN) !\n");
            isValid = false;
        }
        if (!check_4()){
            System.out.print("\n ! EROARE: Este incalcata regula 4 (trebuie sa existe procedura cu neterminal S unic) !\n");
            isValid = false;
        }
        if (!check_5()){
            System.out.print("\n ! EROARE: Este incalcata regula 5 (productiile sa existe in VN, VT) !\n");
            isValid = false;
        }
        if (isValid)
            System.out.print("\n + INFO: Toate cele 5 reguli sunt respectate !\n");
        return isValid;
    }

    //Conditia 1: Verificam daca {VN} intersectat cu {VT} = {Multimea Vida}.
    public boolean check_1(){
        for (Pair<String, String> ruleChecker1 : rules)
            for (Pair<String, String> ruleChecker2 : rules)
                if (Objects.equals(ruleChecker1.getKey(), ruleChecker2.getValue()))
                    return false;
        return true;
    }
    //Conditia 2: Verificam daca exista simbolul 'S' in multimea neterminalilor VN.
    public boolean check_2(){
        for (Pair<String, String> rule : rules)
            if (rule.getKey().contains(S.toString()))
                return true;
        return false;
    }
    //Conditia 3: verificam daca fiecare regula are la membrul stang cel putin un neterminal.
    public boolean check_3(){
        for (Pair<String, String> rule : rules)
            if (rule.getKey().length() == 0)
                return false;
        return true;
    }
    //Conditia 4: Verificam daca exista cel putin o regula ce are membrul stang simbolul 'S' unic.
    public boolean check_4(){
        for (Pair<String, String> rule : rules)
            if (Objects.equals(rule.getKey(), S.toString()))
                return true;
        return false;
    }
    // Conditia 5: Verificm daca toate regulile se incadreaza in alfabetele corespunzatoare.
    public boolean check_5(){
        return check_VN() && check_VT();
    }
    private boolean check_VN(){
        for (Pair<String, String> rule : rules)
            for (char litera : rule.getKey().toCharArray())
                if (!VN.contains(litera)){
                    System.out.print("\n ! EROARE: Litera '" + litera + "' din nonterminalul \"" + rule.getValue() + "\" NU se gaseste in alfabetul nonterminalelor VN!\n");
                    return false;
                }
        return true;
    }
    private boolean check_VT() {
        for (Pair<String, String> regula : rules)
            for (char litera : regula.getValue().toCharArray())
                if (!VT.contains(litera)) {
                    System.out.print("\n ! EROARE: Litera '" + litera + "' din terminalul \"" + regula.getValue() + "\" NU se gaseste in alfabetul terminalelor VT!\n");
                    return false;
                }
        return true;
    }
    //endregion

    public void afisare() {
        int counter = 1;
        System.out.print("\nVocabularul nonterminalilor gramaticii (VN): \n" + getVNSize() + " caractere: \t" + VN + "\n");
        System.out.print("Vocabularul terminalilor gramaticii (Vt): \n" + getVTSize() + " caractere: \t" + VT + "\n");
        System.out.print("Simbolul de start S: \t\"" + S + "\"\n");
        System.out.print("\nRegulile de generare existente:\n");
        for (Pair<String, String> regula : rules){
            System.out.print("Regula " + counter++ + ": \n");
            System.out.print("\t\"" + regula.getKey() + "\" -> \"" + regula.getValue() + "\"\n");
        }
        verificare();
    }

    private ArrayList<Integer> checkValidProductions(String cuvant) {
        ArrayList<Integer> productii = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++){
            if (cuvant.contains(rules.get(i).getKey())) {
                productii.add(i);
            }
        }
        return productii;
    }

    private String applyProduction(String propozitie, int productionID) {
        propozitie = propozitie.replaceFirst(rules.get(productionID).getKey(), rules.get(productionID).getValue());
        return propozitie;
    }

    private int getRandom(int max){
        Random r = new Random();
        return r.nextInt(max);
    }

    public void generare() {
        String propozitie = S.toString();
        ArrayList<Integer> productiiValide = checkValidProductions(propozitie);
        int step = 1, selectedProduction;

        while (productiiValide.size() > 0){
            selectedProduction = productiiValide.get(getRandom(productiiValide.size()));
            System.out.print("\nDerivatia " + step + " (folosind regula #" + selectedProduction + "): ");
            System.out.print("\n\"" + propozitie + "\"\t=>");
            propozitie = applyProduction(propozitie, selectedProduction);
            System.out.print("\t\"" + propozitie + "\"\n");
            productiiValide = checkValidProductions(propozitie);
            step++;
        }

    }

    //Builder design pattern
    public static class GramaticaBuilder implements Builder {
        FileHandler init = ProgramFactory.getInstance().getFileHandler(ProgramType.GRAMATICA);
        private boolean hasTerminator;
        private Set<Character> VN, VT;
        private Character S;
        private List<Pair<String, String>> rules;
        public GramaticaBuilder() {
            this.VN = GramaticaBuilderHelper.getAlphabet(init);
            this.VT = GramaticaBuilderHelper.getAlphabet(init);
            this.S = GramaticaBuilderHelper.getStartSymbol(init);
            this.rules = GramaticaBuilderHelper.getProductions(init);
            updateTerminator();
        }

        //verificam daca exista regula de substitutie ce contine caracterul punt '.' (simbolul terminarii progrmului)
        private void updateTerminator() {
            for (Pair<String, String> regula : rules)
                if (regula.getValue().length() > 0)
                    hasTerminator = regula.getValue().toCharArray()[0] == '.';
        }

        @Override
        public Program build() {
            return new Gramatica(this);
        }
    }
}
