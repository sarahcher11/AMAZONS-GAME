package amazons.board;

import amazons.figures.*;

public class MatrixBoard implements Board{


    private int numberOfRows;
    private int numberOfColumns;
    private Figure[][] plateau;

    public MatrixBoard(int numberOfColumns,int numberOfRows){
         plateau=new Figure[numberOfColumns][numberOfRows];
         this.numberOfColumns=numberOfColumns;
         this.numberOfRows=numberOfRows;
         for(int i=0;i<numberOfColumns;i++){
             for(int j=0;j<numberOfRows;j++){
                 plateau[i][j]=EmptyFigure.EMPTY_FIGURE;

             }
         }
    }


    public void afficherBord()
    {
        for(int i=0;i<numberOfColumns;i++){
            for(int j=0;j<numberOfRows;j++){
                System.out.println("( "+i+" , "+ j+" ");
                System.out.println(plateau[i][j].getClass());

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

        plateau[position.getY()][position.getX()]=figure;

    }

    /**
     * Returns the figure at the specified position in the board.
     *
     * @param position : position of the figure to return
     * @return : the figure at the specified position in the board
     */
    @Override
    public Figure getFigure(Position position) {
        return plateau[position.getY()][position.getX()];
    }

    /**
     * Return {@code true} if the specified position is empty (i.e., contains EMPTY_FIGURE)
     *
     * @param position : position to test
     * @return {@code true} if the specified position is empty in the board.
     */
    @Override
    public boolean isEmpty(Position position) {

        return getFigure(position) instanceof EmptyFigure;
    }

    /**
     * Return {@code true} if the specified position is out of the board.
     *
     * @param position : position to test
     * @return {@code true} if the specified position is out of the board
     */
    @Override
    public boolean isOutOfBoard(Position position) {

        if(position.isOutOfBounds(numberOfColumns,numberOfRows))
            return true;
        return false;
    }

    public void moveFigure(Position source,Position destination) throws IllegalMoveException {
        if(!destination.isOutOfBounds(numberOfColumns,numberOfRows)
            && isEmpty(destination)
                && (getFigure(source) instanceof Amazon)
        )
        {

            getFigure(source).moveTo(destination,this);

        }
        else
        {
            throw new IllegalMoveException("Impossible de se deplacer");

        }
    }




    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }


}
