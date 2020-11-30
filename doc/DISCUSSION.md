## Lab Discussion
### Team 29
### Colin Boccaccio


### Issues in Current Code

#### Brick
 * Design issues

Need to create a class heirarchy for different types of blocks, so that blocks can have shared  attributes but different interations when they have a collision.


 * Design issue

Does not throw exceptions or deal adequately with reading in a file that does not have brick types that are not numbers 0-8

#### Game
 * Design issues

Need to fix all static variables and make them not static to follow good code convention.

 * Design issue

There are currently many "magic numbers" that need to be adressed.

### Refactoring Plan

 * What are the code's biggest issues?

Currently a lot of variables are public that should not be and static that should not be. Additionally I need to add in more tests.

 * Which issues are easy to fix and which are hard?

It depends on how much I am using each variable, but in general fixing public variables should simply be just making those variables parameters that are passed to methods and making the variable private. Some of the static variables that I have may require some more changes, because there may also be some methods that are static that maybe shouldn't be that need to be fixed as well.


 * What are good ways to implement the changes "in place"?

I will look through all the uses of each of the public variables to determine which methods they need to be passed to so that they can still be used by other classes in specific methods but be private.

### Refactoring Work

 * Issue chosen: Fix and Alternatives

Issue: Public variables

Fix: Passing those variables to the methods that really need them, and possible creating a setter method for those variables it seems nessecary.

 * Issue chosen: Fix and Alternatives

 Issue: Magic numbers
 
 Fix: Going through each magic number and making constants with appropriate names to represent these numbers to make my program more dynamic.