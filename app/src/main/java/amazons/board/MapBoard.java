package amazons.board;

import amazons.figures.*;

import java.util.HashMap;
import java.util.Map;

public class MapBoard implements Board{


    private int numberOfRows;
    private int numberOfColumns;
    Map<Position,Figure> plateau;


    public MapBoard(int numberOfColumns,int numberOfRows)
    {
        plateau=new HashMap<>();
        this.numberOfColumns=numberOfColumns;
        this.numberOfRows=numberOfRows;
        fill(new EmptyFigureGenerator());
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

        plateau.put(position,figure);

    }

    /**
     * Returns the figure at the specified position in the board.
     *
     * @param position : position of the figure to return
     * @return : the figure at the specified position in the board
     */
    @Override
    public Figure getFigure(Position position) {
        return plateau.get(position);
    }

    /**
     * Return {@code true} if the specified position is empty (i.e., contains EMPTY_FIGURE)
     *
     * @param position : position to test
     * @return {@code true} if the specified position is empty in the board.
     */
    @Override
    public boolean isEmpty(Position position) {
        return (getFigure(position) instanceof EmptyFigure);
    }

    /**
     * Return {@code true} if the specified position is out of the board.
     *
     * @param position : position to test
     * @return {@code true} if the specified position is out of the board
     */
    @Override
    public boolean isOutOfBoard(Position position) {
        return (position.isOutOfBounds(numberOfColumns,numberOfRows));
    }

    /**
     * Move the figure positioned at {@code startPosition} to {@code dstPosition}.
     * Updates also the position of this figure.
     *
     * @param startPosition : the position of the figure to move
     * @param dstPosition   : the destination position of the figure
     * @throws IllegalMoveException : if position {@code startPosition} is empty, or the figure
     *                               at position {@code startPosition} cannot move to {@code dstPosition}
     */
    @Override
    public void moveFigure(Position startPosition, Position dstPosition) throws IllegalMoveException {
        if(getFigure(startPosition) instanceof Amazon)
        {
            Amazon amazon= (Amazon) getFigure(startPosition);
            if( amazon.canMoveTo(dstPosition,this))
            {      setFigure(dstPosition,amazon);
                setFigure(startPosition,EmptyFigure.EMPTY_FIGURE);
            }
            else
            {
                throw new IllegalMoveException("Impossible de se deplacer");
            }
        }
        else
        {
            throw new IllegalMoveException(" move impossible");
        }

    }

    /**
     * Place an arrow at  {@code dstPosition}. The arrow originates from {@code startPosition}
     *
     * @param startPosition    : the origin of the arrow shot
     * @param arrowDstPosition : the destination of the arrow
     * @throws IllegalMoveException: if {@code dstPosition} is not empty occupied or there is no amazon
     *                               at position {@code startPosition} or it is not legal for the amazon to shoot an arrow to {@code dstPosition}
     */
    @Override
    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException {
        if(getFigure(startPosition) instanceof Amazon)
        {
            Amazon amazon= (Amazon) getFigure(startPosition);
            if( amazon.canMoveTo(arrowDstPosition,this))
            {
                setFigure(arrowDstPosition, ArrowFigure.ARROW_FIGURE);
            }
            else {
                throw new IllegalMoveException("Impossible de se deplacer");
            }
        }
        else
        {
            throw new IllegalMoveException(" move impossible");
        }
    }

    @Override
    public void fill(FigureGenerator generator) {
        Position position;
        for(int i=0;i<numberOfColumns;i++){
            for(int j=0;j<numberOfRows;j++){
                position=new Position(i,j);
                plateau.put(position,generator.nextFigure(position));

                }
            }

    }

    @Override
    public void afficherBord() {

    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
