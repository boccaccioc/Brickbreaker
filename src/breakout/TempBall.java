package breakout;

import static breakout.Game.SCREEN_HEIGHT;
import static breakout.Game.SCREEN_WIDTH;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class TempBall extends Ball{

  /**
   * Creates a TempBall object
   */
  public TempBall() {
    super();
    getThisBall().setFill(Color.GRAY);
  }

  /**
   * Removes the ball from the root if it goes past the bottom of the screen.
   *
   * @param game game object for game that you are checking collision in
   * @param root root for this game
   */
  @Override
  public void checkBallScreenBottomCollision(Game game, Group root) {
    if (getThisBall().getCenterY() + getThisBall().getRadius() > SCREEN_HEIGHT) {
      ballRemove(this, root);
      return;
    }
  }
}
