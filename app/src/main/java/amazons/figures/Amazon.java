package amazons.figures;


import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.ArrayList;
import java.util.List;

public class Amazon extends MovableFigure implements Figure{


    /**
     * Un constructeur de la classse Amazon
     * @param position la position de l'amazon dans la grille
     * @param num le numéro du joueur
     */

    public Amazon(Position position,int num)
    {
        this.position=position;
        this.playerID=PlayerID.getPlayerIDFromIndex(num);


    }
    public Amazon(Position position,PlayerID player)
    {
        this.position=position;
        this.playerID=player;
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
        if( !board.isOutOfBoard(position)
                && board.isEmpty(position)
                && !pathIsBlocked(position, board)
                && ( isHorizental(position) || isVertical(position) || isOnTheSameDiagonal(position)))
       {

           return true;
       }
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

        if(canMoveTo(position,board))
        {

                board.moveFigure(this.position,position);

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

    /**
     * Get a list of accessible position for this amazon
     * @param board
     * @return  return a list of positions
     */

    @Override
    public List<Position> getAccessiblePositions(Board board) {

         List<Position> positions=new ArrayList<>();
         int i=0,j=0;
        int newX,newY;
        for(CardinalDirection card : CardinalDirection.values())
        {
            newX=this.position.getX()+ card.deltaRow;
            newY=this.position.getY()+ card.deltaColumn;

           if(canMoveTo(new Position(newX,newY),board))
            {
                positions.add(new Position(newX,newY));
            }
        }

        return positions;
    }

    /**
     * Retourner vrai si la position ou se dirige l'amazon n'est pas bloqué
     * c'est à dire une Amazone ne peut pas sauter une case occupée
     * @param destination la case de destination de l'amazone
     * @param board
     * @return   retoune un booléen
     */

    public boolean pathIsBlocked(Position destination, Board board) {
        int deltaX = destination.getX() - this.position.getX();
        int deltaY = destination.getY() - this.position.getY();

        int xStep = Integer.compare(deltaX, 0);
        int yStep = Integer.compare(deltaY, 0);

        for (int x = this.position.getX() + xStep, y = this.position.getY() + yStep;
             x != destination.getX() || y != destination.getY();
             x += xStep, y += yStep) {
            Position currentPosition = new Position(x, y);
            if (board.isOutOfBoard(currentPosition) || !board.isEmpty(currentPosition)) {
                return true; // Path is blocked if we find an occupied position
            }
        }
        // Vérifier la case de destination
        if (!board.isEmpty(destination)) {
            return true; // La case de destination est bloquée
        }

        return false; // Le chemin n'est pas bloqué
    }


}
