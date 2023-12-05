package amazons.game;

import amazons.board.*;
import amazons.figures.*;
import amazons.player.Move;
import amazons.player.Player;
import amazons.player.PlayerID;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;

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



    private List<Amazon>[] positionsAmazons=new List[NUMBER_OF_PLAYERS];

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
        List<Amazon> positionZero=new ArrayList<>();
        List<Amazon> positionsOne=new ArrayList<>();

        Iterator<Position>  positionIterator= bord.positionIterator();
        PresetFigureGenerator figures=new PresetFigureGenerator(createPlayersFiguresWithDefaultPosition());
        while (positionIterator.hasNext())
        {
            Position position=positionIterator.next();
            Figure figure=figures.nextFigure(position);
            bord.setFigure(position,figure);
            if(figure instanceof Amazon)
            {
                if(figure.getPlayerID().index==0)
                    positionZero.add(((Amazon) figure));
                 else
                    positionsOne.add(((Amazon) figure));

            }

        }
        positionsAmazons[0]=positionZero;
        positionsAmazons[1]=positionsOne;
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
        if (hasLost(getPlayerID())) {
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
           // getBoard().setFigure(amazonStartPosition,EmptyFigure.EMPTY_FIGURE);
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



    private boolean hasLost(PlayerID playerID) {

       return amazonMovable(playerID).isEmpty();

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

    public Player[] getPlayers() {
        return players;
    }

     public void afficherPos(PlayerID playerID)
     {
         List<Amazon> positions=positionsAmazons[playerID.index];
         for (Amazon position:positions)
         {
             System.out.println("la posiition de sara "+ position.getPosition().toString());
         }
     }

    /**
     * Une méthode qui retourne que les amazones d'un joueurs qui sont movables
     * @param playerID l'identifiant du joueur
     * @return elle retourne une liste des amazones
     */
     public List<Amazon> amazonMovable(PlayerID playerID)
     {
         List<Amazon> liste=new ArrayList<>();
         for (Amazon amazon: positionsAmazons[playerID.index])
         {
             if(!amazon.getAccessiblePositions(bord).isEmpty())
                 liste.add(amazon);
         }

               return liste;

     }
}
