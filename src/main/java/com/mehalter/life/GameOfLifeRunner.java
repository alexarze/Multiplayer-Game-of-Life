package com.mehalter.life;

//Runner to allow the user to create a new game of life
public class GameOfLifeRunner {
    // initialize the program entirely and run the runnable method
    // to start the game
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new GameOfLife());
    }
}