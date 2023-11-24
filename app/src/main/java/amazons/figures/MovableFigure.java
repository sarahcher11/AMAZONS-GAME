package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.List;

public abstract  class MovableFigure {

    protected Position position;
    protected PlayerID playerID;
    abstract  public List<Position> getAccessiblePositions(Board board);

    public Position getPosition() {
        return position;
    }

    public boolean isHorizental(Position position){
       return this.position.getX()== position.getX();
    }
    public boolean isVertical(Position position){
        return this.position.getY()== position.getY();
    }

    public boolean isOnTheSameDiagonal(Position position)
    {
        int deltaX=Math.abs(this.position.getX()- position.getX());
        int deltaY=Math.abs(this.position.getY()- position.getY());

        return deltaY==deltaX;
    }


}
