package com.hammersoft.formal_languages;
import com.hammersoft.formal_languages.view.MeniuMain;

public class Main {

    public static void main(String[] args) {

        ProgramFactory factory = ProgramFactory.getInstance();

        String filePath_Markov = "src/initialization_files/Input_Data_Markov.in";
        String filePath_Gramatica = "src/initialization_files/Input_Data_Gramatica.in";
        String filePath_AFD = "src/initialization_files/Input_Data_AFD.in";

        putFactoryToWork(factory, filePath_Markov, filePath_Gramatica, filePath_AFD);

        MeniuMain.getInstance().startSession(factory.getPrograms());
    }

    public static void putFactoryToWork(ProgramFactory F, String... filePaths){
        F.startPrograms(filePaths);
    }
}
