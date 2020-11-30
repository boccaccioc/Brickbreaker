package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SpeedUpBrick extends Brick{

  private static final Paint SPEEDUP_BRICK_COLOR = Color.DARKBLUE;


  /**
   * Creates a SpeedUpBrick at the coordinates given by the parameters
   *
   * @param xLocation x-location that brick will be spawned at
   * @param yLocation y-location that brick will be spawned at
   */
  public SpeedUpBrick(int xLocation, int yLocation) {
    super(xLocation, yLocation, "speedUp");
    this.getThisBrick().setFill(Color.CYAN);

  }

  /**
   * Called when the brick has been broken and speeds up the ball.
   *
   * @param brick brick that you are updating
   * @param myRoot root that this brick is in
   * @param myGame game that is being used
   */
  @Override
  public void updateStatus(Rectangle brick, Group myRoot, Game myGame) {
    brickRemove(this, 0, myRoot);
    myGame.getBall().setBallYSpeed(myGame.getBall().getBallYSpeed()*2);
    myGame.getBall().setBallXSpeed(myGame.getBall().getBallXSpeed()*2);
    myGame.getBall().getThisBall().setFill(SPEEDUP_BRICK_COLOR);
  }
}
