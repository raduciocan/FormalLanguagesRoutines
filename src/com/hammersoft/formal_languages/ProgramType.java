package com.hammersoft.formal_languages;

import com.hammersoft.formal_languages.language_programs.Program;

public enum ProgramType {
    MARKOV("markov"),
    GRAMATICA("gramatica"),
    AFD("afd");

    ProgramType(String label) {}

    public static ProgramType getByClassType(Program P){
        switch (P.getClass().toString()){
            case "Markov":
                return MARKOV;
            case "Gramatica":
                return GRAMATICA;
            case "AFD":
                return AFD;
            default:
                return null;

        }
    }
}
