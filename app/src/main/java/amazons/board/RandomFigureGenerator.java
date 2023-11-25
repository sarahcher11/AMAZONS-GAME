package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomFigureGenerator implements FigureGenerator {


    private Random random;
   private List<MovableFigure> figures;
    private  Iterator<Position> positionIterator;


    public RandomFigureGenerator(Random random, List<MovableFigure> figures, Iterator<Position> positionIterator)
    {
         this.random=random;
         this.figures=figures;
         this.positionIterator=positionIterator;
    }

    @Override
    public Figure nextFigure(Position position) {
        if (positionIterator.hasNext()) {
            MovableFigure figure = getRandomFigure();
            figure.setPosition(positionIterator.next());
            return (Figure)figure;
        } else {
            return EmptyFigure.EMPTY_FIGURE;
        }
    }

    private MovableFigure getRandomFigure() {
        int randomIndex = random.nextInt(figures.size());
        MovableFigure figure = figures.get(randomIndex);
        figures.remove(randomIndex);
        return figure;
    }
}
