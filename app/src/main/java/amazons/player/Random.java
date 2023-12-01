package amazons.player;
import amazons.board.Position;
import amazons.game.Game;
import amazons.util.RandomUtil;

import java.util.List;

public class Random implements Player {




    private PlayerID playerID;


    private java.util.Random randomUtil;
    private Game game;

    public Random(java.util.Random randomUtil, Game game)
    {
        this.game=game;
        this.randomUtil=randomUtil;
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

        List<Move> possibleMoves = game.getBoard().getAllPossibleMoves(playerID);

        if (!possibleMoves.isEmpty()) {
            return amazons.util.RandomUtil.getRandomElement(randomUtil, possibleMoves);
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
}
