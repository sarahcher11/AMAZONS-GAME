package amazons.player;

import amazons.board.Position;

import java.util.List;
import java.util.Scanner;

public class CLPlayer implements Player {

    private int boardWidth;
    private int boardHeight;
    private PlayerID playerID;
    private List<Position>[] initialPositions;

    private final static Scanner inputScanner = new Scanner(System.in);

    @Override
    public boolean isGUIControlled() {
        return false;
    }

    @Override
    public Move play(Move opponentMove) {
        System.out.println(playerID + " select amazon? (enter X Y coordinate)");
        //use inputScanner.nextInt() to read user input as integer
        int xStart=inputScanner.nextInt();
        int yStart=inputScanner.nextInt();
        Position startPosition=new Position(xStart,yStart);
        System.out.println(playerID + " select destination? (enter X Y coordinate)");
        int xDst=inputScanner.nextInt();
        int yDst=inputScanner.nextInt();
        Position destPosition=new Position(xDst,yDst);

        System.out.println(playerID + " where to shoot arrow? (enter X Y coordinate)");
        int xArrow=inputScanner.nextInt();
        int yArrow=inputScanner.nextInt();
        Position arrowPos=new Position(xArrow,yArrow);

        return new Move(startPosition,destPosition,arrowPos);


    }

    @Override
    public void initialize(int boardWidth, int boardHeight,  PlayerID playerID, List<Position>[] initialPositions) {
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        this.initialPositions=initialPositions;
        this.playerID = playerID;
    }

    @Override
    public PlayerID getPlayerID() {
        return playerID;
    }

    public void setPlayerID(PlayerID playerID) {
        this.playerID = playerID;
    }
}
