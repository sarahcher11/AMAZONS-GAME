package amazons.figures;

import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends MovableFigure {
    @Override
    public List<Position> getAccessiblePositions(Board board) {
        List<Position> positions = new ArrayList<>();

        for (CardinalDirection card : CardinalDirection.values()) {
            int x = this.position.getX() + card.deltaColumn;
            int y = this.position.getY() + card.deltaRow;

            while (!board.isOutOfBoard(new Position(x,y)) ) {
                if (board.isEmpty(new Position(x,y)))
                  positions.add(new Position(x, y));
                x += card.deltaColumn;
                y += card.deltaRow;
            }
        }

        return positions;
    }

    @Override
    public List<Position> getPositionAdjacente(Board board) {
        return null;
    }
}
