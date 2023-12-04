package amazons.player;
import amazons.board.Position;
import amazons.controller.GameController;
import amazons.figures.Amazon;
import amazons.game.Game;
import amazons.util.RandomUtil;

import java.util.List;

public class Random implements Player {




    private PlayerID playerID;


    private java.util.Random randomUtil;
    private Game game;

    public static GameController controller;

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


        System.out.println("***********************************");
        game.afficherPos(playerID);
        // je choisis une amazone que je veux bouger aléatoirement
        List<Amazon> positionsAmazones = Game.positionsAmazons[playerID.index];
        System.out.println("posiiiiiiiiiiition " + positionsAmazones);

        Amazon amazoneChoisie = amazons.util.RandomUtil.getRandomElement(randomUtil, positionsAmazones);
        System.out.println("amazone choisie " + amazoneChoisie);

        Position postionDstAmazone = getRandomAccessiblePosition(amazoneChoisie);
        System.out.println("position de destination choisie " + postionDstAmazone);

        if (postionDstAmazone != null) {
            game.updateGameAmazonMove(amazoneChoisie.getPosition(), postionDstAmazone);

            List<Position> accessiblePositions = amazoneChoisie.getAccessiblePositions(game.getBoard());
            if (!accessiblePositions.isEmpty()) {
                Position arrowPosition = amazons.util.RandomUtil.getRandomElement(randomUtil, accessiblePositions);
                System.out.println("arroooooooooow position " + arrowPosition.toString());
                game.updateGameArrowShot(postionDstAmazone, arrowPosition);
                return new Move(amazoneChoisie.getPosition(), postionDstAmazone, arrowPosition);
            }

        }

        // If no valid move was found, choose another action or return a dummy move
        return Move.DUMMY_MOVE;
    }

    private Position getRandomAccessiblePosition(Amazon amazon) {
        List<Position> accessiblePositions = amazon.getAccessiblePositions(game.getBoard());
        return amazons.util.RandomUtil.getRandomElement(randomUtil, accessiblePositions);
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
