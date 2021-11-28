package com.hammersoft.formal_languages.language_programs;

import com.hammersoft.formal_languages.ProgramType;
import com.hammersoft.formal_languages.file_handler.FileHandler;
import com.hammersoft.formal_languages.ProgramFactory;
import com.hammersoft.formal_languages.language_programs.builder_helpers.AFDBuilderHelper;
import com.hammersoft.formal_languages.language_programs.builder_helpers.Builder;

import java.util.Set;

public final class AFD implements Program {
    private int numberOfStates, numberOfFinalStates, numberOfSymbols, numberOfTransitions;
    private int initState;
    private AFD_TransitionFunction functie;
    Set<Integer> states;
    Set<Integer> finalStates;
    Set<Character> symbols;

    private AFD(AFDBuilder builder) {
        this.states = builder.states;
        this.initState = builder.initState;
        this.finalStates = builder.finalStates;
        this.symbols = builder.symbols;
        this.functie = builder.functie;
        this.numberOfStates = builder.numberOfStates;
        this.numberOfFinalStates = builder.numberOfFinalStates;
        this.numberOfSymbols = builder.numberOfSymbols;
        this.numberOfTransitions = builder.numberOfTransitions;
    }

    public int verificare(String cuvant){
        int currentState = initState;
        for(char litera : cuvant.toCharArray()){
            try {
                currentState = functie.advance(currentState, litera);
            }
            catch (AFD_TransitionFunction.BlockStateReached e) {
                System.out.print(e);
                return -1;
            }
        }
        return finalStates.contains(currentState) ? 1 : 0;
    }

    public void afisare(){
        System.out.print("\nStarile automatului: \t" + states);
        System.out.print("\nAlfabetul simbolurilor: \t" + symbols);
        System.out.print("\nStarile finale: \t" + finalStates);
        System.out.print("\nStarea initiala: \t" + initState);
        functie.afisare();
    }

    //Builder design pattern
    public static class AFDBuilder implements Builder {
        FileHandler init = ProgramFactory.getInstance().getFileHandler(ProgramType.AFD);
        private int numberOfStates, numberOfFinalStates, numberOfSymbols, numberOfTransitions;
        private Set<Integer> states;
        private int initState;
        private Set<Integer> finalStates;
        private Set<Character> symbols;
        private Set<AFD_Transition> rules;
        private AFD_TransitionFunction functie;

        public AFDBuilder() {
            this.states = AFDBuilderHelper.getSymbols(Integer.class, init);
            this.initState = AFDBuilderHelper.getInteger(init);
            this.finalStates = AFDBuilderHelper.getSymbols(Integer.class, init);
            this.symbols = AFDBuilderHelper.getSymbols(Character.class, init);
            this.rules = AFDBuilderHelper.getRules(init);
            this.functie = new AFD_TransitionFunction(rules, symbols);
            updateSizeCounters();
        }

        private void updateSizeCounters() {
            numberOfStates = states.size();
            numberOfFinalStates = finalStates.size();
            numberOfSymbols = symbols.size();
            numberOfTransitions = functie.getNumberOfTransitions();
        }

        private boolean isValid() {
            return checkStates() && checkNotNull();
        }
        private boolean checkStates() {
            if (!states.contains(initState))
                return false;
            for(int state : finalStates)
                if (!states.contains(state))
                    return false;
            return true;
        }
        private boolean checkNotNull() {
            return numberOfStates > 0 && numberOfFinalStates > 0 && numberOfSymbols > 0 && numberOfTransitions > 0;
        }

        @Override
        public Program build(){
            Program AFD = new AFD(this);
            if(!isValid()) System.out.print("\n ! EROARE: Formatul automtului incarcat nu este valid!\n");
            return AFD;
        }
    }
}