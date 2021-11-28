package com.hammersoft.formal_languages.view;
import com.hammersoft.formal_languages.language_programs.Program;
import com.hammersoft.formal_languages.language_programs.AFD;

//Singleton design pattern: Lazy initilization
public final class MeniuAFD extends Meniu{
    private static MeniuAFD instance = null;
    AFD A;

    private MeniuAFD() {}

    public static MeniuAFD getInstance() {
        if (instance == null)
            instance =  new MeniuAFD();
        return instance;
    }

    public void startSession(Program P){
        A = (AFD) P;
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
                    System.out.print("\nPrecizeaza cuvantul de testat: ");
                    switch (A.verificare(readLine())) {
                        case -1:
                            System.out.print("\n + PARCURGERE FINALIZATA:\t \033[1mBLOCAJ\033[0m intalnit !\n");
                            break;
                        case 0:
                            System.out.print("\n + PARCURGERE FINALIZATA:\tCuvant \033[1mRESPINS\033[0m !\n");
                            break;
                        case 1:
                            System.out.print("\n + PARCURGERE FINALIZATA:\tCuvant \033[1mACCEPTAT\033[0m !\n");
                            break;
                    }
                    option = -1;
                    break;
                case 2:
                    //PRINT
                    A.afisare();
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
