package com.mehalter.life;

import com.mehalter.life.model.GameState;
import com.mehalter.life.ui.GameOfLifeUi;
import com.mehalter.life.ui.GridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameOfLife implements Runnable {

    private final GameState gameState;
    private final GameOfLifeUi gameOfLifeUi;

    // initialize state variables
    public GameOfLife() {
        gameState = new GameState(new Timer(100, (e) -> nextStep()), new GridPanel(20, 20, Color.GREEN.darker(), Color.BLUE, new int[20][20]));
        gameOfLifeUi = new GameOfLifeUi(this, gameState);
    }

    // calculates the next step of the grid based on the current grid layout
    public void nextStep() {
        // creates new temporary grid for new layout
        int[][] newGrid = new int[gameState.getGridPanel().getxSize()][gameState.getGridPanel().getySize()];
        for (int x = 0; x < gameState.getGridPanel().getxSize(); x++) {
            for (int y = 0; y < gameState.getGridPanel().getySize(); y++) {
                // calculates number of neighbors and how many are of each
                // user
                ArrayList<Integer> neighbors = gatherNeighbors(x, y);
                int oneCount = Collections.frequency(neighbors, 1);
                int twoCount = Collections.frequency(neighbors, 2);

                // applies the three rules of life Conway developed
                // checks if a dead cell has exactly 3 neighbors, and sets the
                // color to whichever user accounts for more of those three
                // cells
                if (gameState.getGridPanel().getGrid()[x][y] == 0 && neighbors.size() == 3) {
                    newGrid[x][y] = oneCount > twoCount ? 1 : 2;
                    // checks if a lives cell has too few or too many neighbors and
                    // sets it dead or alive accordingly
                } else if (gameState.getGridPanel().getGrid()[x][y] == 1 || gameState.getGridPanel().getGrid()[x][y] == 2) {
                    newGrid[x][y] = (neighbors.size() < 2 || neighbors.size() > 3) ? 0
                            : gameState.getGridPanel().getGrid()[x][y];
                    // if none of previous rules, set cell to dead
                } else {
                    newGrid[x][y] = 0;
                }
            }
        }
        // moves the temporary grid to the used grid and repaints
        gameState.getGridPanel().setGrid(newGrid);
        gameState.getGridPanel().repaint();
    }

    // method to return array of a cell's neighbors
    private ArrayList<Integer> gatherNeighbors(int x, int y) {
        // initializes an array list of integers to house the neighbors
        ArrayList<Integer> count = new ArrayList<>();
        int right, left, up, down, rightup, rightdown, leftup, leftdown;

        // if contiguous option is true
        // defines the different cell positions around the current cell
        // the ternary operators are to make sure that the grid wraps around and
        // doesn't hit an edge
        boolean xBig = gameState.isContiguous() ? (x == (gameState.getGridPanel().getxSize() - 1)) : (x < (gameState.getGridPanel().getxSize() - 1));
        boolean xSmall = gameState.isContiguous() ? x == 0 : x > 0;
        boolean yBig = gameState.isContiguous() ? y == gameState.getGridPanel().getxSize() - 1 : y < gameState.getGridPanel().getxSize() - 1;
        boolean ySmall = gameState.isContiguous() ? y == 0 : y > 0;

        if (gameState.isContiguous()) {
            right = gameState.getGridPanel().getGrid()[xBig ? 0 : x + 1][y];
            left = gameState.getGridPanel().getGrid()[xSmall ? gameState.getGridPanel().getxSize() - 1 : x - 1][y];
            up = gameState.getGridPanel().getGrid()[x][yBig ? 0 : y + 1];
            down = gameState.getGridPanel().getGrid()[x][ySmall ? gameState.getGridPanel().getxSize() - 1 : y - 1];

            rightup = gameState.getGridPanel().getGrid()[xBig ? 0 : x + 1][yBig ? 0
                    : y + 1];
            rightdown = gameState.getGridPanel().getGrid()[xBig ? 0 : x + 1][ySmall ? gameState.getGridPanel().getxSize() - 1
                    : y - 1];
            leftup = gameState.getGridPanel().getGrid()[xSmall ? gameState.getGridPanel().getxSize() - 1 : x - 1][yBig ? 0
                    : y + 1];
            leftdown = gameState.getGridPanel().getGrid()[xSmall ? gameState.getGridPanel().getxSize() - 1 : x - 1][ySmall ? gameState.getGridPanel().getxSize() - 1
                    : y - 1];

        } else {
            // if contiguous option is false
            // defines the different cell positions around the current cell
            right = xBig ? gameState.getGridPanel().getGrid()[x + 1][y] : 0;
            left = xSmall ? gameState.getGridPanel().getGrid()[x - 1][y] : 0;
            up = yBig ? gameState.getGridPanel().getGrid()[x][y + 1] : 0;
            down = ySmall ? gameState.getGridPanel().getGrid()[x][y - 1] : 0;

            rightup = xBig && yBig ? gameState.getGridPanel().getGrid()[x + 1][y + 1] : 0;
            rightdown = xBig && ySmall ? gameState.getGridPanel().getGrid()[x + 1][y - 1] : 0;
            leftup = xSmall && yBig ? gameState.getGridPanel().getGrid()[x - 1][y + 1] : 0;
            leftdown = xSmall && ySmall ? gameState.getGridPanel().getGrid()[x - 1][y - 1] : 0;
        }

        // adds the neighbor value if it is alive
        count = countAdd(right, count);
        count = countAdd(left, count);
        count = countAdd(up, count);
        count = countAdd(down, count);

        count = countAdd(rightup, count);
        count = countAdd(rightdown, count);
        count = countAdd(leftup, count);
        count = countAdd(leftdown, count);

        return count;
    }

    private ArrayList<Integer> countAdd(int _int, ArrayList<Integer> count) {
        if (_int != 0)
            count.add(_int);
        return count;
    }

    // run method to start the game
    public void run() {
        // set the initial size of the window
        gameOfLifeUi.setSize(700, 726);
        // build the top menu bar
        gameOfLifeUi.makeMenus();
        gameOfLifeUi.setTitle("Multiplayer Game of Life");
        // initialize the right-click shape pop-up menu

        // add grid panel to the window for viewing
        gameOfLifeUi.getContentPane().add(gameState.getGridPanel());
        // add mouse listener to listen for clicks on the grid
        gameState.getGridPanel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // gets current location on the panel and divides it by the size
                // of the panel and size of the grid to get the box number
                // uses a double in the denominator to force an integer divide
                // to force the final value to round down
                gameState.setCurrentY((int) (e.getX() / ((double) gameState.getGridPanel().getWidth() / gameState.getGridPanel().getxSize())));
                gameState.setCurrentX((int) (e.getY() / ((double) gameState.getGridPanel().getHeight() / gameState.getGridPanel().getxSize())));
                // if the click is a right click, don't toggle cell state, but
                // open shape menu
                if (SwingUtilities.isRightMouseButton(e))
                    gameOfLifeUi.getShapeMenu().show(e.getComponent(), e.getX(), e.getY());
                    // if it isn't a right click, and the grid is editable, toggle
                    // the cell state and repaint
                else if (! gameState.isRunning()) {
                    gameState.getGridPanel().getGrid()[gameState.getCurrentY()][gameState.getCurrentX()] = (gameState.getGridPanel().getGrid()[gameState.getCurrentY()][gameState.getCurrentX()] == gameState.getCurrentUser() ? 0
                            : gameState.getCurrentUser());
                    gameState.getGridPanel().repaint();
                }
            }

            // blank unused mouse event methods that java forces me to put here
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        gameState.getGridPanel().addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                // Allow user to click and drag mouse to toggle cell states
                gameState.setCurrentY((int) (e.getX() / ((double) gameState.getGridPanel().getWidth() / gameState.getGridPanel().getxSize())));
                gameState.setCurrentX((int) (e.getY() / ((double) gameState.getGridPanel().getHeight() / gameState.getGridPanel().getxSize())));

                // checks if the board is editable, and if the mouse is on the
                // screen
                if (! gameState.isRunning() && (gameState.getCurrentY() < gameState.getGridPanel().getxSize() && gameState.getCurrentY() >= 0)
                        && (gameState.getCurrentX() < gameState.getGridPanel().getxSize() && gameState.getCurrentX() >= 0)) {
                    // if the button used is the left click, then it turns cells
                    // on
                    // Checks to make sure that the cell it is changing is 0 so
                    // it
                    // doesn't overwrite the other user's information
                    // accidentally
                    gameState.getGridPanel().getGrid()[gameState.getCurrentY()][gameState.getCurrentX()] = SwingUtilities
                            .isLeftMouseButton(e) ? gameState.getGridPanel().getGrid()[gameState.getCurrentY()][gameState.getCurrentX()] == 0 ? gameState.getCurrentUser()
                            : gameState.getGridPanel().getGrid()[gameState.getCurrentY()][gameState.getCurrentX()]
                            // if the button isn't the left click, then it
                            // becomes an eraser
                            // and deletes everything
                            : 0;
                    gameState.getGridPanel().repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
        // set the default close operation to fully close the program when the
        // user presses the 'x' in the corner
        gameOfLifeUi.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // sets the window contents to visible
        gameOfLifeUi.setVisible(true);
    }

}