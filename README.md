## TO RUN:

Add the following through maven:

Junit: org.junit.jupiter:junit-jupiter-api:5.4.2

TestFX: org.testfx:testfx-junit5:4.0.16-alpha

game
====

This project implements the game of Breakout.

Name: Colin Boccaccio

### Timeline

Start Date: 9/14

Finish Date: 9/28

Hours Spent: >25

### Resources Used

DukeApplicationTest class to assist in testing javaFX code.

### Running the Program

Main class: Game

Data files needed: Level files with names "level_"+LevelNumber

Key/Mouse inputs: 

LEFT ARROW: Changes paddle direction to left

RIGHT ARROW: Changes paddle direction to right

SPACE: Pauses game, or resumes it if it was already paused

R: Restarts game form the beggining


Cheat keys:

L: Adds 1 to life count

B: Spawns in an additional ball

Known Bugs: 

Additional Balls that are spawned in sometimes do not collide with bricks correctly.

Holding left or right arrow while the paddle is at the edge of the screen will drive it out of view.

Restart button increases the Y speed of the ball in the new game.

The ball colliding with the side of the paddle causes it to get extremely fast x-velocity

Extra credit:


### Notes/Assumptions

Level files must have format:  "level_"+levelNumber+.txt 

To add to total number of levels just create a new level file following the number of the last existing level and add to the max level constant in game.

This project is normally completed as a team project, but my partner dropped the class just a couple of days and really only contributed to the PLAN.md  document.

### Impressions

This project was not easy to complete on my own, but considering the circumstances I am very happy with this final product.
"# Brickbreaker" 
