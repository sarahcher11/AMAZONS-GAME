package amazons.board;

import amazons.figures.Figure;

public class EmptyFigureGenerator implements FigureGenerator {
    @Override
    public Figure nextFigure(Position position) {
        return null;
    }
}
