import breakout.Ball;
import breakout.Game;
import breakout.Paddle;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static breakout.Brick.BLOCK_HEIGHT;
import static breakout.Game.BACKGROUND;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.input.KeyCode;
import util.DukeApplicationTest;


/**
 * Tests for Game class.
 *
 * @author Robert C Duvall
 */
public class GameTest extends DukeApplicationTest {

  // create an instance of our game to be able to call in tests (like step())
  private final Game myGame = new Game();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  public final int SCREEN_HEIGHT = screenSize.height - 100;
  public final int SCREEN_WIDTH = screenSize.width / 2;
  // keep any useful elements whose values you want to test directly in multiple tests
  private Paddle myPaddle;
  private Ball myBall;
  private Group myRoot;


  /**
   * Start special test version of application that does not animate on its own before each test.
   * <p>
   * Automatically called @BeforeEach by TestFX.
   */
  @Override
  public void start(Stage stage) {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    // find individual items within game by ID (must have been set in your code using setID())
    myBall = myGame.getBall();//lookup("#ball").query();
    myPaddle = myGame.getPaddle();//lookup("#paddle").query();
    myRoot = (Group) myScene.getRoot();
  }

  @Test
  public void ballInitialPositions () {
    // GIVEN, start of the game
    // WHEN, no events have happened
    // THEN, check elements are correctly positioned
    assertEquals(SCREEN_WIDTH/2, myBall.getThisBall().getCenterX());
    assertEquals(200, myBall.getThisBall().getCenterY());
    assertEquals(12, myBall.getThisBall().getRadius());

    assertEquals(SCREEN_WIDTH/2 - myPaddle.getPaddleSize()/2, myPaddle.getThisPaddle().getX());
    assertEquals(SCREEN_HEIGHT - 100, myPaddle.getThisPaddle().getY());
    assertEquals(160, myPaddle.getThisPaddle().getWidth());
    assertEquals(16, myPaddle.getThisPaddle().getHeight());

  }
  @Test
  public void paddleMovement () {
    // GIVEN, start of the game
    // WHEN, no events have happened
    // THEN, checks paddle movement
    myPaddle.getThisPaddle().setX(100);
    myPaddle.getThisPaddle().setY(100);
    press(myScene, KeyCode.LEFT);
    javafxRun(() -> myGame.step());
    assertEquals(100 + myPaddle.getPaddleSpeed(), myPaddle.getThisPaddle().getX());
    assertEquals(100, myPaddle.getThisPaddle().getY());
    press(myScene, KeyCode.RIGHT);
    javafxRun(() -> myGame.step());
    press(myScene, KeyCode.RIGHT);
    javafxRun(() -> myGame.step());
    assertEquals(100 + myPaddle.getPaddleSpeed(), myPaddle.getThisPaddle().getX());
    assertEquals(100, myPaddle.getThisPaddle().getY());
  }
  @Test
  public void checkBallCornerBounce() {
    // GIVEN, start of the game
    // WHEN, ball in corner
    // THEN, checks how ball bounces off
    javafxRun(() -> myGame.step());
    myBall.setBallXSpeed(-10);
    myBall.setBallYSpeed(-10);
    myBall.getThisBall().setCenterX(myBall.getThisBall().getRadius());
    myBall.getThisBall().setCenterY(myBall.getThisBall().getRadius());
    //myBall.checkCollisions(myRoot, myPaddle);
    javafxRun(() -> myGame.step());
    assertEquals(10, myBall.getBallXSpeed());
    assertEquals(10, myBall.getBallYSpeed());
  }

  @Test
  public void checkBallFallThroughBottom() {
    // GIVEN, start of the game
    // WHEN, ball hits paddle
    // THEN, checks how ball bounces off
    javafxRun(() -> myGame.step());
    //myBall.getThisBall().setCenterX(myBall.getThisBall().getRadius()+50);
    myBall.getThisBall().setCenterY(SCREEN_HEIGHT-myBall.getThisBall().getRadius());
    myGame.setLives(5);
    javafxRun(() -> myGame.step());
    assertEquals(SCREEN_WIDTH/2, myBall.getThisBall().getCenterX());
    assertEquals(myBall.getBallRespawnHeight(), myBall.getThisBall().getCenterY());
    assertEquals(4, myGame.getLives());
  }


  @Test
  public void checkBallPaddleBounce() {
    // GIVEN, start of the game
    // WHEN, ball hits paddle
    // THEN, checks how ball bounces off
    javafxRun(() -> myGame.step());
    myBall.setBallXSpeed(0);
    myBall.setBallYSpeed(8);
    myBall.getThisBall().setCenterX(SCREEN_WIDTH/2);
    myBall.getThisBall().setCenterY(myPaddle.getThisPaddle().getY()-myBall.getThisBall().getRadius());
    myPaddle.getThisPaddle().setX(SCREEN_WIDTH/2);
    myPaddle.setPaddleSpeed(0);
    javafxRun(() -> myGame.step());

    assertEquals(0, myBall.getBallXSpeed());
    assertEquals(-8, myBall.getBallYSpeed());
  }

  @Test
  public void checkBallBrickBounce() {
    // GIVEN, start of the game
    // WHEN, ball hits paddle
    // THEN, checks how ball bounces off
    javafxRun(() -> myGame.step());
    myBall.setBallXSpeed(0);
    myBall.setBallYSpeed(-8);
    myBall.getThisBall().setCenterX(SCREEN_WIDTH/2);
    myBall.getThisBall().setCenterY(BLOCK_HEIGHT + myBall.getThisBall().getRadius());
    javafxRun(() -> myGame.step());

    assertEquals(8, myBall.getBallYSpeed());
  }
}