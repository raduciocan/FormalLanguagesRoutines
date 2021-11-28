package com.hammersoft.formal_languages.view;

import java.util.Scanner;

public abstract class Meniu implements View {
    private Scanner in = new Scanner(System.in);

    public String readLine(){
        return in.nextLine();
    }

    public int readOption(){
        String line = readLine();
        if (line.matches("\\d+")) {
            int option = Integer.parseInt(line);
            if (option > -1)
                return option;
        }
        else
            System.out.print("\n ! EROARE: optiunea selectata nu este valida \t (Must be POSITIVE INTEGER) \n");
        return -2;
    }
}
