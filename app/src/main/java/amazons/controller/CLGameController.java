package amazons.controller;

import amazons.game.Game;
import amazons.player.*;

import java.util.Random;

public class CLGameController {
    private final Game game;
    private Move lastMove;
    private static final Random random = new Random();

    private final Player[] players = new Player[Game.NUMBER_OF_PLAYERS];

    public CLGameController(){
        this.game = new Game();

        CLPlayer clPlayer1=new CLPlayer();
        CLPlayer clPlayer2=new CLPlayer();
        setPlayer(clPlayer1,0);
        setPlayer(clPlayer2,1);
       clPlayer1.setPlayerID(players[0].getPlayerID());
        clPlayer2.setPlayerID(players[1].getPlayerID());
        clPlayer2.setPlayerID(PlayerID.PLAYER_ONE);
        clPlayer1.setPlayerID(PlayerID.PLAYER_ZERO);
        game.initializeGame(players[0], players[1]);
        lastMove = Move.DUMMY_MOVE;
    }
    private void setPlayer(Player player, int playerIndex){
        players[playerIndex] = player;
    }


    public void run() {
        while (!game.hasEnded()) {
            System.out.println(game.getBoard().toString());
            game.getBoard().toString();
            lastMove = game.getPlayer().play(lastMove);
            game.updateGame(lastMove);
            game.incrementTurn();
        }
        System.out.println(game.getBoard());
        System.out.println("Player " + game.getWinner() + " wins!!! ");
    }

}
