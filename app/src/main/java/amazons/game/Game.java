package amazons.game;

import amazons.board.*;
import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.figures.IllegalMoveException;
import amazons.figures.MovableFigure;
import amazons.player.Move;
import amazons.player.Player;
import amazons.player.PlayerID;
import javafx.geometry.Pos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Game {
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final int DEFAULT_NUMBER_OF_AMAZONS = 4;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 10;
    private static  final int DEFAULT_NUMBER_OF_ROWS = 10;

    private Board bord;




    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));



    private final Player[] players = new Player[NUMBER_OF_PLAYERS];

    private PlayerID winner;

    private int turn = 0;
    private boolean isThisIsTheEnd = false;


    public Game() {
        bord = new MatrixBoard(DEFAULT_NUMBER_OF_COLUMNS, DEFAULT_NUMBER_OF_ROWS);
    }


    public void initializeGame(Player player0, Player player1){
        bord.fill(new EmptyFigureGenerator()); // Assurez-vous que le plateau est rempli avec des figures vides.
        Iterator<Position>  positionIterator= bord.positionIterator();
        PresetFigureGenerator figures=new PresetFigureGenerator(createPlayersFiguresWithDefaultPosition());
        while (positionIterator.hasNext())
        {
            Position position=positionIterator.next();
            Figure figure=figures.nextFigure(position);
            bord.setFigure(position,figure);
        }
        players[0] = player0;
        players[1] = player1;
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


    public List<MovableFigure> createPlayersFiguresWithDefaultPositionP1()
    {
        List<MovableFigure> allPlayersFigures = new ArrayList<>();
        for(Position position: DEFAULT_PLAYER0_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ZERO));
        }

        return allPlayersFigures;
    }

    public List<MovableFigure> createPlayersFiguresWithDefaultPositionP2()
    {
        List<MovableFigure> allPlayersFigures = new ArrayList<>();
        for(Position position: DEFAULT_PLAYER1_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ONE));
        }
        return allPlayersFigures;
    }






    public void updateGame(Move move){
        Position amazonStartPosition = move.getAmazonStartPosition();
        Position amazonDstPosition = move.getAmazonDstPosition();
        Position arrowDstPosition = move.getArrowDestPosition();
        updateGameAmazonMove(amazonStartPosition, amazonDstPosition);
        updateGameArrowShot(amazonDstPosition, arrowDstPosition);
        List<Position> list=positionsAccessible(players[turn%NUMBER_OF_PLAYERS].getPlayerID());
        if (list.size()==0) {
            winner = players[(turn+1)%NUMBER_OF_PLAYERS].getPlayerID(); // L'autre joueur est le gagnant
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

    public void updateGameArrowShot(Position amazonDstPosition, Position arrowDstPosition) {
        try {
            getBoard().shootArrow(amazonDstPosition,arrowDstPosition);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

    }


    /**
     * Méthode qui permet d'avoir toute les positions accessible pour toutes les amazone du joueur
     * @param playerID l'ID du joueur pour qu'on lui cherche ses positions
     * @return une liste de toutes les positions
     */
    public List<Position> positionsAccessible(PlayerID playerID)
    {
        Iterator<Position> positionIterator=new PositionIterator(getNumberOfColumns(),getNumberOfRows());
        List<Position> list=new ArrayList<>();
        List<Position> list2=new ArrayList<>();
        while (positionIterator.hasNext())
        {
            Figure figure=bord.getFigure(positionIterator.next());
            if(figure.getPlayerID().equals(playerID))
            {

                list2=((MovableFigure)figure).getAccessiblePositions(bord);
                list.addAll(list2);
            }
        }
        return list;
    }

    public List<Position> positionsAmazone(PlayerID playerID)
    {
        Iterator<Position> positionIterator=new PositionIterator(getNumberOfColumns(),getNumberOfRows());
        List<Position> list=new ArrayList<>();
        while (positionIterator.hasNext())
        {
            Position position=positionIterator.next();

            if(bord.getFigure(position) instanceof Amazon)
            {
                Amazon amazon=(Amazon) bord.getFigure(position);
                if(amazon.getPlayerID().equals(playerID) )
                {
                    list.add(position);
                }
            }

        }
        return list;
    }


    private boolean hasLost(PlayerID playerID) {
        List<Position> amazonPositions = positionsAmazone(playerID);

        for (Position amazonPosition : amazonPositions) {
            Amazon amazon = (Amazon) bord.getFigure(amazonPosition);
            List<Position> accessiblePositions = amazon.getAccessiblePositions(bord);
            if (!accessiblePositions.isEmpty()) {
                return false; // Player has at least one Amazon with accessible positions
            }
        }

        return true; // Player has no Amazons with accessible positions
    }



    public Board getBoard(){
         return bord;
    }


    public PlayerID getWinner(){
        if(winner==null)
            return PlayerID.NONE;
        return winner;
    }


    public PlayerID getPlayerID(){
        return players[turn % NUMBER_OF_PLAYERS].getPlayerID();
    }


    public Player getPlayer() {
        return players[turn % NUMBER_OF_PLAYERS];
    }

    public boolean hasEnded() {
        return isThisIsTheEnd;
    }

    public void incrementTurn(){
        turn++;
    }

    public int getTurn() {return turn; }


    public int getNumberOfColumns(){
        return bord.getNumberOfColumns();
    }

    public int getNumberOfRows(){
        return bord.getNumberOfRows();
    }

    public void setThisIsTheEnd(boolean thisIsTheEnd) {
        isThisIsTheEnd = thisIsTheEnd;
    }
}
