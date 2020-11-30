package breakout;

import static breakout.Brick.checkBallBrickCollision;
import static breakout.Game.PHYSICS_X_BOUNCE_MODIFIER;
import static breakout.Game.SCREEN_HEIGHT;
import static breakout.Game.SCREEN_WIDTH;


import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Ball extends Node {

  private static final int BALL_SIZE = 12;
  private static final Paint BALL_COLOR = Color.BLACK;
  private static final int BALL_RESPAWN_X = SCREEN_WIDTH / 2;
  private static final int BALL_RESPAWN_HEIGHT = 200; //This just happens to be a good height
  private static List<Ball> ballList = new ArrayList<>();


  private double ballYSpeed = 8;
  private double ballXSpeed = 0;

  private Circle thisBall;

  /**
   * Constructs a Ball object by creating a circle and giving it attributes
   */
  public Ball() {
    thisBall = new Circle(BALL_RESPAWN_X, BALL_RESPAWN_HEIGHT, BALL_SIZE);
    thisBall.setFill(BALL_COLOR);
    thisBall.setId("ball");
    ballList.add(this);
  }

  public Ball(Circle newBall) {
    thisBall = newBall;
    thisBall.setFill(BALL_COLOR);
    thisBall.setId("ball");
  }

  /**
   * @return returns the circle object for the ball
   */
  public Circle getThisBall() {
    return thisBall;
  }

  /**
   * Getter method for x-speed
   *
   * @return x-speed
   */
  public double getBallXSpeed() {
    return ballXSpeed;
  }

  /**
   * Getter method for y-speed
   *
   * @return y-speed
   */
  public double getBallYSpeed() {
    return ballYSpeed;
  }

  /**
   * Setter method for x-speed
   *
   * @param newSpeed set the x-speed to newSpeed
   */
  public void setBallXSpeed(double newSpeed) {
    ballXSpeed = newSpeed;
  }

  /**
   * Setter method for y-speed
   *
   * @param newSpeed set the y-speed to newSpeed
   */
  public void setBallYSpeed(double newSpeed) {
    ballYSpeed = newSpeed;
  }

  /**
   * Getter method for ball respawn height
   *
   * @return ball respawn height
   */
  public double getBallRespawnHeight() {
    return BALL_RESPAWN_HEIGHT;
  }

  /**
   * Updates all ball objects collision and movements.
   *
   * @param root root of the game
   * @param thisPaddle the paddle for this game
   * @param game game that you are checking collision in
   */
  public static void updateBalls(Group root, Paddle thisPaddle, Game game) {
    for (int i = 0; i < ballList.size(); i++) {
      Ball currentBall = ballList.get(i);
      currentBall.updateLocation();
      currentBall.checkCollisions(root, thisPaddle, game);
    }
  }

  /**
   * Updates the location of the ball for each step based on its current velocity.
   */
  public void updateLocation() {
    thisBall.setCenterY(thisBall.getCenterY() + ballYSpeed);
    thisBall.setCenterX(thisBall.getCenterX() + ballXSpeed);
  }

  /**
   * Calls various methods to check for collision with the ball and other entities.
   *
   * @param root       The root for the scene
   * @param thisPaddle The paddle that will be used for collision checks
   * @param game
   */
  public void checkCollisions(Group root, Paddle thisPaddle, Game game) {
    checkBallScreenBottomCollision(game, root);
    checkBallWallCollision();
    checkBallPaddleCollision(thisPaddle);
    checkBallBrickCollision(root, game);
  }

  /**
   * @param game game object for game that you are checking collision in
   * @param root root for this game
   */
  public void checkBallScreenBottomCollision(Game game, Group root) {
    if (thisBall.getCenterY() + thisBall.getRadius() > SCREEN_HEIGHT) {
      moveToStartPosition();
      game.setLives(game.getLives() - 1);
    }
  }

  private void checkBallWallCollision() {
    if ((thisBall.getCenterY() + thisBall.getRadius() > SCREEN_HEIGHT
        || thisBall.getCenterY() - thisBall.getRadius() <= 0) && (
        thisBall.getCenterX() + thisBall.getRadius() > SCREEN_WIDTH
            || thisBall.getCenterX() - thisBall.getRadius() <= 0)) {
      ballXSpeed *= -1;
      ballYSpeed *= -1;
      return;
    }
    if (thisBall.getCenterX() + thisBall.getRadius() > SCREEN_WIDTH ||
        thisBall.getCenterX() - thisBall.getRadius() <= 0) {
      ballXSpeed *= -1;
      return;
    }
    if (thisBall.getCenterY() - thisBall.getRadius() <= 0) {
      ballYSpeed *= -1;
      return;
    }
  }

  private void checkBallPaddleCollision(Paddle thisPaddle) {
    /*if (myBall.getCenterY() + BALL_SIZE/2 >= myPaddle.getPaddleHeight() &&
        myBall.getCenterY() + BALL_SIZE/2 < myPaddle.getPaddleHeight() + 20 &&
        myBall.getCenterX() - BALL_SIZE/2 > myPaddle.getThisPaddle().getX() &&
        myBall.getCenterX() + BALL_SIZE/2 < myPaddle.getThisPaddle().getX() + myPaddle.getPaddleSize())*/
    Shape intersection = Shape.intersect(thisBall, thisPaddle.getThisPaddle());
    if (intersection.getBoundsInLocal().getWidth() != -1) {
      ballYSpeed *= -1;
      ballXSpeed += -1 * (thisPaddle.getPaddleSpeed() / PHYSICS_X_BOUNCE_MODIFIER);
    }
  }

  /**
   * Moves the ball to its starting position and sets the x-velocity to zero.
   */
  public void moveToStartPosition() {
    thisBall.setCenterX(BALL_RESPAWN_X);
    thisBall.setCenterY(BALL_RESPAWN_HEIGHT);
    ballXSpeed = 0;
    ballYSpeed = 8;
    thisBall.setFill(BALL_COLOR);
  }


  /**
   * @param ballToDelete The ball that you want to remove from the static collection of balls and the root
   * @param myRoot root for the game to remove the circle node of the ball object
   */
  public static void ballRemove(Ball ballToDelete, Group myRoot) {
    myRoot.getChildren().remove(ballToDelete.getThisBall());
    ballList.remove(ballToDelete);
  }

  /**
   * @return ballList that contains all ball objects that are currently being used.
   */
  public static List<Ball> getBallList() {
    return ballList;
  }
}
