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
}
