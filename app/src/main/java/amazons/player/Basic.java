package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.game.Game;

import java.util.List;
import java.util.Random;

public class Basic implements Player{



    private List<Position> positions;
    private int boardHeight;
    private int boardWidth;
    private PlayerID playerID;

    private Game game;

    public Basic(Game game){
        this.game=game;
    }

    /**
     * Play one turn of the game. Receives as input the move of the other player.
     * Return the move of  this player
     *
     * @param opponentMove : the last move of the opponent
     * @return the new move
     */
    @Override
    public Move play(Move opponentMove) {
        List<Position> positions = game.positionsAmazone(playerID);
        Position positionAmazon = null;
        Position amazoneDst = null;
        Position arrowPos = null;
        Amazon figure;
        Boolean stop = false;
        int i = 0;
        while (i < positions.size()) {
            figure = (Amazon) game.getBoard().getFigure(positions.get(i));

            if (figure.getAccessiblePositions(game.getBoard()).size() != 0) {
                positionAmazon = positions.get(i);
                amazoneDst = figure.getAccessiblePositions(game.getBoard()).get(0);
                game.updateGameAmazonMove(positionAmazon, amazoneDst);
                figure = (Amazon) game.getBoard().getFigure(amazoneDst);

                if (figure.getAccessiblePositions(game.getBoard()).size() != 0) {
                    arrowPos = figure.getAccessiblePositions(game.getBoard()).get(0);
                    game.updateGameArrowShot(amazoneDst, arrowPos);
                    return new Move(positionAmazon, amazoneDst, arrowPos);
                }
            } else {
                i++;
            }
        }
        if (game.positionsAccessible(playerID).size() == 0) {
            game.setThisIsTheEnd(true);
        }
        return null;
    }


    /**
     * Give the player the initial state of the game
     *
     * @param boardHeight      :      the height of the board (number of rows)
     * @param boardWidth       :       the width of the board   (number of columns)
     * @param playerID         :     the Id of this player. The first player index is  0
     * @param initialPositions : the initials positions of the figures of each player
     */
    @Override
    public void initialize(int boardHeight, int boardWidth, PlayerID playerID, List<Position>[] initialPositions) {
        this.boardHeight=boardHeight;
        this.boardWidth=boardWidth;
        this.positions=initialPositions[playerID.index];
        this.playerID=playerID;

    }

    /**
     * Determines whether this {@code Player} is controlled by the Graphical User Interface.
     * GUIcontrolled players have their move input by the user via the interface
     *
     * @return {@code true} is this {@code Player} is controlled by the GUI, {@code false}otherwise
     */
    @Override
    public boolean isGUIControlled() {
        return false;
    }

    /**
     * Return the ID of this player
     *
     * @return the ID of this player
     */
    @Override
    public PlayerID getPlayerID() {
        return playerID;
    }

    @Override
    public void setPlayerID(PlayerID playerID) {
       this.playerID=playerID;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public List<Position> getPositions() {
        return positions;
    }



   /* public Position selectAmazonPosition()
    {

        List<Position> positions=game.positionsAmazone(playerID);
        Position positionAmazon=null;
        Position amazoneDst=null;
        Position arrowPos = null;
        Amazon figure;
        Boolean stop=false;
        int i=0;
        while (!stop && i<positions.size())
        {
            figure=(Amazon) game.getBoard().getFigure(positions.get(i));

            if(figure.getAccessiblePositions(game.getBoard()).size()!=0)
            {
                positionAmazon=positions.get(i);
                amazoneDst=figure.getAccessiblePositions(game.getBoard()).get(0);
                game.updateGameAmazonMove(positionAmazon,amazoneDst);
                figure=(Amazon) game.getBoard().getFigure(amazoneDst);
                if(figure.getAccessiblePositions(game.getBoard()).size()!=0)
                {
                    arrowPos=figure.getAccessiblePositions(game.getBoard()).get(0);
                    game.updateGameArrowShot(amazoneDst,arrowPos);
                }
            }
            else {
                i++;
            }
        }
        return new Move(positionAmazon,amazoneDst,arrowPos);
    }*/

}
