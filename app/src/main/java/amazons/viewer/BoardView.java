package amazons.viewer;

import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;
import amazons.controller.GameController;
import amazons.player.Move;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static amazons.player.Random.controller;

public class BoardView extends GridPane{
    private final Map<Position, FieldView> fieldMap = new HashMap<>();


    public BoardView(GameController controller) {
        Board board = controller.getBoard();
        System.out.println("LA POSITION 3, 0"+board.getFigure(new Position(3,0)));
        System.out.println("LA POSITION 6, 0"+board.getFigure(new Position(6,0)));
        System.out.println("LA POSITION 0, 3"+board.getFigure(new Position(0,3)));
        System.out.println("LA POSITION 9, 3"+board.getFigure(new Position(9,3)));
        for (Iterator<Position> it = board.positionIterator(); it.hasNext(); ) {
            Position position = it.next();
            FieldView field = new FieldView(position,controller);
            add(field, position.getX(), position.getY());
            fieldMap.put(position,field);
        }

    }

    public void updateFields() {
        for (FieldView field: fieldMap.values()) {
            field.setFigureIcon();
        }
    }

    /*
    the move is supposed to be valid
    board has already been updated
     */
    public void showMove(Move move) {


            fieldMap.get(move.getAmazonStartPosition()).setFigureIcon();
        System.out.println("ineeeeeeeeeees");
            highlightAmazonMove(move.getAmazonStartPosition(),move.getAmazonDstPosition());
        System.out.println("ineeeeeeeeeees 2");
            fieldMap.get(move.getAmazonDstPosition()).setFigureIcon();

            highlightArrowShoot(move.getAmazonDstPosition(), move.getArrowDestPosition());
            fieldMap.get(move.getArrowDestPosition()).setFigureIcon();
        Board board = controller.getBoard();
        System.out.println("LA POSITION 3, 0"+board.getFigure(new Position(3,0)));
        System.out.println("LA POSITION 6, 0"+board.getFigure(new Position(6,0)));
        System.out.println("LA POSITION 0, 3"+board.getFigure(new Position(0,3)));
        System.out.println("LA POSITION 9, 3"+board.getFigure(new Position(9,3)));


    }

    private void highlightAmazonMove(Position startPosition, Position destPosition){
        System.out.println("destination de la position dans highlight "+ destPosition.toString());
        CardinalDirection direction = startPosition.getDirection(destPosition);
        System.out.println("inessssssssssssssssss 3 dircetion "+direction);
        for(Position position = startPosition; !position.equals(destPosition); position = position.next(direction)) {
            fieldMap.get(position).setHighlightBackGroundColorTemp();
        }
        fieldMap.get(destPosition).setHighlightBackGroundColorTemp();
    }

    private void highlightArrowShoot(Position amazonPosition, Position arrowDestPosition){
        CardinalDirection direction  = amazonPosition.getDirection(arrowDestPosition);
        System.out.println(" position destination de arrow dans highlight "+arrowDestPosition.toString());
        for(Position position = amazonPosition.next(direction); !position.equals(arrowDestPosition); position =  position.next(direction)){
            fieldMap.get(position).setHighlightShootBackGroundColorTemp();
        }
        fieldMap.get(arrowDestPosition).setHighlightShootBackGroundColorTemp();
    }

}
