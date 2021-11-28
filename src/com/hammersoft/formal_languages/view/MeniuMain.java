package com.hammersoft.formal_languages.view;

import com.hammersoft.formal_languages.ProgramType;
import com.hammersoft.formal_languages.language_programs.Program;

import java.util.Map;

//Singleton design pattern: Lazy initilization
public final class MeniuMain extends Meniu{
    private static MeniuMain instance = null;
    private Map<ProgramType, Program> programs;
    private MeniuMain() {
    }

    public static MeniuMain getInstance() {
        if (instance == null)
            instance =  new MeniuMain();
        return instance;
    }

    public void startSession(Map<ProgramType, Program> programs){
        this.programs = programs;
        run();
    }

    private void run(){
        boolean isRunning = true;
        int option = -1;
        while (option != 0){
            switch (option) {
                case -1:
                    //INIT
                    System.out.print("\nSelecteaza programul dorit: \n\t\"0\" - pentru iesire\n\t\"1\" - Build Markov\n\t\"2\" - Build Grammar\n\t\"3\" - Build AFD\n");
                    System.out.print("\nInput: ");
                    option = readOption();
                    break;
                case 1:
                    //CHECK
                    System.out.print("\nSubrutina Markov: \n");
                    if(programs.containsKey(ProgramType.MARKOV)){
                        MeniuMarkov.getInstance().startSession(programs.get(ProgramType.MARKOV));
                    }
                    else
                        System.out.print("\n - EROARE: Fisierul de initializare a subprogramului Markov nu exista!\n");
                    option = -1;
                    break;
                case 2:
                    //PRINT
                    System.out.print("\nSubrutina Gramatica: \n");
                    if(programs.containsKey(ProgramType.GRAMATICA)){
                        MeniuGramatica.getInstance().startSession(programs.get(ProgramType.GRAMATICA));
                    }
                    else
                        System.out.print("\n - EROARE: Fisierul de initializare a subprogramului verificator de gramatica nu exista!\n");
                    option = -1;
                    break;
                case 3:
                    //
                    System.out.print("\nSubrutina AFD: \n");
                    if(programs.containsKey(ProgramType.AFD)){
                        MeniuAFD.getInstance().startSession(programs.get(ProgramType.AFD));
                    }
                    else
                        System.out.print("\n - EROARE: Fisierul de initializare a subprogramului automatului deterministic finit nu exista!\n");
                    option = -1;
                    break;
                default:
                    System.out.print("\nEROARE: Optiunea selectata nu este valida!\n");
                    option = -1;
                    break;
            }
        }
    }
    public String readLine(){
        return super.readLine();
    }
    public int readOption(){
        return super.readOption();
    }
}
