package org.ankurGangwar.systemDesign;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to tic tac toe ....");
        while(true){
            Game game = new Game();
            System.out.print("Which board size do you want: ");
            Scanner s = new Scanner(System.in);
            int size = s.nextInt();
            if(size > 10 || size < 3){
                System.out.println("Please choose a size between 3 and 10 !!");
                continue;
            }
            System.out.println("Initializing Board ....");
            game.initializeGame(size);
            System.out.println(game.startGame());
        }
    }
}