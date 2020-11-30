package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ExplodingBrick extends Brick{


  /**
   * Creates a ExplodingBrick at the coordinates given by the parameters
   *
   * @param xLocation x-location that brick will be spawned at
   * @param yLocation y-location that brick will be spawned at
   */
  public ExplodingBrick(int xLocation, int yLocation) {
    super(xLocation, yLocation, "explode");
    this.getThisBrick().setFill(Color.DARKGREEN);
  }

  /**
   * Called when the brick has been broken and explodes surrounding blocks.
   *
   * @param brick brick that you are updating
   * @param myRoot root that this brick is in
   * @param myGame game that is being used
   */
  @Override
  public void updateStatus(Rectangle brick, Group myRoot, Game myGame) {
    brickRemove(this, 1, myRoot);
  }
}
