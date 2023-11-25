package amazons.board;

import amazons.figures.Figure;
import amazons.figures.IllegalMoveException;

public class MapBoard implements Board{
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
        return null;
    }

    /**
     * Return {@code true} if the specified position is empty (i.e., contains EMPTY_FIGURE)
     *
     * @param position : position to test
     * @return {@code true} if the specified position is empty in the board.
     */
    @Override
    public boolean isEmpty(Position position) {
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

    }

    @Override
    public void fill(FigureGenerator generator) {

    }

    @Override
    public void afficherBord() {

    }
}
