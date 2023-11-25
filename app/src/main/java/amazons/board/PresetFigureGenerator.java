package amazons.board;

import amazons.figures.Figure;
import amazons.figures.MovableFigure;

import java.util.List;

public class PresetFigureGenerator implements FigureGenerator{

    private List<MovableFigure> figures;
    public PresetFigureGenerator(List<MovableFigure> figures)
    {
            this.figures=figures;
    }
    @Override
    public Figure nextFigure(Position position) {
        return null;
    }
}
