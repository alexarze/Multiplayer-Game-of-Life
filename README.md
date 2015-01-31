------
README
------

== Multiplayer Conway's Game of Life ==

Creator : Micah Halter

Version 1.0  31 January 2015

Requires: Java8

![picture alt](https://lh3.googleusercontent.com/-2Yh8ItEcTQQ/VMxs3m3Na5I/AAAAAAAAeWk/R-cyDb0sitE/w720-h746-no/game%2Bof%2Blife.png "Image of the game running.")

======================================================================
I. Description
--------------

Usage :

1. **~$ javac /path/to/gameoflife.java**
2. **~$ java gameoflife**



The Game of Life, also known simply as Life, is a two-dimensional
cellular automaton devised by the British mathematician John Horton
Conway in 1970. The idea is that every cell in a square grid has one
of two states, dead or alive. A cell can become alive if it has three neighbors, but dies if it has more than three or less than two. The
result is a pattern of cells which mimicks how simple life forms react
to isolation and overpopulation.

In this particular version, I have worked on creating a new rule and
twist to the game. I have created a "multiplayer" version where the
user can define a second "species" on the board along with the first.
This allows the user to experiment with competition in the game. You
can create systems and see what species wins the most territory, takes
over another, or you could see if you can create a mutualistic
relationship where the two species help each other survive.

This program is also an implementation of Coway's game that allows you
to draw upon years of research by downloading interesting patterns
from [bitstorm](http://www.bitstorm.org/gameoflife/lexicon/) and using
these patterns in your game. Also, you can save any grid you like and
use it later on in the same manner.

The world of Life has boundless shapes and patterns, each of which
has its own unique evolution, behavior, and interaction with the shapes
around it. Go ahead and start up the program and give it a try.

Life awaits.

II. Included
------------

- README.md

- LICENSE

- gameoflife.java

III. Instructions
-----------------

- You can toggle the state of a cell by clicking on a cell
  (oscillates between dead and alive).

- You can change the current species you are editing by clicking the
  user button in the menu bar.

- As you are editing a species, you can press the Color button on the
  menu bar to change the current species' color.

- The size of the grid can be controlled throught the size menu.

- By selecting the start menu item, you can toggle the game on and off
  and watch the population that you have defined evolve.

- If you want to see each individual step, you can prest the step item
  and the board will move forward a single generation.

- The speed menu controls the speed at which the animation plays.

- In the file menu, there are several options:

    - The open item allows you to open an existing .cells file that
      you have either created or downloaded from [bitstorm](http://www.bitstorm.org/gameoflife/lexicon/).

    - Once a file has been opened, you can right click on the board
      to open up a menu to select any of the shapes you have opened.

    - The save item allows you to save the current board as a cropped
      down shape for later use, or for sharing.

    - The quit item exits out of the program.


======================================================================
Contact
-------


Questions, Comments, and Bugs at :

-   micah@mehalter.com
