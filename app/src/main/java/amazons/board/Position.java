package amazons.board;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.Objects;

//TODO complete the code this class and its documentation

public class Position implements Serializable {
    public static final DataFormat POSITION_FORMAT = new DataFormat("amazons.position");

   int x;
   int y;

    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public int getX() {return 0;}
    public int getY() {return 0;}




    /**
     * renvoyer vrai si et seulement si la position this n'est pas  celle d’une case dans une grille
     * ayant numberOfColumns colonnes et numberOfRows lignes
     * @param numberOfColumns = nombre de colonnes de la grille
     * @param numberOfRows= nombre de lignes de la grille
     * @return un booleen
     */
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows){
        if( x>numberOfRows-1 && y>numberOfColumns-1)
        {
            return true;
        }
        return false;
    }

    /**
     * renvoyer la position voisine de this dans la direction direction.
     * @param direction=la direction qu'on souhaite prendre
     * @return un objet de type position
     */
    public Position next(CardinalDirection direction) {
        return new Position(this.x+direction.deltaRow,this.y+ direction.deltaColumn);
    }
    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(x,y, destPosition.x, destPosition.y);
    }

}
