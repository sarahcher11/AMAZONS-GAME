package amazons.player;

import amazons.board.Position;

import java.util.Objects;

public class Move {

     private Position amazonStartPosition;
     private Position amazonDstPosition;
     private Position arrowDstPosition;

    public static final Move DUMMY_MOVE = new Move();


    // TODO complete the code of this class

    public Position getAmazonStartPosition() {
        return amazonStartPosition;
        // TODO
    }

    public Position getAmazonDstPosition() {
        return amazonDstPosition;
        // TODO
    }

    public Position getArrowDestPosition() {
        return null;
        // TODO
    }


    private Move() {}


    // TODO
    public Move(Position amazonStartPosition, Position amazonDestPosition, Position arrowDestPosition) {

    }


    // TODO method equals
    // TODO method toString
    // TODO method hashCode
}
