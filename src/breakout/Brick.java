package breakout;


import static breakout.Game.PHYSICS_X_BOUNCE_MODIFIER;
import static breakout.Game.SCREEN_HEIGHT;
import static breakout.Game.SCREEN_WIDTH;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public abstract class Brick {

  private static final int NUM_BLOCKS = 8;
  public static final int BLOCK_WIDTH = (int) (SCREEN_WIDTH / NUM_BLOCKS);
  public static final int BLOCK_HEIGHT = (int) (SCREEN_HEIGHT / 16);
  private static List<Brick> brickList = new ArrayList<>();

  private Rectangle thisBrick;

  /**
   * Constructs a Brick by creating a Rectangle and giving it attributes
   *
   * @param xLocation     The x location that the block should be created (has a value of 0-7)
   * @param yLocation     The y location that the block should be created
   * @param blockStrength determines the block color and hom many hits until it breaks
   */
  public Brick(int xLocation, int yLocation, String blockStrength) {
    thisBrick = new Rectangle(xLocation * BLOCK_WIDTH, yLocation * BLOCK_HEIGHT, BLOCK_WIDTH,
        BLOCK_HEIGHT);
    thisBrick.setId("block_" + blockStrength);
    brickList.add(this);
  }

  /**
   * @return The Rectangle object for thisBrick
   */
  public Rectangle getThisBrick() {
    return thisBrick;
  }
  public abstract void updateStatus(Rectangle brick, Group myRoot, Game myGame);

  /**
   * Determines if there is collision with the ball and each brick
   *
   * @param myRoot root containing the balls and bricks for the game
   * @param game game that collision is being tested in
   */
  public static void checkBallBrickCollision(Group myRoot, Game game) {
    if (brickList.size() == 0){
      game.startNextLevel();
    }
    for (Brick currentBrick : brickList) {
      for(Ball currentBall : Ball.getBallList()){
        if (currentBall.getThisBall().getBoundsInLocal().intersects(currentBrick.getThisBrick().getBoundsInLocal())) {
          currentBrick.updateStatus(currentBrick.thisBrick, myRoot, game);
          game.setScore(game.getScore()+1);
          currentBall.setBallYSpeed(currentBall.getBallYSpeed()*-1);
          currentBall.setBallXSpeed((currentBall.getBallXSpeed()/PHYSICS_X_BOUNCE_MODIFIER)*-1);
          return;
        }
      }
    }
  }

  /**
   * Removes a brick and its rectangle object, and removes surrounding bricks if removingSurrounding >=1
   *
   * @param brickToDelete brick that will be deleted
   * @param removingSurrounding value used to determine how many surrounding blocks will be destroyed
   * @param myRoot root that contains the brick
   */
  public static void brickRemove(Brick brickToDelete, int removingSurrounding, Group myRoot){
    int brickToDeleteLocation = brickList.indexOf(brickToDelete);
    myRoot.getChildren().remove(brickToDelete.getThisBrick());
    brickList.remove(brickToDelete);
    for(int i = 0; i < removingSurrounding; i++){
      if(brickList.size() > 0 && brickToDeleteLocation < brickList.size()){
       brickRemove(brickList.get(brickToDeleteLocation), removingSurrounding-1, myRoot);
        if(brickToDeleteLocation >= 1 && brickToDeleteLocation < brickList.size()){
          brickRemove(brickList.get(brickToDeleteLocation-1), removingSurrounding-1, myRoot);
        }
        if(brickList.size() >= brickToDeleteLocation + 8) {
          brickRemove(brickList.get(brickToDeleteLocation+8), removingSurrounding-1, myRoot);
        }
        if(brickToDeleteLocation - 8 >= 0){
          brickRemove(brickList.get(brickToDeleteLocation-8), removingSurrounding-1, myRoot);
        }
      }
    }
  }

}
