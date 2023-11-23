package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

public class MatrixBoard implements Board{


    private int numberOfRows;
    private int numberOfColumns;
    private Figure[][] plateau;

    public MatrixBoard(int numberOfColumns,int numberOfRows){
         plateau=new Figure[numberOfRows][numberOfColumns];
         this.numberOfColumns=numberOfColumns;
         this.numberOfRows=numberOfRows;
         for(int i=0;i<numberOfRows;i++){
             for(int j=0;j<numberOfColumns;j++){
                 plateau[i][j]=EmptyFigure.EMPTY_FIGURE;

             }
         }
    }
    /**
     * Place the given figure {@code figure} at the position {@code position}. Nothing happens if {@code position}
     * is outside this board.
     *
     * @param position : where the figure has to be positioned
     * @param figure   : the figure to be placed at position {@code position}
     */
    @Override
    public void setFigure(Position position, Figure figure) {

    }

    /**
     * Returns the figure at the specified position in the board.
     *
     * @param position : position of the figure to return
     * @return : the figure at the specified position in the board
     */
    @Override
    public Figure getFigure(Position position) {
        return plateau[position.getX()][position.getY()];
    }

    /**
     * Return {@code true} if the specified position is empty (i.e., contains EMPTY_FIGURE)
     *
     * @param position : position to test
     * @return {@code true} if the specified position is empty in the board.
     */
    @Override
    public boolean isEmpty(Position position) {

        if(getFigure(position) instanceof EmptyFigure)
         return true;
        return false;
    }

    /**
     * Return {@code true} if the specified position is out of the board.
     *
     * @param position : position to test
     * @return {@code true} if the specified position is out of the board
     */
    @Override
    public boolean isOutOfBoard(Position position) {
        return false;
    }
}
