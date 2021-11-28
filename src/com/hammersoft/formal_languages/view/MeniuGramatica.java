package com.hammersoft.formal_languages.view;
import com.hammersoft.formal_languages.language_programs.Program;
import com.hammersoft.formal_languages.language_programs.Gramatica;

//Singleton design pattern: Lazy initilization
public final class MeniuGramatica extends Meniu {
    private static MeniuGramatica instance = null;
    Gramatica G;

    private MeniuGramatica() {}

    public static MeniuGramatica getInstance() {
        if (instance == null)
            instance =  new MeniuGramatica();
        return instance;
    }

    public void startSession(Program P){
        G = (Gramatica) P;
        run();
    }

    private void run(){
        boolean isRunning = true;
        int option = 2; //incepem cu optiunea de afisaj selectata
        while (option != 0){
            switch (option) {
                case -1:
                    //INIT
                    System.out.print("\nSelecteaza optiunea: \n\t\"0\" - pentru iesire\n\t\"1\" - pentru a genera o propozitie\n\t\"2\" - pentru afisarea elementelor clasei\n\t\"3\" - pentru verificarea conditiilor gramaticii\n");
                    System.out.print("\nInput: ");
                    option = readOption();
                    break;
                case 1:
                    //CHECK
                    System.out.print("\nIncepem generarea: \n");
                    G.generare();
                    option = -1;
                    break;
                case 2:
                    //PRINT
                    G.afisare();
                    option = -1;
                    break;
                case 3:
                    //CHECK
                    G.verificare();
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
