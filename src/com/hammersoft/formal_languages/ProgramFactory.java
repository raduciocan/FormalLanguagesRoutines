package com.hammersoft.formal_languages;

import com.hammersoft.formal_languages.file_handler.FileHandler;
import com.hammersoft.formal_languages.language_programs.AFD;
import com.hammersoft.formal_languages.language_programs.Gramatica;
import com.hammersoft.formal_languages.language_programs.Markov;
import com.hammersoft.formal_languages.language_programs.Program;

import java.util.HashMap;
import java.util.Map;

//Singleton design pattern: Eager initilization
//Factory creational design pattern
public class ProgramFactory {
    private static ProgramFactory instance = new ProgramFactory();
    private static Map<ProgramType, FileHandler> programStartFiles = new HashMap<>();
    private static Map<ProgramType, Program> activePrograms = new HashMap<>();

    private ProgramFactory() {}
    public static ProgramFactory getInstance() { return instance; }

    public static boolean hasFileHandler(ProgramType type){
        return programStartFiles.containsKey(type);
    }
    public static boolean isActive(ProgramType type){
        return activePrograms.containsKey(type);
    }
    public static FileHandler getFileHandler(ProgramType type) {
        return programStartFiles.get(type);
    }
    public Map<ProgramType, Program> getPrograms() {
        return activePrograms;
    }

    private void initiateFileHandler(ProgramType type, String filePath) {
        FileHandler F = new FileHandler(filePath);
        F.openFile();
        if(F.isValid()){
            programStartFiles.put(type, F);
        }
    }

    private void createProgramsInitializationFiles(String... filePaths){
        int iterator = 0;
        for(ProgramType type : ProgramType.values()){
            if(!filePaths[iterator].isEmpty()){
                initiateFileHandler(type, filePaths[iterator++]);
            }
        }
    }

    private Program buildProgram(ProgramType type) {
        switch (type) {
            case MARKOV:
                return new Markov.MarkovBuilder().build();
            case GRAMATICA:
                return new Gramatica.GramaticaBuilder().build();
            case AFD:
                return new AFD.AFDBuilder().build();
        }
        return null;
    }
    public void startPrograms(String... filePath) {
        createProgramsInitializationFiles(filePath);
        for(Map.Entry<ProgramType, FileHandler> entry : programStartFiles.entrySet()){
            activePrograms.put(entry.getKey(), buildProgram(entry.getKey()));
        }
    }

}
