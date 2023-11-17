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




    /**
     * doit renvoyer vrai si et seulement si la position this n'est pas  celle d’une case dans une grille
     * ayant numberOfColumns colonnes et numberOfRows lignes
     * @param numberOfColumns = nombre de colonnes de la grille
     * @param numberOfRows= nombre de lignes de la grille
     * @return
     */
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows){
        if( x>numberOfRows-1 && y>numberOfColumns-1)
        {
            return true;
        }
        return false;
    }

    public Position next(CardinalDirection direction) {
        return new Position();
    }
    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(x,y, destPosition.x, destPosition.y);
    }

}
