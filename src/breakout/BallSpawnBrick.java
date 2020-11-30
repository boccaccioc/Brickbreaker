package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BallSpawnBrick extends Brick{

  /**
   * Creates a BallSpawnBrick at the coordinates given by the parameters
   *
   * @param xLocation x-location that brick will be spawned at
   * @param yLocation y-location that brick will be spawned at
   */
  public BallSpawnBrick(int xLocation, int yLocation) {
    super(xLocation, yLocation, "ballSpawn");
    this.getThisBrick().setFill(Color.BLACK);
  }

  /**
   * Called when the brick has been broken and spawns a ball where the brick used to be.
   *
   * @param brick brick that you are updating
   * @param myRoot root that this brick is in
   * @param myGame game that is being used
   */
  @Override
  public void updateStatus(Rectangle brick, Group myRoot, Game myGame) {
    brickRemove(this, 0, myRoot);
    Ball droppedBall = new TempBall();
    droppedBall.getThisBall().setCenterX(brick.getX() + brick.getWidth()/2);
    droppedBall.getThisBall().setCenterY(brick.getY() + brick.getHeight()/2);
    myRoot.getChildren().add(droppedBall.getThisBall());
  }

}
