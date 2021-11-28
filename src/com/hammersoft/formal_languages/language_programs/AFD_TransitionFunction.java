package com.hammersoft.formal_languages.language_programs;

import java.util.Set;

public class AFD_TransitionFunction {
    boolean isValid;
    int nrTransitions;
    Set<AFD_Transition> transitions;

    AFD_TransitionFunction(Set<AFD_Transition> transitions){
        this.transitions = transitions;
        nrTransitions = transitions.size();
        isValid = false;
    }
    AFD_TransitionFunction(Set<AFD_Transition> transitions, Set<Character> alphabet){
        this.transitions = transitions;
        nrTransitions = transitions.size();
        isValid = isValid(alphabet);
    }

    public int getNumberOfTransitions() {
        return nrTransitions;
    }

    public boolean isValid(Set<Character> alphabet){
        for(AFD_Transition tranzitie : transitions)
            if (!alphabet.contains(tranzitie.getValueOfState()))
                return false;
        return true;
    }

    class BlockStateReached extends Exception{
        public BlockStateReached(int currentState, Character symbol){
            super(" ! EROARE: Automatul a ajuns intr-un state din care nu poate iesi!\t(Starea <" + currentState + "> cu simbolul '" + symbol + "')\n");
        }
    }

    public int advance(int fromState, char withSymbol) throws BlockStateReached{
        int toState;
        for(AFD_Transition tranzitie : transitions){
            if(tranzitie.getFromState() == fromState && tranzitie.getValueOfState() == withSymbol)
                return tranzitie.getToState();
        }
        throw new BlockStateReached(fromState, withSymbol);
    }

    public void afisare() {
        int counter = 1;
        System.out.print("\nRegulile de generare existente (#"+transitions.size()+"):\n");
        for(AFD_Transition tranzitie : transitions){
            System.out.print("Tranzitia " + counter++ + ": \n");
            System.out.print("State (" + tranzitie.getFromState() + ") --to--> State (" + tranzitie.getToState() + ") using value '" + tranzitie.getValueOfState() + "'\n");
        }
    }

}
