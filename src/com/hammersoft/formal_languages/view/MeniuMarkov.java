package com.hammersoft.formal_languages.view;
import com.hammersoft.formal_languages.language_programs.Markov;
import com.hammersoft.formal_languages.language_programs.Program;

//Singleton design pattern: Lazy initilization
public final class MeniuMarkov extends Meniu {
    private static MeniuMarkov instance = null;
    Markov M;

    private MeniuMarkov() {}

    public static MeniuMarkov getInstance() {
        if (instance == null)
            instance =  new MeniuMarkov();
        return instance;
    }

    public void startSession(Program P){
        M = (Markov) P;
        run();
    }

    private void run(){
        boolean isRunning = true;
        int option = 2; //incepem cu optiunea de afisaj selectata
        while (option != 0){
            switch (option) {
                case -1:
                    //INIT
                    System.out.print("\nSelecteaza optiunea: \n\t\"0\" - pentru iesire\n\t\"1\" - pentru a deriva o propozitie\n\t\"2\" - pentru afisarea elementelor clasei\n");
                    System.out.print("\nInput: ");
                    option = readOption();
                    break;
                case 1:
                    //CHECK
                    System.out.print("\nPrecizeaza propozitia de derivat: ");
                    M.derivare(readLine());
                    option = -1;
                    break;
                case 2:
                    //PRINT
                    M.afisare();
                    option = -1;
                    break;

                default:
                    System.out.print("\nEROARE: Optiunea selectata nu este valida!\n");
                    option = -1;
                    break;
            }
        }
    }

    public String readLine() { return super.readLine(); }
    public int readOption() { return super.readOption(); }
}
