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


    public int getX() {return x;}



    public int getY() {return y;}


    /**
     * renvoyer vrai si et seulement si la position this n'est pas  celle d’une case dans une grille
     * ayant numberOfColumns colonnes et numberOfRows lignes
     * @param numberOfColumns = nombre de colonnes de la grille
     * @param numberOfRows= nombre de lignes de la grille
     * @return un booleen
     */
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows){
        if( x>(numberOfColumns-1) || y>(numberOfRows-1) || this.x<0 || this.y<0)
        {
            return true;
        }
        else {
            return false;
        }
    }




    /**
     * renvoyer la position voisine de this dans la direction direction.
     * @param direction=la direction qu'on souhaite prendre
     * @return un objet de type position
     */
    public Position next(CardinalDirection direction) {
        return new Position(this.y+ direction.deltaColumn,this.x+direction.deltaRow);
    }



    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(x,y, destPosition.x, destPosition.y);
    }


    @Override
    public String toString()
    {
        return "("+x+","+y+")";
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }


}
