package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;
import amazons.game.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Greedy implements Player{

    Map<List<Position>,PlayerID> score=new HashMap<>();
      Game game;

       private PlayerID playerID;


      public Greedy(Game game)
      {
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
        this.playerID=playerID;
        //le joueur actuel
           List<Amazon> movableFigureAmazon=game.amazonMovable(playerID);
           List<Position> positions=new ArrayList<>();
           List<Position> position2=new ArrayList<>();
           List<Position> newPos;
           for (Amazon amazon:movableFigureAmazon)
           {
               newPos=amazon.getAccessiblePositions(game.getBoard());
               positions.addAll(newPos);
           }

           score.put(positions,playerID);
           // le second joueur

        movableFigureAmazon=game.amazonMovable(playerID.opponent());
        for (Amazon amazon: movableFigureAmazon)
        {
            newPos=amazon.getPositionAdjacente(game.getBoard());
            position2.addAll(newPos);

        }
        score.put(position2,playerID.opponent());

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
        return null;
    }

    @Override
    public void setPlayerID(PlayerID playerID) {

    }
}
