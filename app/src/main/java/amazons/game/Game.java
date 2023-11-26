package amazons.game;

import amazons.board.*;
import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.figures.IllegalMoveException;
import amazons.figures.MovableFigure;
import amazons.player.Move;
import amazons.player.Player;
import amazons.player.PlayerID;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Game {
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final int DEFAULT_NUMBER_OF_AMAZONS = 4;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 10;
    private static  final int DEFAULT_NUMBER_OF_ROWS = 10;

    private Board bord;

    private PresetFigureGenerator presetFigureGenerator;


    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));



    private final Player[] players = new Player[NUMBER_OF_PLAYERS];

    private PlayerID winner;

    private int turn = 0;
    private boolean isThisIsTheEnd = false;


    public Game() {

    }

    public void initializeGame(Player player0, Player player1){
        List<MovableFigure> figuresMovables=new ArrayList<>();
        bord=new MatrixBoard(DEFAULT_NUMBER_OF_COLUMNS,DEFAULT_NUMBER_OF_ROWS);
        Iterator<Position> positionIterator= bord.positionIterator();
        players[0]=player0;
        players[1]=player1;
        figuresMovables=createPlayersFiguresWithDefaultPosition();


    }




    private List<MovableFigure> createPlayersFiguresWithDefaultPosition(){
        List<MovableFigure> allPlayersFigures = new ArrayList<>();
        for(Position position: DEFAULT_PLAYER0_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ZERO));
        }
        for(Position position: DEFAULT_PLAYER1_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ONE));
        }
        return allPlayersFigures;
    }






    public void updateGame(Move move){
        Position amazonStartPosition = move.getAmazonStartPosition();
        Position amazonDstPosition = move.getAmazonDstPosition();
        Position arrowDstPosition = move.getArrowDestPosition();

        // Mettre à jour le plateau avec le mouvement de l'amazone
        updateGameAmazonMove(amazonStartPosition, amazonDstPosition);
        // Mettre à jour le plateau avec le tir de flèche
        updateGameArrowShot(amazonDstPosition, arrowDstPosition);
        // Vérifier si le joueur actuel a perdu
        if (hasLost(players[turn].getPlayerID())) {
            winner = players[1 - turn].getPlayerID(); // L'autre joueur est le gagnant
            isThisIsTheEnd = true;
        } else {
            // Passer au tour suivant
            incrementTurn();
        }

    }

    public void updateGameAmazonMove(Position amazonStartPosition, Position amazonDstPosition){
        // Récupérer la figure de l'amazone à déplacer
        Figure amazonFigure = getBoard().getFigure(amazonStartPosition);

        // Déplacer l'amazone vers la nouvelle position
        try {
            getBoard().moveFigure(amazonStartPosition, amazonDstPosition);
            ((MovableFigure) amazonFigure).setPosition(amazonDstPosition);
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }



    }
    // TODO
    public void updateGameArrowShot(Position amazonDstPosition, Position arrowDstPosition) {

    }

    // TODO
    private boolean hasLost(PlayerID playerID) {
        return true;

    }

    // TODO
    public Board getBoard(){
         return null;
    }

    // TODO
    public PlayerID getWinner(){
        if(winner==null)
            return PlayerID.NONE;
        return winner;
    }

    // TODO
    public PlayerID getPlayerID(){
        return PlayerID.PLAYER_ONE;
    }

    // TODO
    public Player getPlayer() {return null; }

    // TODO
    public boolean hasEnded() {
        return true;
    }

    public void incrementTurn(){
        turn++;
    }

    public int getTurn() {return turn; }

    // TODO
    public int getNumberOfColumns(){
        return 0;
    }

    // TODO
    public int getNumberOfRows(){
        return 0;
    }
}
