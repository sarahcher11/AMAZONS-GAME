package amazons.figures;

import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.ArrayList;
import java.util.List;

public class Amazon extends MovableFigure implements Figure{







    public Amazon(Position position,int num)
    {
        this.position=position;
        this.playerID=PlayerID.getPlayerIDFromIndex(num);


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


        return !board.isOutOfBoard(position)
                && board.isEmpty(position)
                && !pathIsBlocked(position, board)
                && ( isHorizental(position) || isVertical(position) || isOnTheSameDiagonal(position));

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
        if(canMoveTo(position,board))
        {
            try {
                board.moveFigure(this.position,position);
            } catch (IllegalMoveException e) {
                e.printStackTrace();
            }
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
        return playerID;
    }

    @Override
    public List<Position> getAccessiblePositions(Board board) {

         List<Position> positions=new ArrayList<>();
        int newX,newY;
        for(CardinalDirection card : CardinalDirection.values())
        {
            newX=this.position.getX()+ card.deltaRow;
            newY=this.position.getY()+ card.deltaColumn;

            if(board.isEmpty(new Position(newX,newY)) && (!board.isOutOfBoard(new Position(newX,newY))))
            {
                positions.add(new Position(newX,newY));
            }
        }
        return positions;
    }

    private boolean pathIsBlocked(Position destination, Board board) {
        int deltaX = destination.getX() - this.position.getX();
        int deltaY = destination.getY() - this.position.getY();

        int xStep = Integer.compare(deltaX, 0);
        int yStep = Integer.compare(deltaY, 0);

        for (int x = this.position.getX() + xStep, y = this.position.getY() + yStep;
             x != destination.getX() || y != destination.getY();
             x += xStep, y += yStep) {
            Position currentPosition = new Position(x, y);
            if (board.isOutOfBoard(currentPosition) || !(board.isEmpty(currentPosition) || board.getFigure(currentPosition) instanceof Amazon)) {
                return true; // Path is blocked
            }
        }
        return false; // Path is not blocked
    }

}
