package breakout;

import static breakout.Level.createLevel;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

  public static final String TITLE = "Breakout!";
  public static final int PHYSICS_X_BOUNCE_MODIFIER = (int) (2 - Math
      .random()); //Higher values are less physics
  public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  public static final int SCREEN_HEIGHT = screenSize.height - 100;
  public static final int SCREEN_WIDTH = screenSize.width / 2;
  public static final Paint BACKGROUND = Color.LIGHTBLUE;

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private int numLives = 3;
  public Text lifeStatus;
  public int score = 0;
  public Text scoreStatus;
  private int currentLevel = -1; //Set to the level you want to start at minus one
  private final int FINAL_LEVEL = 3; //Change this as levels are added
  private Scene myScene;
  private Stage myStage;
  private Timeline myTimeLine;
  private Group myRoot;
  private Paddle myPaddle;
  private Ball myBall;

  public Game myGame;


  /**
   * Starts the game
   *
   * @param stage that the instance variable myStage is set to
   */
  public void start(Stage stage) {
    myGame = new Game();
    myStage = stage;
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step());
    Timeline animation = new Timeline();
    myTimeLine = animation;
    myScene = setupScene(BACKGROUND);
    stage.setScene(myScene);
    stage.setTitle(TITLE);
    stage.show();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }


  /**
   * Creates a group and adds nodes into that group which is used to create the scene.
   *
   * @param background color that background will take
   * @return scene with root containing a paddle, ball, and statuses
   */
  public Scene setupScene(Paint background) {
    myRoot = new Group();
    myPaddle = new Paddle();
    myBall = new Ball();
    lifeStatus = new Text(SCREEN_WIDTH - 180, 20, "Lives Remaining: " + numLives);
    lifeStatus.setId("Life Status");
    lifeStatus.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

    scoreStatus = new Text(SCREEN_WIDTH - 100, 40, "Score: " + score);
    scoreStatus.setId("Score Status");
    scoreStatus.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

    myRoot.getChildren().add(myPaddle.getThisPaddle());
    myRoot.getChildren().add(myBall.getThisBall());
    myRoot.getChildren().add(lifeStatus);
    myRoot.getChildren().add(scoreStatus);

    Scene scene = new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT, background);
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  /**
   * Checks the win condition, then resets ball position and sets up the bricks for the next level.
   */
  public void startNextLevel() {
    currentLevel++;
    if (currentLevel > FINAL_LEVEL) {
      System.out.println("YOU WIN!");
      System.exit(0);
    }
    myBall.moveToStartPosition();
    createLevel(currentLevel, myRoot);
    lifeStatus.toFront();
    scoreStatus.toFront();
    numLives += currentLevel;
  }


  /**
   * Checks for loss condition and updates the scene.
   */
  public void step() {
    if (numLives <= 0) {
      System.out.println("You Lose!");
      System.exit(0);
    }
    updateShapes();
  }

  private void updateShapes() {
    lifeStatus.setText("Lives Remaining: " + numLives);
    scoreStatus.setText("Score: " + score);
    Ball.updateBalls(myRoot, myPaddle, this);
    //myBall.updateLocation();
    //myBall.checkCollisions(myRoot, myPaddle, this);
    myPaddle.updateLocation();
  }

  private void handleKeyInput(KeyCode code) {
    switch (code) {
      case LEFT -> myPaddle.setPaddleSpeed(-1 * myPaddle.getPaddleBaseSpeed());
      case RIGHT -> myPaddle.setPaddleSpeed(myPaddle.getPaddleBaseSpeed());
      case SPACE -> {
        if (Animation.Status.PAUSED == myTimeLine.getStatus()) {
          myTimeLine.play();
        } else {
          myTimeLine.pause();
        }
      }
      case R -> {
        start(new Stage());
        currentLevel--;
      }
      case L -> numLives++;
      case B -> {Ball newBall = new TempBall(); newBall.getThisBall().setId(""); myRoot.getChildren().add(newBall.getThisBall());}
    }
  }
  /**
   * Getter method for myBall
   *
   * @return myBall
   */
  public Ball getBall(){
    return myBall;
  }
  /**
   * Getter method for myPaddle
   *
   * @return myPaddle
   */
  public Paddle getPaddle(){
    return myPaddle;
  }
  /**
   * Getter method for lives
   *
   * @return numLives
   */
  public int getLives(){
    return numLives;
  }
  /**
   * Setter method for lives
   *
   * @param newLives new value for lives
   */
  public void setLives(int newLives){
    numLives = newLives;
  }

  /**
   * Getter method for score
   *
   * @return current score
   */
  public int getScore(){
    return score;
  }

  /**
   * Setter method for score
   *
   * @param newScore score to be set to
   */
  public void setScore(int newScore){
    score = newScore;
  }

  /**
   * Begins the game.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
