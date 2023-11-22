package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.ArrayList;
import java.util.List;

public class Amazon extends MovableFigure implements Figure{

    private Position position;

    private PlayerID playerID;

    public Amazon(Position initialPosition,PlayerID playerID) {
        this.position = initialPosition;
        this.playerID=playerID;
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
        if (board.isOutOfBoard(position) || position.equals(EmptyFigure.EMPTY_FIGURE) || position.equals(ArrowFigure.ARROW_FIGURE))
                 { return false ;}
        // voir si elle peut se deplacer diagonalement ou verticalement ou horizentalement

        int row= position.getX()-this.position.getX();
        int column= position.getY()-this.position.getY();
        return (row==0 || column==0 || Math.abs(row)==Math.abs(column));
    }

    /**
     * Move this to the given position. An exception is thrown if the move
     * is illegal according to the rules of the game.
     *
     * @param position : the position to which this should be moved
     * @param board
     */
    @Override
    public void moveTo(Position position, Board board) {
        if( canMoveTo(position,board))
        {
            // mettre la figure EmptyFigure dans la case position dans bord
            board.setFigure(this.position,EmptyFigure.EMPTY_FIGURE);
            this.position=position;
            //mettre la figure amazon ( this ) dans la position this.position 
            board.setFigure(this.position,this);

        }
    }

    /**
     * Set the position of this figure to the given position.
     *
     * @param position : the new position of this figure
     */
    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    /**
     * Get the ID of the player who owns this figure (maybe NONE if this figure is an arrow or empty)
     *
     * @return the PlayerID of the player who owns this figure
     */
    @Override
    public PlayerID getPlayerID() {
        return this.playerID;
    }

    @Override
    public List<Position> getAccessiblePositions(Board board) {
        List<Position> accessiblePositions = new ArrayList<>();

    }
}
