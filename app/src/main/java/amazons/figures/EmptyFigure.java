package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

public class EmptyFigure implements Figure {


        public static EmptyFigure EMPTY_FIGURE =new EmptyFigure();



    private EmptyFigure(){

    }
    /**
     * Check if this figure can move to {@code position} according to its displacement rules.
     *
     * @param position : a valid position in {@code board}
     * @param board    : a board on which this is placed
     * @return {@code true} if this can move to {@code position} given the current state of {@code board}
     * {@code} false otherwise.
     */
    @Override
    public boolean canMoveTo(Position position, Board board) {
        return false;
    }

    /**
     * Move this to the given position. An exception is thrown if the move
     * is illegal according to the rules of the game.
     *
     * @param position : the position to which this should be moved
     * @param board
     */
    @Override
    public void moveTo(Position position, Board board) throws IllegalMoveException {
        throw new IllegalMoveException("La figure est vide");
    }

    /**
     * Set the position of this figure to the given position.
     *
     * @param position : the new position of this figure
     */
    @Override
    public void setPosition(Position position) {

    }

    /**
     * Get the ID of the player who owns this figure (maybe NONE if this figure is an arrow or empty)
     *
     * @return the PlayerID of the player who owns this figure
     */
    @Override
    public PlayerID getPlayerID() {
       return PlayerID.NONE;

    }
}
