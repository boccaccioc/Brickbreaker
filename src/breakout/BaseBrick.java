package breakout;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BaseBrick extends Brick{

  /**
   * Creates a BaseBrick at the given coordinates and with a given strength
   *
   * @param xLocation x-location brick will be created
   * @param yLocation y-location brick will be created
   * @param blockStrength how many hits the brick will take to break
   */
  public BaseBrick(int xLocation, int yLocation, String blockStrength){
    super(xLocation, yLocation, blockStrength);
    setBlockColor(this.getThisBrick(), Integer.valueOf(blockStrength));
  }

  private static final List<Paint> BLOCK_COLOR_LIST = List
      .of(Color.TRANSPARENT, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
          Color.INDIGO, Color.VIOLET, Color.WHITE);

  private static void setBlockColor(Rectangle block, int colorNumber) {
    block.setFill(BLOCK_COLOR_LIST.get(colorNumber));
  }

  private static int getBlockColorValue(Paint blockColor) {
    return BLOCK_COLOR_LIST.indexOf(blockColor);
  }

  /**
   * Determines if the brick should be removed, and if not, it changes its color.
   *
   * @param brick  The brick that will be modified
   * @param myRoot The root that the brick belongs to
   */
  public void updateStatus(Rectangle brick, Group myRoot, Game myGame) {
    int blockNum = getBlockColorValue((brick).getFill()) - 1;
    if (blockNum == 0) {
      brickRemove(this, 0, myRoot);
    }
    setBlockColor(brick, blockNum);
  }
}
