package breakout;

import static breakout.Game.SCREEN_HEIGHT;
import static breakout.Game.SCREEN_WIDTH;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Node {

  private static final int PADDLE_SIZE = 160;
  private static final double PADDLE_BASE_SPEED = 10;
  private static final Paint PADDLE_COLOR = Color.BLACK;
  private final double PADDLE_HEIGHT = SCREEN_HEIGHT - 100;

  private double currentSpeed = 0;

  private Rectangle thisPaddle;

  /**
   * Creates a paddle by giving a rectangle attributes.
   *
   * @param newPaddle rectangle that the paddle will be based off of
   */
  public Paddle(Rectangle newPaddle) {
    thisPaddle = newPaddle;
    thisPaddle.setFill(PADDLE_COLOR);
    thisPaddle.setId("paddle");
  }

  /**
   * Creates a paddle with preset values.
   */
  public Paddle() {
    thisPaddle = new Rectangle(SCREEN_WIDTH / 2 - PADDLE_SIZE / 2, PADDLE_HEIGHT, PADDLE_SIZE,
        PADDLE_SIZE / 10);
    thisPaddle.setFill(PADDLE_COLOR);
    thisPaddle.setId("paddle");
  }

  /**
   * @return the rectangle object for this paddle
   */
  public Rectangle getThisPaddle() {
    return thisPaddle;
  }

  /**
   * @return the paddle size
   */
  public int getPaddleSize() {
    return PADDLE_SIZE;
  }

  /**
   * @return the paddle speed
   */
  public double getPaddleSpeed() {
    return currentSpeed;
  }

  /**
   * @return the paddle base speed
   */
  public double getPaddleBaseSpeed() {
    return PADDLE_BASE_SPEED;
  }

  /**
   * Sets the paddle speed to a new speed.
   *
   * @param newSpeed speed that the paddle speed will be set to
   */
  public void setPaddleSpeed(double newSpeed) {
    currentSpeed = newSpeed;
  }

  /**
   * Updates the paddles location based off of the paddles current velocity.
   */
  public void updateLocation() {
    thisPaddle.setX(thisPaddle.getX() + currentSpeed);
    checkPaddleWallCollision();
  }


  private void checkPaddleWallCollision() {
    if (thisPaddle.getX() + PADDLE_SIZE > SCREEN_WIDTH || thisPaddle.getX() < 0) {
      currentSpeed *= -1;
    }
  }


}
