package amazons.player;

import amazons.board.Position;

import java.util.Objects;

public class Move {

     private Position amazonStartPosition;
     private Position amazonDstPosition;
     private Position arrowDstPosition;

    public static final Move DUMMY_MOVE = new Move();



    public Move(Position amazonStartPosition, Position amazonDestPosition, Position arrowDestPosition) {

        this.amazonStartPosition=amazonStartPosition;
        this.amazonDstPosition=amazonDestPosition;
        this.arrowDstPosition=arrowDestPosition;

    }

    public Move(){}

    public Position getAmazonStartPosition() {
        return amazonStartPosition;

    }

    public Position getAmazonDstPosition() {
        return amazonDstPosition;

    }

    public Position getArrowDestPosition() {
        return arrowDstPosition;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move move)) return false;
        return Objects.equals(amazonStartPosition, move.amazonStartPosition) && Objects.equals(amazonDstPosition, move.amazonDstPosition) && Objects.equals(arrowDstPosition, move.arrowDstPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amazonStartPosition, amazonDstPosition, arrowDstPosition);
    }


    @Override
    public String toString()
    {
        return "("+amazonStartPosition.getX()+","+amazonStartPosition.getY()+") : "+
                "("+amazonDstPosition.getX()+","+amazonDstPosition.getY()+")->("+
                arrowDstPosition.getX()+","+arrowDstPosition.getY()+")";
    }


}
