package amazons.board;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.Objects;

//TODO complete the code this class and its documentation

public class Position implements Serializable {
    public static final DataFormat POSITION_FORMAT = new DataFormat("amazons.position");

   int x;
   int y;

   //TODO
    public int getX() {return 0;}
    public int getY() {return 0;}


    //TODO
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows){
        return true;
    }
    // TODO
    public Position next(CardinalDirection direction) {
        return new Position();
    }
    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(x,y, destPosition.x, destPosition.y);
    }

}
