# Game Plan
## NAMEs


### Breakout Variation Ideas


### Interesting Existing Game Variations

 * Circular Brickbreaker (Vortex)

 * Collective Brickbreak (invest balls) 
 

#### Block Ideas

 * TNT Block: Explodes neighbor blocks when hit from top.
 Rejuvinates neighbor blocks when hit from the top.  
 
 * Missile Blocks: When broken, block shoots missle downward. If missle hits paddle, player loses a life. 

 * Falling Block: Falls straight through and breaks every block on the way. It is randomly chosen and player does not know
 which block is falling block until it is hit. Once block is hit, player has limited time to hit it again. If it is hit within
 this time frame, the block falls. If not, the player misses their chance and another block becomes the falling block. 

 * Buckshot Blocks: Shoots out balls when broken.


#### Power Up Ideas

There will be temporary and permanent power-ups. The temporary power ups
will spawn randomly and fall from the top toward bottom. Player must catch power ups with paddle at the bottom. 
Permanent power-ups are given after completion of each level and stay with the player for the rest of the game (spans across levels).

If player has stackable power-ups, stacks disappear when player dies.  

 Temp Power Up:
 
 * Exponential Split: the power up splits the ball into two
 and those two balls get split themselves. How many splits happen depend on the strength
 of the power up i.e. a level two exponential split has the original ball split in two
 and the children themselves split in two and that's it. 

 * Star Ball: Breaks everything in path. Power up ends once paddle catches ball again. 

 * Sticky Paddle: Keeps balls from bouncing off paddle. Sticks balls to paddle until player hits release button. 
 
 Permanent Power Up
 
 * Wide Paddle: 
 * Extra Life


#### Cheat Key Ideas

 * Reset Lives 

 * Fill Power Ups

 * R - reset level

 * Space - pause


#### Level Descriptions

  Block will be different colors. Colors correspond to number of times block needs to be hit to be destroyed.
  
  Red = 1 time
  Orange = 2 times 
  Yellow = 3 times
  Green = 4
  Blue = 5
    ...
    
    

 * Level 1
   * Just three row of blocks. Only red, orange, and yellow blocks.

   * Once half the blocks are gone, power ups begin to drop. 

 * Level 2
   * Now Green and Blue blocks. TNT blocks introduced. 

   * Before level begins, player chooses permanent power up. 
   
   * New Background
   
   * More intense music

 * Level 3
   * All blocks of strength yellow and above. Blocks will be grouped in the middle of screen. 

   * All blocks introduced. 
   
   * New Background
      
   * More intense music
   
   


### Possible Classes

 * Class 1
   * Paddle

   * Method

 * Class 2
   * Block

   * Method

 * Class 3
   * Ball

   * Method

 * Class 4
   * Background 

   * Method

 * Class 5
   * PowerUp

   * Method
   
* Class 6
  * Sound

  * Method
