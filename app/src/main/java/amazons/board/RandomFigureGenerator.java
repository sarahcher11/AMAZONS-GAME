package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

import java.util.ArrayList;
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
         this.figures=new ArrayList<>(figures);
         this.positionIterator=positionIterator;
    }

    @Override
    public Figure nextFigure(Position position) {

        if (positionIterator.hasNext()) {

            Figure figure = getRandomFigure();
            figure.setPosition(position);
            System.out.println("Assigned position: " + position + " to figure: " + figure.getClass()+ " le j "+figure.getPlayerID());
            return figure;
        } else {
            return EmptyFigure.EMPTY_FIGURE;
        }


    }

    private Figure getRandomFigure() {

        if (figures.isEmpty()) {
            return EmptyFigure.EMPTY_FIGURE;
        }
        int randomIndex = random.nextInt(figures.size());
        Figure figure = (Figure) figures.get(randomIndex);
        figures.remove(randomIndex);
        return figure;
    }


}
