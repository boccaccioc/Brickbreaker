package breakout;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.scene.Group;

public class Level {

  private static final List<String> levelLocations = List
      .of("level_0.txt", "level_1.txt", "level_2.txt", "level_3.txt");


  /**
   * Reads the levelNumbers respective file and creates the level based on that information
   *
   * @param levelNumber level of the level that will be created
   * @param myRoot      root that bricks will be added to
   */
  public static void createLevel(int levelNumber, Group myRoot) {
    String levelFile = levelLocations.get(levelNumber);
    try {
      Path path = Paths.get(Level.class.getClassLoader().getResource(levelFile).toURI());
      int row = 0;
      for (String line : Files.readAllLines(path)) {
        for (int i = 0; i < line.length(); i++) {
          if(line.substring(i, i + 1).equals("E")){
            myRoot.getChildren().add(new ExplodingBrick(i, row).getThisBrick());
            continue;
          }
          if(line.substring(i, i + 1).equals("S")){
            myRoot.getChildren().add(new SpeedUpBrick(i, row).getThisBrick());
            continue;
          }
          if(line.substring(i, i + 1).equals("B")){
            myRoot.getChildren().add(new BallSpawnBrick(i, row).getThisBrick());
            continue;
          }
          int brickHardness = Integer.valueOf(line.substring(i, i + 1));
          if (brickHardness == 0) {
            continue;
          }
          myRoot.getChildren().add(new BaseBrick(i, row, Integer.toString(brickHardness)).getThisBrick());
        }
        row++;
      }
    } catch (Exception e) {
      System.out.println("Please fix file locations for levels.");
      System.exit(0);
    }
  }
}
